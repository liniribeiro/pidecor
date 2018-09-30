package com.aliniribeiro.admin.api.controller.customerorder;

import com.aliniribeiro.admin.api.common.exceptions.ExceptionType;
import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.controller.customerorder.contracts.ClientOrderByStatusDTO;
import com.aliniribeiro.admin.api.controller.customerorder.contracts.ClientOrderMonthsEvolutionDTO;
import com.aliniribeiro.admin.api.controller.customerorder.contracts.OrderDTO;
import com.aliniribeiro.admin.api.controller.customerorder.contracts.ReceivedValueByMonthDTO;
import com.aliniribeiro.admin.api.controller.item.ItemService;
import com.aliniribeiro.admin.api.controller.provider.client.ProvidersClient;
import com.aliniribeiro.admin.api.model.customer.CustomerEntity;
import com.aliniribeiro.admin.api.model.customer.CustomerRepository;
import com.aliniribeiro.admin.api.model.customerorder.CustomerOrderEntity;
import com.aliniribeiro.admin.api.model.customerorder.CustomerOrderRepository;
import com.aliniribeiro.admin.api.model.customerorder.enums.OrderStatus;
import com.aliniribeiro.admin.api.model.item.ItemEntity;
import com.aliniribeiro.admin.api.model.provider.ProviderRepository;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Component;
import providers.wsdl.CreateOrderResponse;
import providers.wsdl.GetOrderStatusResponse;
import providers.wsdl.Item;

import java.beans.Transient;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;

@Component
public class CustomerOrderService {

    private final Locale LOCALE = new Locale("pt", "BR");

    /**
     * Método que cia um novo pedido.
     *
     * @param orderDTO DTO com as informações do pedido.
     * @return OrderDTO com as informações do pedido atualizadas.
     */
    @Transient
    public OrderDTO createOrder(OrderDTO orderDTO) {
        CustomerEntity customer = Spring.bean(CustomerRepository.class).findById(orderDTO.customerId);
        if (customer == null || orderDTO.itemIds == null || orderDTO.itemIds.isEmpty()) {
            throw new IllegalArgumentException(ExceptionType.MISSING_INFORMATIONS_ORDER.name());
        }
        CustomerOrderEntity order = new CustomerOrderEntity();
        order.setCustomerId(customer.getId());
        order.setStatus(OrderStatus.GENERATED_REQUEST);
        order.setDeliveryAdress(orderDTO.deliveryAddress);
        order.setDeliveryReceiverName(orderDTO.deliveryReceiverName);
        order.setCreationDate(LocalDate.now());
        Spring.bean(CustomerOrderRepository.class).save(order);

        List<ItemEntity> providerList = new ArrayList<>();
        try {
            providerList = sendOrderToProvider(orderDTO, order);
        } catch (Exception e) {
            Spring.bean(CustomerOrderRepository.class).delete(order);
            throw new IllegalArgumentException(ExceptionType.NEW_ORDER_ERROR.name());
        }

        return Spring.bean(CustomerOrderMapper.class).entityToOrderDTO(order, providerList);
    }

    /**
     * Método que envia o pedido para o sistema de terceiros.
     *
     * @param order       DTO com as informações do pedido.
     * @param orderEntity Pedido criado.
     * @return Lista de Items que compôem os pedidos.
     */
    private List<ItemEntity> sendOrderToProvider(OrderDTO order, CustomerOrderEntity orderEntity) {
        CreateOrderResponse providersResponse = Spring.bean(ProvidersClient.class).createOrder(Spring.bean(CustomerOrderMapper.class).toOrder(order));
        List<Item> providerList = providersResponse.getItems().getData();
        orderEntity.setProvidersAPIId(providersResponse.getOrder());
        Spring.bean(CustomerOrderRepository.class).save(orderEntity);
        List<ItemEntity> providerEntityList = new ArrayList<>();
        providerList.forEach(item -> {
            UUID providerId = Spring.bean(ProviderRepository.class).findByProviderAPIId(item.getProviderId());
            providerEntityList.add(Spring.bean(ItemService.class).createItem(item.getName(), item.getPhotolink(), providerId, item.getPrice(), orderEntity.getId()));
        });
        return providerEntityList;
    }

    /**
     * Método que retorna a quantidade de pedidos de acordo com seu status.
     *
     * @return Lista de ClientOrderByStatusDTO .
     */
    public Optional<List<ClientOrderByStatusDTO>> findAllByStatus() {
        updateOrderStatus();
        Optional<List<Tuple>> orders = Spring.bean(CustomerOrderRepository.class).findAllByStatus();
        if (orders.isPresent()) {
            List<ClientOrderByStatusDTO> mappedOrder = new ArrayList<>();
            orders.get().forEach(o -> mappedOrder.add(Spring.bean(CustomerOrderMapper.class).toClientOrderByStatusDTO(o)));
            mappedOrder.sort((o1, o2) -> o1.status.compareTo(o2.status));

            return Optional.ofNullable(mappedOrder);
        }
        return Optional.empty();
    }

    /**
     * Método que atualiza o status dos pedidos, de acordo com o sistema dos fornecedores.
     */
    public void updateOrderStatus() {
        Optional<List<CustomerOrderEntity>> orders = Spring.bean(CustomerOrderRepository.class).findNotDeliveredOrders();
        if (orders.isPresent()) {
            orders.get().forEach(o -> {
                GetOrderStatusResponse response = Spring.bean(ProvidersClient.class).getOrderStatus(o.getProvidersAPIId());
                o.setStatus(OrderStatus.getOrderStatus(response.getStatus()));
                Spring.bean(CustomerOrderRepository.class).save(o);
            });
        }
    }

    /**
     * Busca a evolução de pedidos realizados nos últimos 6 meses.
     * O dia base para a busca das informações é o dia primeiro do mês subsequente.
     *
     * @return
     */
    public Optional<List<ClientOrderMonthsEvolutionDTO>> getOrderEvolutionValue() {
        List<ClientOrderMonthsEvolutionDTO> monthsDTO = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (int i = 11; i >= 0; i--) {
            YearMonth month = YearMonth.from(date.minusMonths(i));
            Optional<Long> monthValue = Spring.bean(CustomerOrderRepository.class).getOrderEvolutionValue(month);
            if (monthValue.isPresent()) {
                ClientOrderMonthsEvolutionDTO dto = new ClientOrderMonthsEvolutionDTO();
                dto.month = month.getMonth().getDisplayName(TextStyle.SHORT, LOCALE).toUpperCase();
                dto.ordersAmmount = monthValue.get();
                monthsDTO.add(dto);
            }
        }
        return Optional.ofNullable(monthsDTO);
    }

    /**
     * Busca a massa mensal de pagamentos recebidos pelos itens vendidos dos fornecedores dos últimos 12 meses.
     *
     * @return
     */
    public Optional<List<ReceivedValueByMonthDTO>> getReceivedValueByMonth() {
        List<ReceivedValueByMonthDTO> receivedValue = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (int i = 11; i >= 0; i--) {
            YearMonth month = YearMonth.from(date.minusMonths(i));
            Optional<List<ItemEntity>> monthValue = Spring.bean(CustomerOrderRepository.class).getItemsSoldByMonth(month);
            if (monthValue.isPresent()) {
                ReceivedValueByMonthDTO dto = new ReceivedValueByMonthDTO();
                dto.month = month.getMonth().getDisplayName(TextStyle.SHORT, LOCALE).toUpperCase();
                dto.receivedValue = getMonthReceivedValue(monthValue.get());
                receivedValue.add(dto);
            }
        }
        return Optional.ofNullable(receivedValue);
    }

    /**
     * Busca a massa do mês corrente de pagamentos recebidos pelos itens vendidos dos fornecedores dos últimos 12 meses.
     *
     * @return
     */
    public Optional<String> getReceivedValueCurentMonth() {
        LocalDate date = LocalDate.now();
        YearMonth month = YearMonth.from(date);
        Optional<List<ItemEntity>> monthValue = Spring.bean(CustomerOrderRepository.class).getItemsSoldByMonth(month);
        if (monthValue.isPresent()) {
            DecimalFormat df = new DecimalFormat("###.##");
            return Optional.ofNullable(df.format(getMonthReceivedValue(monthValue.get())));
        }
        return Optional.empty();
    }


    private Double getMonthReceivedValue(List<ItemEntity> itemList) {
        Double monthValue = itemList.stream().mapToDouble(p -> p.getPrice() * p.getProvider().getPercentage() / 100).sum();
        return monthValue;
    }
}
package com.aliniribeiro.admin.api.controller.monitoringreport;

import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.controller.customerorder.CustomerOrderService;
import com.aliniribeiro.admin.api.controller.monitoringreport.contracts.MonitoringReportDTO;
import com.aliniribeiro.admin.api.controller.monitoringreport.contracts.MonitoringReportResponseDTO;
import com.aliniribeiro.admin.api.controller.provider.client.ProvidersClient;
import com.aliniribeiro.admin.api.model.customer.CustomerEntity;
import com.aliniribeiro.admin.api.model.customer.CustomerRepository;
import com.aliniribeiro.admin.api.model.customerorder.CustomerOrderEntity;
import com.aliniribeiro.admin.api.model.customerorder.CustomerOrderRepository;
import com.aliniribeiro.admin.api.model.item.ItemEntity;
import com.aliniribeiro.admin.api.model.item.ItemRepository;
import com.aliniribeiro.admin.api.model.provider.ProviderEntity;
import com.aliniribeiro.admin.api.model.provider.ProviderRepository;
import com.aliniribeiro.admin.api.model.user.UserEntity;
import com.aliniribeiro.admin.api.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class MonitoringReportService {


    /**
     * Método que busca todas as informações da base de dados de acordo com os prâmetros informados.
     *
     * @param data MonitoringReportDTO com as informações da busca.
     * @return Lista de MonitoringReportResponseDTO com as informações buscadas de acordo com o tipo da tabela solicitada.
     */
    public Optional<List<MonitoringReportResponseDTO>> findAllData(MonitoringReportDTO data) {
        YearMonth startMonth = YearMonth.parse(data.startDate);
        YearMonth endMonth = YearMonth.parse(data.endDate);
        LocalDate start = startMonth.atDay(1);
        LocalDate end = endMonth.atEndOfMonth();

        if (data.type.equalsIgnoreCase("PROVIDERS")) {
            return getProvidersData(start, end);
        } else if (data.type.equalsIgnoreCase("USERS")) {
            return getUsersData(start, end);
        } else if (data.type.equalsIgnoreCase("CUSTOMER_ORDER")) {
            return getCustomerOrderData(start, end);
        } else if (data.type.equalsIgnoreCase("CUSTOMER")) {
            return getCustomerData(start, end);
        } else if (data.type.equalsIgnoreCase("ITEMS")) {
            return getItemsData(start, end);
        }

        return Optional.empty();
    }

    private Optional<List<MonitoringReportResponseDTO>> getProvidersData(LocalDate startDate, LocalDate endDate) {
        Optional<List<ProviderEntity>> providers = Spring.bean(ProviderRepository.class).findBetweenDates(startDate, endDate);
        List<MonitoringReportResponseDTO> dtoList = new ArrayList<>();
        if (providers.isPresent()) {
            AtomicReference<Integer> index = new AtomicReference<>(1);
            providers.get().forEach(p -> {
                dtoList.add(Spring.bean(MonitoringReportMapper.class).providersToDTO(p, index.get()));
                index.set(index.get() + 1);
            });
            return Optional.ofNullable(dtoList);
        }

        return Optional.empty();
    }

    private Optional<List<MonitoringReportResponseDTO>> getUsersData(LocalDate startDate, LocalDate endDate) {
        Optional<List<UserEntity>> users = Spring.bean(UserRepository.class).findBetweenDates(startDate, endDate);
        List<MonitoringReportResponseDTO> dtoList = new ArrayList<>();
        if (users.isPresent()) {
            AtomicReference<Integer> index = new AtomicReference<>(1);
            users.get().forEach(u -> {
                dtoList.add(Spring.bean(MonitoringReportMapper.class).usersToDTO(u, index.get()));
                index.set(index.get() + 1);
            });
            return Optional.ofNullable(dtoList);
        }

        return Optional.empty();
    }

    private Optional<List<MonitoringReportResponseDTO>> getCustomerOrderData(LocalDate startDate, LocalDate endDate) {
        Spring.bean(CustomerOrderService.class).updateOrderStatus();
        Optional<List<CustomerOrderEntity>> orders = Spring.bean(CustomerOrderRepository.class).findBetweenDates(startDate, endDate);
        List<MonitoringReportResponseDTO> dtoList = new ArrayList<>();
        if (orders.isPresent()) {
            AtomicReference<Integer> index = new AtomicReference<>(1);
            orders.get().forEach(c -> {
                dtoList.add(Spring.bean(MonitoringReportMapper.class).customerOrderToDTO(c, index.get()));
                index.set(index.get() + 1);
            });
            return Optional.ofNullable(dtoList);
        }

        return Optional.empty();
    }

    private Optional<List<MonitoringReportResponseDTO>> getCustomerData(LocalDate startDate, LocalDate endDate) {
        Optional<List<CustomerEntity>> customer = Spring.bean(CustomerRepository.class).findBetweenDates(startDate, endDate);
        List<MonitoringReportResponseDTO> dtoList = new ArrayList<>();
        if (customer.isPresent()) {
            AtomicReference<Integer> index = new AtomicReference<>(1);
            customer.get().forEach(c -> {
                dtoList.add(Spring.bean(MonitoringReportMapper.class).customerToDTO(c, index.get()));
                index.set(index.get() + 1);
            });
            return Optional.ofNullable(dtoList);
        }

        return Optional.empty();
    }

    private Optional<List<MonitoringReportResponseDTO>> getItemsData(LocalDate startDate, LocalDate endDate) {
        Optional<List<ItemEntity>> items = Spring.bean(ItemRepository.class).findBetweenDates(startDate, endDate);
        List<MonitoringReportResponseDTO> dtoList = new ArrayList<>();
        if (items.isPresent()) {
            AtomicReference<Integer> index = new AtomicReference<>(1);
            items.get().forEach(i -> {
                dtoList.add(Spring.bean(MonitoringReportMapper.class).itemToDTO(i, index.get()));
                index.set(index.get() + 1);
            });
            return Optional.ofNullable(dtoList);
        }

        return Optional.empty();
    }
}

package com.aliniribeiro.admin.api.controller.customerorder;

import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.controller.customerorder.contracts.ClientOrderByStatusDTO;
import com.aliniribeiro.admin.api.controller.customerorder.contracts.OrderDTO;
import com.aliniribeiro.admin.api.controller.item.ItemMapper;
import com.aliniribeiro.admin.api.model.customerorder.CustomerOrderEntity;
import com.aliniribeiro.admin.api.model.customerorder.enums.OrderStatus;
import com.aliniribeiro.admin.api.model.item.ItemEntity;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Component;
import providers.wsdl.Order;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerOrderMapper {


    public ClientOrderByStatusDTO toClientOrderByStatusDTO(Tuple tuple) {
        if (tuple == null) {
            return null;
        }
        OrderStatus status = tuple.get(0, OrderStatus.class);
        Long value = tuple.get(1, Long.class);

        ClientOrderByStatusDTO dto = new ClientOrderByStatusDTO();
        dto.status = status.description;
        dto.amount = value.intValue();
        return dto;
    }

    public Order toOrder(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setDeliveryAddress(dto.deliveryAddress);
        order.setDeliveryReceiverName(dto.deliveryReceiverName);
        order.setItems(dto.itemIds.toString());
        return order;
    }

    public OrderDTO entityToOrderDTO(CustomerOrderEntity entity, List<ItemEntity> items) {
        if (entity == null) {
            return null;
        }
        OrderDTO dto = new OrderDTO();
        dto.customerId = entity.getCustomerId();
        dto.deliveryAddress = entity.getDeliveryAdress();
        dto.deliveryReceiverName = entity.getDeliveryReceiverName();
        dto.status = entity.getStatus().description;
        dto.items =  new ArrayList<>();
        items.forEach( i -> {
            dto.items.add(Spring.bean(ItemMapper.class).toDTO(i));
        });

        return dto;
    }
}

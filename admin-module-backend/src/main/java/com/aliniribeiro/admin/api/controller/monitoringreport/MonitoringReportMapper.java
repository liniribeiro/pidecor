package com.aliniribeiro.admin.api.controller.monitoringreport;

import com.aliniribeiro.admin.api.controller.monitoringreport.contracts.MonitoringReportResponseDTO;
import com.aliniribeiro.admin.api.model.customer.CustomerEntity;
import com.aliniribeiro.admin.api.model.customerorder.CustomerOrderEntity;
import com.aliniribeiro.admin.api.model.customerorder.enums.OrderStatus;
import com.aliniribeiro.admin.api.model.item.ItemEntity;
import com.aliniribeiro.admin.api.model.provider.ProviderEntity;
import com.aliniribeiro.admin.api.model.provider.enums.*;
import com.aliniribeiro.admin.api.model.user.UserEntity;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class MonitoringReportMapper {

    public MonitoringReportResponseDTO providersToDTO(ProviderEntity entity, Integer index) {
        if (entity == null) {
            return null;
        }
        MonitoringReportResponseDTO dto = new MonitoringReportResponseDTO();
        dto.index = index;
        dto.name = entity.getName();
     //   dto.phone = entity.getPhone();
        dto.email = entity.getEmail();
        dto.qtd_clients = entity.getQtdClient();
        dto.reputation = entity.getReputation() != null ? ProviderReputation.getDscription(entity.getReputation().name()) : null;
        dto.category = entity.getCategory() != null ? ProviderCategory.getDscription(entity.getCategory().name()) : null;
        dto.qtd_items = entity.getItems() + " items";
        dto.percentage = entity.getPercentage();
        dto.delivery_days = entity.getDeliveryDays() + " dias";
        dto.rejection_rate = entity.getRejectionRate() + " %";
        dto.qtd_items_sold = entity.getQtdItemsSold() + " items";
     //   dto.status = entity.getReputation() != null ? ProviderStatus.getDecription(entity.getStatus().name()) : null; ;
        return dto;
    }

    public MonitoringReportResponseDTO usersToDTO(UserEntity entity, Integer index) {
        if (entity == null) {
            return null;
        }
        MonitoringReportResponseDTO dto = new MonitoringReportResponseDTO();
        dto.index = index;
        dto.name = entity.getName();
        dto.email = entity.getEmail();
        dto.date = entity.getActivatedDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dto.profile = entity.getProfile().getDescription();
        return dto;
    }

    public MonitoringReportResponseDTO itemToDTO(ItemEntity entity, Integer index) {
        if (entity == null) {
            return null;
        }
        MonitoringReportResponseDTO dto = new MonitoringReportResponseDTO();
        dto.index = index;
        dto.name = entity.getName();
        dto.providerName = entity.getProvider().getName();
        dto.date = entity.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dto.price =  "R$ "+ entity.getPrice();
        return dto;
    }

    public MonitoringReportResponseDTO customerToDTO(CustomerEntity entity, Integer index) {
        if (entity == null) {
            return null;
        }
        MonitoringReportResponseDTO dto = new MonitoringReportResponseDTO();
        dto.index = index;
        dto.name = entity.getName();
        dto.email = entity.getUserAccount().getEmail();
        dto.date = entity.getUserAccount().getActivatedDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return dto;
    }

    public MonitoringReportResponseDTO customerOrderToDTO(CustomerOrderEntity entity, Integer index) {
        if (entity == null) {
            return null;
        }
        MonitoringReportResponseDTO dto = new MonitoringReportResponseDTO();
        dto.index = index;
        dto.name = entity.getCustomer().getName();
        dto.receiverName = entity.getDeliveryReceiverName();
        dto.deliveryAddress = entity.getDeliveryAdress();
        dto.status = OrderStatus.getDecription(entity.getStatus().name());
        dto.qtd_items = entity.getItems().size() + " items";
        return dto;
    }
}

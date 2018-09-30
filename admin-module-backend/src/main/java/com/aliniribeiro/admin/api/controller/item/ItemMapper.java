package com.aliniribeiro.admin.api.controller.item;

import com.aliniribeiro.admin.api.controller.item.contracts.ItemDTO;
import com.aliniribeiro.admin.api.model.item.ItemEntity;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ItemMapper {

    public ItemDTO toDTO(ItemEntity entity) {
        if (entity == null) {
            return null;
        }
        ItemDTO dto = new ItemDTO();
        dto.customerOrderId = entity.getCustomerOrderId();
        dto.date = entity.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dto.name = entity.getName();
        dto.photoLink = entity.getPhotoLink();
        dto.price = entity.getPrice();
        dto.provider = entity.getProviderId();
        dto.status = entity.getStatus().name();
        return dto;
    }
}

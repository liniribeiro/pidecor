package com.aliniribeiro.admin.api.controller.item;

import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.model.item.ItemEntity;
import com.aliniribeiro.admin.api.model.item.ItemRepository;
import com.aliniribeiro.admin.api.model.item.enums.ItemStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class ItemService {

    public ItemEntity createItem(String name, String photoLink, UUID provider, Double price, UUID orderId){
        ItemEntity item = new ItemEntity();
        item.setDate(LocalDate.now());
        item.setPrice(price);
        item.setName(name);
        item.setPhotoLink(photoLink);
        item.setProviderId(provider);
        item.setStatus(ItemStatus.UNAVALIABLE);
        item.setCustomerOrderId(orderId);
        Spring.bean(ItemRepository.class).save(item);
        return item;
    }
}

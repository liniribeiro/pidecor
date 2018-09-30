package com.aliniribeiro.admin.api.controller.customerorder.contracts;

import com.aliniribeiro.admin.api.controller.item.contracts.ItemDTO;

import java.util.List;
import java.util.UUID;

public class OrderDTO {


    /**
     * Lista de Ids dos items do pedido
     */
    public List<String> itemIds;

    /**
     * Identificador único do cliente que realizou o pedido
     */
    public UUID customerId;

    /**
     * Endereço de entrega do pedido
     */
    public String deliveryAddress;

    /**
     * Pessoa que receberá o pedido
     */
    public String deliveryReceiverName;

    /**
     * Status do pedido
     */
    public String status;

    /**
     * Items do pedido
     */
    public List<ItemDTO> items;
}

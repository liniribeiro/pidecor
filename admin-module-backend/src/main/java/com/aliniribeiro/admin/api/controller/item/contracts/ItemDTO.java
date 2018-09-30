package com.aliniribeiro.admin.api.controller.item.contracts;

import java.util.UUID;

public class ItemDTO {

    /**
     * Identificador único do Item
     */
    private UUID id;

    /**
     * Nome do Item
     */
    public String name;

    /**
     * Status do Item
     */
    public String status;

    /**
     * Data de criação do Item
     */
    public String date;

    /**
     * Id do fornecedor do Item
     */
    public UUID provider;

    /**
     * Link da foto do item
     */
    public String photoLink;

    /**
     * Preço do Item
     */
    public Double price;

    /**
     * Identificador único do pedido em que o item está vínculado
     */
    public UUID customerOrderId;
}

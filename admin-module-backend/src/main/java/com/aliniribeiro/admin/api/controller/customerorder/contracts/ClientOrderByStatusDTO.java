package com.aliniribeiro.admin.api.controller.customerorder.contracts;


public class ClientOrderByStatusDTO {

    /**
     * Status do pedido
     */
    public String status;

    /**
     * Quantidade de itens que estão com este status
     */
    public Integer amount;
}

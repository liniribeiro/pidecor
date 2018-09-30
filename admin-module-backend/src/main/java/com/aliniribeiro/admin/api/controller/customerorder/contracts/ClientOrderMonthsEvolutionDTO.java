package com.aliniribeiro.admin.api.controller.customerorder.contracts;

public class ClientOrderMonthsEvolutionDTO {

    /**
     * Mês a qual está sendo realizada a busca.
     */
    public String month;

    /**
     * Quantidade de pedidos criados no mês.
     */
    public Long ordersAmmount;

}

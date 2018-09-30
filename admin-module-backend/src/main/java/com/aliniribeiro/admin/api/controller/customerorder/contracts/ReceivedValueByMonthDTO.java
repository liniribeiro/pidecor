package com.aliniribeiro.admin.api.controller.customerorder.contracts;

public class ReceivedValueByMonthDTO {

    /**
     * Mês a qual está sendo realizada a busca.
     */
    public String month;

    /**
     * Massa mensal de pagamentos recebidos pelos itens vendidos dos fornecedores
     */
    public Double receivedValue;

}

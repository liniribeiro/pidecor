package com.aliniribeiro.admin.api.controller.monitoringreport.contracts;

public class MonitoringReportResponseDTO {

    /**
     * Número do objeto na lista
     */
    public int index;

    /**
     * Nome
     */
    public String name;

    /**
     * Nome da pessoa que recebe o pedido
     */
    public String receiverName;

    /**
     * Endere;o de entrega do pedido
     */
    public String deliveryAddress;

    /**
     * Telefone do Fornecedor.
     */
    public String phone;

    /**
     * Email do Fornecedor.
     */
    public String email;

    /**
     * Quantidade de clientes que o Fornecedor possui.
     */
    public Integer qtd_clients;

    /**
     * Reputação do Fornecedor na API de Terceiros (API de fornecedores).
     */
    public String reputation;

    /**
     * Categoria do Fornecedor.
     */
    public String category;

    /**
     * Quantidade de itens disponíveis que o Fornecedor possui na API de Terceiros (API de fornecedores).
     */
    public String qtd_items;

    /**
     * Status do Fornecedor.
     */
    public String status;

    /**
     * Porcentagem que o Fornecedor paga para a loja.
     */
    public Double percentage;

    /**
     * Média de dias para a entrega do produto do Fornecedor.
     */
    public String delivery_days;

    /**
     * Taxa de rejeição dos produtos do Fornecedor.
     */
    public String rejection_rate;

    /**
     * Quantidade de itens já vendidos pelo Fornecedor.
     */
    public String qtd_items_sold;

    /**
     * Data
     */
    public String date;

    /**
     * Nome do fornecedor
     */
    public String providerName;

    /**
     * Valor do Item
     */
    public String price;

    /**
     * Perfil do usuário
     */
    public String profile;

}

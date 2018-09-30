package com.aliniribeiro.admin.api.controller.provider;

public class ProviderDTO {

    /**
     * Identificador único do fornecedor.
     */
    public String id;

    /**
     * Id do Fornecedor na API de Terceiros(API de fornecedores).
     */
    public String providerAPIID;

    /**
     * Nome do Fornecedor.
     */
    public String name;

    /**
     * Razão social do Fornecedor.
     */
    public String social_name;

    /**
     * CNPJ do Fornecedor.
     */
    public String cnpj;

    /**
     * Endereço do Fornecedor.
     */
    public String address;

    /**
     * Cidade do Fornecedor.
     */
    public String city;

    /**
     * Telefone do Fornecedor.
     */
    public String phone;

    /**
     * Site do Fornecedor.
     */
    public String homepage;

    /**
     * Email do Fornecedor.
     */
    public String email;

    /**
     * Tipo de empresa do Fornecedor.
     */
    public String companyType;

    /**
     * Faturamento médio anual do Fornecedor.
     */
    public String annual_billing;

    /**
     * Ano de fundação da empresa do Fornecedor.
     */
    public Integer foundation_year;

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
    public Integer qtd_items;

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
    public Integer delivery_days;

    /**
     * Taxa de rejeição dos produtos do Fornecedor.
     */
    public Double rejection_rate;

    /**
     * Quantidade de itens já vendidos pelo Fornecedor.
     */
    public Integer qtd_items_sold;


}

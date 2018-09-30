package com.aliniribeiro.admin.api.model.customerorder.enums;

public enum OrderStatus {


    GENERATED_REQUEST(0, "Gerado"),
    PAYMENT_AUTHORIZATION(1, "Autorização de pagamento"),
    SEPARATION_ORDER(2, "Em separação"),
    TRANSPORTATION_ORDER(3, "Em transporte"),
    DELIVERED_ORDER(4, "Entregue"),
    CANCELED_ORDER(5, "Cancelado");

    private int ordinal;
    public String description;

    OrderStatus(int ordinal, String description) {
        this.ordinal = ordinal;
        this.description = description;
    }

    public static String getDecription(String reputation) {
        switch (reputation) {
            case "GENERATED_REQUEST":
                return GENERATED_REQUEST.description;
            case "PAYMENT_AUTHORIZATION":
                return PAYMENT_AUTHORIZATION.description;
            case "SEPARATION_ORDER":
                return SEPARATION_ORDER.description;
            case "TRANSPORTATION_ORDER":
                return TRANSPORTATION_ORDER.description;
            case "DELIVERED_ORDER":
                return DELIVERED_ORDER.description;
            default:
                return CANCELED_ORDER.description;
        }
    }

    public static OrderStatus getOrderStatus(String status) {
        switch (status) {
            case "GENERATED_REQUEST":
                return GENERATED_REQUEST;
            case "PAYMENT_AUTHORIZATION":
                return PAYMENT_AUTHORIZATION;
            case "SEPARATION_ORDER":
                return SEPARATION_ORDER;
            case "TRANSPORTATION_ORDER":
                return TRANSPORTATION_ORDER;
            case "DELIVERED_ORDER":
                return DELIVERED_ORDER;
            default:
                return CANCELED_ORDER;
        }
    }

}

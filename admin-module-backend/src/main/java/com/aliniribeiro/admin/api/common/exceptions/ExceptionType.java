package com.aliniribeiro.admin.api.common.exceptions;

public enum ExceptionType {

    /***/
    EMAIL_ALREADY_EXISTS(0, "%s not found with id %s ."),
    /***/
    ENTITY_NOT_FOUND(1, "%s not found with id %s ."),
    /***/
    EMAIL_NOT_FOUND(2, "Email is required."),
    /***/
    MISSING_INFORMATIONS(3, "Missing informations to create a new Client!."),

    MISSING_INFORMATIONS_ORDER(4, "Missing informations to create a new customer order!."),
    /***/
    CLIENT_NOT_FOUND(5, "Client not found."),
    /***/
    NEW_ORDER_ERROR(6, "Error creating a new Order."),

    BI_ERROR(7, "Error  sending BI informations.");

    private int cod;
    private String description;

    private ExceptionType(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static ExceptionType toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (ExceptionType x : ExceptionType.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid ID" + cod);

    }

}

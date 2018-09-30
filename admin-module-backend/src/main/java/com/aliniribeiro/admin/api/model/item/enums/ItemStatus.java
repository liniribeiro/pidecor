package com.aliniribeiro.admin.api.model.item.enums;

public enum ItemStatus {

    AVALIABLE(0, "Disponível"),
    UNAVALIABLE(1, "Indisponível");

    private int ordinal;
    private String description;

    ItemStatus(int ordinal, String description) {
        this.ordinal = ordinal;
        this.description = description;
    }

    public static String getDecription(String reputation) {
        switch (reputation) {
            case "AVALIABLE":
                return UNAVALIABLE.description;
            case "UNAVALIABLE":
                return UNAVALIABLE.description;

            default:
                return UNAVALIABLE.description;
        }
    }

    public static ItemStatus getStatus(String status) {
        switch (status) {
            case "AVALIABLE":
                return AVALIABLE;
            case "UNAVALIABLE":
                return UNAVALIABLE;
            default:
                return UNAVALIABLE;
        }
    }
}

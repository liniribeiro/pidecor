package com.aliniribeiro.admin.api.model.provider.enums;

public enum ProviderStatus {

    ACTIVE(0, "Ativo"),
    INACTIVE(1, "Inativo");

    private int ordinal;
    private String description;

    ProviderStatus(int ordinal, String description) {
        this.ordinal = ordinal;
        this.description = description;
    }

    public static ProviderStatus getStatus(String status) {
        switch (status) {
            case "ACTIVE":
                return ACTIVE;
            default:
                return INACTIVE;
        }
    }

    public static String getDecription(String reputation) {
        switch (reputation) {
            case "ACTIVE":
                return ACTIVE.description;
            default:
                return INACTIVE.description;
        }
    }
}

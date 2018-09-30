package com.aliniribeiro.admin.api.model.provider.enums;

public enum ProviderCategory {

    NONE(0, "Sem categoria"),
    FORNITURE(1, "Móveis"),
    LIGHTS(2, "Iluminação"),
    PILLOW(3, "Almofadas");

    private int ordinal;
    private String description;

    ProviderCategory(int ordinal, String description) {
        this.ordinal = ordinal;
        this.description = description;
    }

    public static String getDscription(String reputation) {
        switch (reputation) {
            case "NONE":
                return NONE.description;
            case "FORNITURE":
                return FORNITURE.description;
            case "LIGHTS":
                return LIGHTS.description;
            default:
                return NONE.description;
        }
    }

    public static ProviderCategory getCategory(String category) {
        switch (category) {
            case "FORNITURE":
                return FORNITURE;
            case "LIGHTS":
                return LIGHTS;
            case "PILLOW":
                return PILLOW;
            default:
                return NONE;
        }
    }
}

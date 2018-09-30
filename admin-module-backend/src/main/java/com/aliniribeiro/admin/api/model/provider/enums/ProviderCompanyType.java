package com.aliniribeiro.admin.api.model.provider.enums;

public enum ProviderCompanyType {

    NONE(0, ""),
    MEI(1, "MEI"),
    MICRO(2, "Micro"),
    SMALL(3, "Pequeno"),
    MEDIUM(4, "Médio"),
    MULTINATIONAL(4, "Multinacional");

    private int ordinal;
    private String description;

    ProviderCompanyType(int ordinal, String description) {
        this.ordinal = ordinal;
        this.description = description;
    }

    public static String getDscription(String reputation) {
        switch (reputation) {
            case "MEI":
                return MEI.description;
            case "MICRO":
                return MICRO.description;
            case "SMALL":
                return SMALL.description;
            case "MEDIUM":
                return MEDIUM.description;
            case "MULTINATIONAL":
                return MULTINATIONAL.description;
            default:
                return NONE.description;
        }
    }

    public static ProviderCompanyType getCompanyType(String type) {
        switch (type) {
            case "MEI":
                return MEI;
            case "MICRO":
                return MICRO;
            case "SMALL":
                return SMALL;
            case "MEDIUM":
                return MEDIUM;
            case "MULTINATIONAL":
                return MULTINATIONAL;
            default:
                return NONE;
        }
    }

}

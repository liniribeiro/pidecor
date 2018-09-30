package com.aliniribeiro.admin.api.model.provider.enums;

public enum ProviderReputation {


    NOT_EVALUATED(0, "Não Avaliado"),
    BAD(1, "Ruim"),
    GOOD(2, "Bom"),
    VERY_GOOD(3, "Muito bom");


    private int ordinal;
    private String description;

    ProviderReputation(int ordinal, String description) {
        this.ordinal = ordinal;
        this.description = description;
    }

    public static String getDscription(String reputation) {
        switch (reputation) {
            case "BAD":
                return BAD.description;
            case "GOOD":
                return GOOD.description;
            case "VERY_GOOD":
                return VERY_GOOD.description;
            default:
                return NOT_EVALUATED.description;
        }
    }


    public static ProviderReputation getReputation(String reputation) {
        switch (reputation) {
            case "BAD":
                return BAD;
            case "GOOD":
                return GOOD;
            case "VERY_GOOD":
                return VERY_GOOD;
            default:
                return NOT_EVALUATED;
        }
    }

}

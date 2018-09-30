package com.aliniribeiro.admin.api.model.provider.enums;

public enum ProviderAnnualBilling {

    NONE(0, "Não Avaliado"),
    UNTILL_100_MIL(1, "Até R$ 1 Mil"),
    BETWEEN_1_10_MILLION(2, "Entre R$ 1 Milhão e R$ 10 Milhões"),
    BETWEEN_10_100_MILLION(3, "Entre R$ 10 Milhões e R$ 100 Milhões"),
    OVER_100_MILLION(4, "Acima de R$ 100 Milhões");


    private int ordinal;
    private String description;

    ProviderAnnualBilling(int ordinal, String description) {
        this.ordinal = ordinal;
        this.description = description;
    }

    public static String getDscription(String annualBilling) {
        switch (annualBilling) {
            case "UNTILL_100_MIL":
                return UNTILL_100_MIL.description;
            case "BETWEEN_1_10_MILLION":
                return BETWEEN_1_10_MILLION.description;
            case "BETWEEN_10_100_MILLION":
                return BETWEEN_10_100_MILLION.description;
            case "OVER_100_MILLION":
                return OVER_100_MILLION.description;
            default:
                return NONE.description;
        }
    }


    public static ProviderAnnualBilling getAnualBilling(String annualBilling) {
        switch (annualBilling) {
            case "UNTILL_100_MIL":
                return UNTILL_100_MIL;
            case "BETWEEN_1_10_MILLION":
                return BETWEEN_1_10_MILLION;
            case "BETWEEN_10_100_MILLION":
                return BETWEEN_10_100_MILLION;
            case "OVER_100_MILLION":
                return OVER_100_MILLION;
            default:
                return NONE;
        }
    }

}

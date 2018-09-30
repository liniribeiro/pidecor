package com.aliniribeiro.admin.api.model.user.enums;

public enum UserProfile {

    ROLE_ADMIN("Administrador do sistema"),
    ROLE_CUSTOMER("Cliente"),
    ROLE_HELP_DESK("Técnico de atendimento ao cliente"),
    ROLE_COMERCIAL_ANALYST("Analista comercial"),
    ROLE_MARKETING_ANALYST("Analista de Marketing");


    private String description;

    UserProfile(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }


}

package com.aliniribeiro.admin.api.controller.user.contracts;

import com.aliniribeiro.admin.api.model.user.enums.UserProfile;

public class UserByProfileDTO {

    /**
     * Nome do profile
     */
    public UserProfile profileName;

    /**
     * Quantidade de usuários
     */
    public Long users;

}

package com.aliniribeiro.admin.api.common.security.model;

import com.aliniribeiro.admin.api.model.user.UserEntity;

public class CurrentUser {

    private String token;
    private UserEntity user;

    public CurrentUser(String token, UserEntity user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

package com.aliniribeiro.admin.api.controller.user;

import com.aliniribeiro.admin.api.controller.user.contracts.UserByProfileDTO;
import com.aliniribeiro.admin.api.controller.user.contracts.UserDTO;
import com.aliniribeiro.admin.api.model.user.UserEntity;
import com.aliniribeiro.admin.api.model.user.enums.UserProfile;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(UserEntity user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.email = user.getEmail();
        dto.name = user.getName();
        dto.role = user.getProfile().toString();
        dto.roleDescription = user.getProfile().getDescription();
        dto.id = user.getId().toString();
        return dto;
    }

    public UserByProfileDTO toUserByProfileDTO(Tuple tuple) {
        UserByProfileDTO dto = new UserByProfileDTO();
        dto.profileName = tuple.get(0, UserProfile.class);
        dto.users =  tuple.get(1, Long.class);
        return dto;
    }

    public UserEntity toEntity(UserDTO user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.email);
        entity.setName(user.name);
        entity.setProfile(getRole(user.role));
        entity.setPassword(user.password);
        return entity;
    }


    /**
     * Método que converte a string recebida para UserProfile
     *
     * @param role
     * @return
     */
    public UserProfile getRole(String role) {
        if (role.equalsIgnoreCase("ROLE_ADMIN")) {
            return UserProfile.ROLE_ADMIN;
        } else if (role.equalsIgnoreCase("ROLE_CUSTOMER")) {
            return UserProfile.ROLE_CUSTOMER;
        } else if (role.equalsIgnoreCase("ROLE_HELP_DESK")) {
            return UserProfile.ROLE_HELP_DESK;
        } else if (role.equalsIgnoreCase("ROLE_COMERCIAL_ANALYST")) {
            return UserProfile.ROLE_COMERCIAL_ANALYST;
        } else if (role.equalsIgnoreCase("ROLE_MARKETING_ANALYST")) {
            return UserProfile.ROLE_MARKETING_ANALYST;
        }
        return UserProfile.ROLE_CUSTOMER;
    }


}

package com.aliniribeiro.admin.api.controller.user;

import com.aliniribeiro.admin.api.common.exceptions.ExceptionType;
import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.controller.user.contracts.UserByProfileDTO;
import com.aliniribeiro.admin.api.controller.user.contracts.UserDTO;
import com.aliniribeiro.admin.api.model.user.UserEntity;
import com.aliniribeiro.admin.api.model.user.enums.UserProfile;
import com.aliniribeiro.admin.api.model.user.UserRepository;
import com.querydsl.core.Tuple;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserService {

    /**
     * Método que cria um novo usuário.
     *
     * @param email    email do usuário que será criado.
     * @param password senha do usuário que será criado.
     * @param name     nome do usuário que será criado.
     * @param profile  perfil do usuário que será criado.
     * @return UserEntity do usuário criado.
     */
    public UserEntity createUser(String email, String password, String name, UserProfile profile) {

        Optional<UserEntity> userEntity = Spring.bean(UserRepository.class).findByEmail(email);
        if (userEntity.isPresent()) {
            throw new IllegalArgumentException(ExceptionType.EMAIL_ALREADY_EXISTS.name());
        }
        UserEntity user = new UserEntity();
        user.setPassword(Spring.bean(PasswordEncoder.class).encode(password));
        user.setEmail(email);
        user.setProfile(profile);
        user.setName(name);
        user.setActivatedDate(LocalDate.now());
        validateUser(user);
        Spring.bean(UserRepository.class).save(user);
        return user;
    }

    public void deleteUser(UserEntity user) {
        Spring.bean(UserRepository.class).delete(user);
    }

    public void validateUser(UserEntity user) {
        if (user.getEmail() == null) {
            throw new IllegalArgumentException(ExceptionType.EMAIL_NOT_FOUND.name());
        }
    }

    /**
     * Método que busca uma lista  com o total de usuários por perfil
     *
     * @return lista  com o total de usuários por perfil.
     */
    public List<UserByProfileDTO> getTotalByProfile() {
        List<Tuple> entityUsers = Spring.bean(UserRepository.class).getTotalByProfile();
        List<UserByProfileDTO> profiles = entityUsers.stream().map(e -> Spring.bean(UserMapper.class).toUserByProfileDTO(e)).collect(Collectors.toList());
        return profiles;
    }

    /**
     * Método que retorna todos os usuários.
     *
     * @return lista todos os usuários.
     */
    public List<UserDTO> findAllUsers() {
        List<UserEntity> entityUsers = Spring.bean(UserRepository.class).findAll();
        List<UserDTO> users = entityUsers.stream().map(e -> Spring.bean(UserMapper.class).toDTO(e)).collect(Collectors.toList());
        return users;
    }

    /**
     * Método que busca um usuário pelo seu ID.
     *
     * @return Usuário encontrado.
     */
    public Optional<UserDTO> findById(String id) {
        UserEntity user = Spring.bean(UserRepository.class).findOne(id);
        return Optional.ofNullable(Spring.bean(UserMapper.class).toDTO(user));
    }

    /**
     * Método que retorna o total de usuários cadastrados no sistema.
     *
     * @return Optional copm o total de usuários cadastrados no sistema.
     */
    public Optional<String> getTotalUsers() {
        Long totalUsers = Spring.bean(UserRepository.class).count();
        return Optional.ofNullable(totalUsers.toString());
    }



}

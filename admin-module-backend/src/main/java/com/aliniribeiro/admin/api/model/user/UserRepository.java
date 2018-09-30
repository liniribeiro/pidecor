package com.aliniribeiro.admin.api.model.user;

import com.aliniribeiro.admin.api.model.common.RepositoryBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends RepositoryBase<UserEntity>,  UserRepositoryCustom {

    Optional<UserEntity> findByEmail(String email);

    Page<UserEntity> findAll(Pageable pageable);

    List<UserEntity> findAll();

    UserEntity findById(UUID id);
}
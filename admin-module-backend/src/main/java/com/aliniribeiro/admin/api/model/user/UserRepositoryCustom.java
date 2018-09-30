package com.aliniribeiro.admin.api.model.user;


import com.querydsl.core.Tuple;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {

    /**
     * Métofo que retorna a quantidade de usuários de acordo com seu tipo de profile
     * @return
     */
    List<Tuple> getTotalByProfile();

    Optional<List<UserEntity>> findBetweenDates(LocalDate startDate, LocalDate endDate);
}

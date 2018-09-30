package com.aliniribeiro.admin.api.model.user;

import com.aliniribeiro.admin.api.model.common.RepositoryBaseImpl;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl extends RepositoryBaseImpl implements UserRepositoryCustom {


    @Override
    public List<Tuple> getTotalByProfile() {
        QUserEntity user = QUserEntity.userEntity;
        JPAQuery<Tuple> userQuery = select(user.profile, user.count()).from(user);
        userQuery.groupBy(user.profile);
        return userQuery.fetch();
    }

    @Override
    public Optional<List<UserEntity>> findBetweenDates(LocalDate startDate, LocalDate endDate) {
        QUserEntity user = QUserEntity.userEntity;
        JPAQuery<UserEntity> query = select(user).from(user);
        query.where(user.activatedDate.after(startDate).and(user.activatedDate.before(endDate)));
        query.orderBy(user.name.asc());
        return Optional.ofNullable(query.fetch());
    }
}

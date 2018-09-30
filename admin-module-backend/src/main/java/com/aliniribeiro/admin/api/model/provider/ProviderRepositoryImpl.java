package com.aliniribeiro.admin.api.model.provider;

import com.aliniribeiro.admin.api.model.common.RepositoryBaseImpl;
import com.querydsl.jpa.impl.JPAQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProviderRepositoryImpl extends RepositoryBaseImpl implements ProviderRepositoryCustom {

    @Override
    public UUID findByProviderAPIId(String id) {
        QProviderEntity provider = QProviderEntity.providerEntity;
        JPAQuery<UUID> query = select(provider.id).from(provider);
        query.where(provider.providersAPIId.eq(id));
        return query.fetchFirst();
    }

    @Override
    public Optional<List<ProviderEntity>> findBetweenDates(LocalDate startDate, LocalDate endDate) {
        QProviderEntity provider = QProviderEntity.providerEntity;
        JPAQuery<ProviderEntity> query = select(provider).from(provider);
        query.where(provider.activatedDate.after(startDate).and(provider.activatedDate.before(endDate)));
        query.orderBy(provider.name.asc());
        return Optional.ofNullable(query.fetch());
    }
}

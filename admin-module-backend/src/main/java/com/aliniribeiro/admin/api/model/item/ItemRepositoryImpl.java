package com.aliniribeiro.admin.api.model.item;

import com.aliniribeiro.admin.api.model.common.RepositoryBaseImpl;
import com.querydsl.jpa.impl.JPAQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ItemRepositoryImpl extends RepositoryBaseImpl implements ItemRepositoryCustom {


    @Override
    public Optional<List<ItemEntity>> findBetweenDates(LocalDate startDate, LocalDate endDate) {
        QItemEntity item = QItemEntity.itemEntity;
        JPAQuery<ItemEntity> query = select(item).from(item);
        query.where(item.date.after(startDate).and(item.date.before(endDate)));
        query.orderBy(item.name.asc());
        return Optional.ofNullable(query.fetch());
    }
}

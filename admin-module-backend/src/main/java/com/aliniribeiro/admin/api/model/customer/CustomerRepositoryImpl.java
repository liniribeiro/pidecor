package com.aliniribeiro.admin.api.model.customer;

import com.aliniribeiro.admin.api.model.common.RepositoryBaseImpl;
import com.querydsl.jpa.impl.JPAQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CustomerRepositoryImpl extends RepositoryBaseImpl implements CustomerRepositoryCustom {


    @Override
    public Optional<List<CustomerEntity>> findBetweenDates(LocalDate startDate, LocalDate endDate) {
        QCustomerEntity customer = QCustomerEntity.customerEntity;
        JPAQuery<CustomerEntity> query = select(customer).from(customer);
        query.where(customer.creationDate.before(endDate).and(customer.creationDate.after(startDate)));
        query.orderBy(customer.name.asc());
        return Optional.ofNullable(query.fetch());
    }
}

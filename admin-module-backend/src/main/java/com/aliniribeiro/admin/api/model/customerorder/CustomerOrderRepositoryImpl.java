package com.aliniribeiro.admin.api.model.customerorder;

import com.aliniribeiro.admin.api.model.common.RepositoryBaseImpl;
import com.aliniribeiro.admin.api.model.customerorder.enums.OrderStatus;
import com.aliniribeiro.admin.api.model.item.ItemEntity;
import com.aliniribeiro.admin.api.model.item.QItemEntity;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public class CustomerOrderRepositoryImpl extends RepositoryBaseImpl implements CustomerOrderRepositoryCustom {

    @Override
    public Optional<List<Tuple>> findAllByStatus() {
        QCustomerOrderEntity order = QCustomerOrderEntity.customerOrderEntity;
        JPAQuery<Tuple> query = select(order.status, order.count()).from(order);
        query.groupBy(order.status);
        return Optional.ofNullable(query.fetch());
    }

    @Override
    public Optional<Long> getOrderEvolutionValue(YearMonth month) {
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();

        QCustomerOrderEntity order = QCustomerOrderEntity.customerOrderEntity;
        JPAQuery<CustomerOrderEntity> query = select(order).from(order);
        query.where(order.creationDate.before(endDate).and(order.creationDate.after(startDate)));
        return Optional.ofNullable(query.fetchCount());
    }

    @Override
    public Optional<List<ItemEntity>> getItemsSoldByMonth(YearMonth month) {
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();
        QCustomerOrderEntity order = QCustomerOrderEntity.customerOrderEntity;
        QItemEntity item = QItemEntity.itemEntity;
        JPAQuery<ItemEntity> query = select(item).from(item);
        query.join(item.customerOrder, order);
        query.where(order.creationDate.before(endDate).and(order.creationDate.after(startDate)));
        return Optional.ofNullable(query.fetch());
    }

    @Override
    public Optional<List<CustomerOrderEntity>> findBetweenDates(LocalDate startDate, LocalDate endDate) {
        QCustomerOrderEntity order = QCustomerOrderEntity.customerOrderEntity;
        JPAQuery<CustomerOrderEntity> query = select(order).from(order);
        query.where(order.creationDate.before(endDate).and(order.creationDate.after(startDate)));
        query.orderBy(order.deliveryReceiverName.asc());
        return Optional.ofNullable(query.fetch());
    }

    @Override
    public Optional<List<CustomerOrderEntity>> findNotDeliveredOrders() {
        QCustomerOrderEntity order = QCustomerOrderEntity.customerOrderEntity;
        JPAQuery<CustomerOrderEntity> query = select(order).from(order);
        query.where(order.status.notIn(OrderStatus.DELIVERED_ORDER, OrderStatus.CANCELED_ORDER));
        query.orderBy(order.deliveryReceiverName.asc());
        return Optional.ofNullable(query.fetch());
    }

}

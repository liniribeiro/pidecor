package com.aliniribeiro.admin.api.model.common;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class RepositoryBaseImpl {

    @PersistenceContext
    private EntityManager ctx;

    public <U> JPAQuery<U> select(Expression<U> expr) {
        return new JPAQuery<>(ctx).select(expr);
    }

    public JPAQuery<Tuple> select(Expression<?>... exprs) {
        return new JPAQuery<>(ctx).select(exprs);
    }

}

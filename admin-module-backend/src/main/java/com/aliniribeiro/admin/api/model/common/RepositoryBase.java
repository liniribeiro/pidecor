package com.aliniribeiro.admin.api.model.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.ArrayList;
import java.util.List;

@NoRepositoryBean
public interface RepositoryBase<Entity> extends JpaRepository<Entity, String>, QueryDslPredicateExecutor<Entity> {
//
//    @Override
//    default <S extends Entity> S save(S entity) {
//        PropertyAccessor accessor = PropertyAccessorFactory.forDirectFieldAccess(entity);
//        if (accessor.isWritableProperty("Id")) {
//            String id = (String) accessor.getPropertyValue("Id");
//            if (Strings.isNullOrEmpty(id)){
//                accessor.setPropertyValue("id", RandomUUID.uuid());
//            }
//        }
//
//        return saveAndFlush(entity);
//    }

    @Override
    default <S extends Entity> List<S> save(Iterable<S> entities) {
        List<S> persistedEntities = new ArrayList<>();
        entities.forEach(entity -> persistedEntities.add(this.save(entity)));
        return persistedEntities;
    }

    @Override
    default void delete(Iterable<? extends Entity> iterable) {
        this.delete(iterable);
    }
}

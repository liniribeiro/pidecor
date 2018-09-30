package com.aliniribeiro.admin.api.common;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import java.util.UUID;

public class RandomUUID {
    /**
     * Retorna um hash Id com 32 caracteres.
     *
     * @return Id
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * Retorna um hash Id com 32 caracteres caso o Id informado seja nulo.
     *
     * @param uuid Id da entidade.
     * @return Id
     */
    public static String uuid(String uuid) {
        return (uuid == null) ? uuid() : uuid;
    }

    /**
     * Gera id para um objeto que tem uma propriedade ID
     *
     * @param entity Entidade.
     * @return T entity
     */
    public static <T> T uuid(T entity) {
        PropertyAccessor accessor = PropertyAccessorFactory.forDirectFieldAccess(entity);
        accessor.setPropertyValue("id", RandomUUID.uuid());
        return entity;
    }

}

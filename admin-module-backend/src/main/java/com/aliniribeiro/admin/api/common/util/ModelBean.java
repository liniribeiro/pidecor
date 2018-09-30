package com.aliniribeiro.admin.api.common.util;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ModelBean {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Retorna uma bean do spring.
     *
     * @param bean Bean spring
     * @return {@link T}
     */
    public <T> T get(Class<T> bean) {
        try {
            return applicationContext.getBean(bean);
        } catch (Throwable e) {
            throw new ServiceException(String.format("Bean '%s' not found.", bean.getSimpleName()));
        }
    }
}
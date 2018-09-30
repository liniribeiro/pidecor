package com.aliniribeiro.admin.api.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Spring implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T bean(Class<T> clazz, Object... args) {
        if (applicationContext == null) throw new IllegalStateException();

        return applicationContext.getBean(clazz, args);
    }

    public static <T> T bean(String name, Object... args) {
        if (applicationContext == null) {
            throw new IllegalStateException();
        }
        try {
            return (T) applicationContext.getBean(name, args);
        } catch (BeansException e) {
            return null;
        }
    }
}
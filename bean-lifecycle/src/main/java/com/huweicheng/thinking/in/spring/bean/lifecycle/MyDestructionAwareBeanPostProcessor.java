package com.huweicheng.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setNumber("6");
            System.out.println("MyDestructionAwareBeanPostProcessor" + userHolder.getNumber());
        }
        System.out.println(bean);
        System.out.println(bean.getClass());
        System.out.println(UserHolder.class);
    }
}

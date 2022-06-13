package com.huweicheng.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.InitializingBean;

public class DefaultUserFactory implements UserFactory, InitializingBean {
    //@PostConstruct
    public void init() {
        System.out.println("@PostConstruct初始化");
    }

    public void initUserFactory(){
        System.out.println("自定义初始化");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet初始化");
    }
}

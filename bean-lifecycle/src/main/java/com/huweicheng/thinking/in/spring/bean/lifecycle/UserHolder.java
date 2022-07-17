package com.huweicheng.thinking.in.spring.bean.lifecycle;

import com.huweicheng.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

public class UserHolder implements BeanNameAware, InitializingBean, SmartInitializingSingleton {

    private final User user = null;

    private String number;

    private String beanName;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.number = "3";
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.number = "5";
    }

    protected void finalize() throws Throwable {
        System.out.println("finalize...");
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number='" + number + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}

package com.huweicheng.thinking.in.spring.bean.definition;

import com.huweicheng.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean初始化
 */
public class BeanCreationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-creation-context.xml");
        User userStatic = beanFactory.getBean("user-by-static-method", User.class);
        User userInstance = beanFactory.getBean("user-by-instance-method", User.class);
        User userFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(userStatic);
        System.out.println(userInstance);
        System.out.println(userStatic == userInstance);
        System.out.println(userFactoryBean);
    }
}

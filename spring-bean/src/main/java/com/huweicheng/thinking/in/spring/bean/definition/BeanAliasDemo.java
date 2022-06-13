package com.huweicheng.thinking.in.spring.bean.definition;

import com.huweicheng.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean别名实例
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        User huweicheng = (User) beanFactory.getBean("huweicheng", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user == huweicheng);
        System.out.println(beanFactory);
    }
}

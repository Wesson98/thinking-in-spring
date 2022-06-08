package com.huweicheng.thinking.in.spring.ioc.overview.dependency.lookup;

import com.huweicheng.thinking.in.spring.ioc.overview.annotation.Super;
import com.huweicheng.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找案例
 *
 * @author Wesson
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        //配置xml配置文件
        //启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupInLazy(beanFactory);
        lookupInRealTime(beanFactory);
        lookupByType(beanFactory);
        lookupCollectionByType(beanFactory);
        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println(userMap);
        }

    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println(userMap);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找" + user);
    }

}

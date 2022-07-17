package com.huweicheng.thinking.in.spring.ioc.overview.dependency.injection;

import com.huweicheng.thinking.in.spring.ioc.overview.domain.User;
import com.huweicheng.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectionDemo {
    public static void main(String[] args) {
        //配置xml配置文件
        //启动spring应用上下文
        AbstractApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");


        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        ObjectProvider<UserRepository> userRepositories = beanFactory.getBeanProvider(UserRepository.class);
        System.out.println(userRepositories);
        System.out.println(userRepositories.getObject());

        System.out.println(userRepository.getBeanFactory());
        System.out.println(beanFactory);
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        ObjectFactory userFactory = userRepository.getUserObjectFactory();
        System.out.println(userFactory.getObject());

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencyInjectionDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }
}

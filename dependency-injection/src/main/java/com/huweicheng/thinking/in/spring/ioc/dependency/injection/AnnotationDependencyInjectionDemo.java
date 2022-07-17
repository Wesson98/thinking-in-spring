package com.huweicheng.thinking.in.spring.ioc.dependency.injection;

import com.huweicheng.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    private Map<String, User> userMap;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        applicationContext.refresh();
        AnnotationDependencyInjectionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionDemo.class);
        System.out.println(demo.user);
        applicationContext.close();
    }
}

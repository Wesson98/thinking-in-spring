package com.huweicheng.thinking.in.spring.bean.definition;

import com.huweicheng.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Config.class);//配置类方式注册bean
        registerBeanDefinition(applicationContext,"hu-registry",User.class);
        registerBeanDefinition(applicationContext,User.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBeansOfType(Config.class));
        System.out.println(applicationContext.getBeansOfType(User.class));
        applicationContext.close();
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry, String name, Class<?> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder.addPropertyValue("id", 1L).addPropertyValue("name", "hu");
        beanDefinitionRegistry.registerBeanDefinition(name, beanDefinitionBuilder.getBeanDefinition());
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry, Class<?> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder.addPropertyValue("id", 1L).addPropertyValue("name", "hu");
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), beanDefinitionRegistry);
    }

    @Component
    public static class Config {
        @Bean
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("huweicheng");
            return user;
        }
    }
}

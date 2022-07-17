package com.huweicheng.thinking.in.spring.bean.lifecycle;

import com.huweicheng.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.huweicheng.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        //lookupByProperties(beanFactory);
        //lookupByXml(beanFactory);
        //lookupByAnnotated(beanFactory);
        mergeBeanDefinition(beanFactory);
    }

    private static void mergeBeanDefinition(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        beanDefinitionReader.loadBeanDefinitions(locations);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);
        //构造器注入其实是按照类型注入，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        beanFactory.preInstantiateSingletons();
        beanFactory.destroyBean("userHolder", userHolder);
        System.out.println(userHolder);
        beanFactory.destroySingletons();
        System.gc();
    }

    private static void lookupByAnnotated(DefaultListableBeanFactory beanFactory) {
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        beanDefinitionReader.register(BeanMetadataConfigurationDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionCountAfter - beanDefinitionCountBefore;
        System.out.println("加载了" + beanDefinitionCount + "个Bean");
        BeanMetadataConfigurationDemo demo = beanFactory.getBean("beanMetadataConfigurationDemo", BeanMetadataConfigurationDemo.class);
        System.out.println(demo);
    }

    //读取properties文件的方式获取Bean
    private static void lookupByProperties(DefaultListableBeanFactory beanFactory) {
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "META-INF/user.properties";
        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int nums = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println(nums);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }

    //读取XML文件的方式获取Bean
    private static void lookupByXml(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int nums = beanDefinitionReader.loadBeanDefinitions(location);
        System.out.println(nums);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                return new SuperUser();
            }
            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                User user = (User) bean;
                user.setId(2L);
                user.setName("huweicheng");
                //进行变更处理
                return false;
            }
            //按原有逻辑处理
            return true;
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
                MutablePropertyValues propertyValues = new MutablePropertyValues();
                propertyValues.addPropertyValue("number", "1");
                return propertyValues;
            }
            return pvs;
        }

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
                UserHolder userHolder = new UserHolder();
                userHolder.setNumber("2");
                return userHolder;
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
                UserHolder userHolder = new UserHolder();
                userHolder.setNumber("4");
                return userHolder;
            }
            return bean;
        }
    }
}

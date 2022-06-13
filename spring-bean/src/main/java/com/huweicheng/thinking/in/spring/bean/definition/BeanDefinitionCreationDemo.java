package com.huweicheng.thinking.in.spring.bean.definition;

import com.huweicheng.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * 构建bean(定义bean的元信息)
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        //1、通过 BeanDefinitionBuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //通过属性设置
        beanDefinitionBuilder.addPropertyValue("name", "huweicheng");
        beanDefinitionBuilder.addPropertyValue("id",1);
        //获取 BeanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //BeanDefinition 并非 Bean 最终状态，可以自定义修改

        //2、通过 AbstractBeanDefinition 以及派生类创建
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置Bean类型
        genericBeanDefinition.setBeanClass(User.class);
        //通过 MutablePropertyValues 批量操作属性
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.addPropertyValue("name","huweicheng");
        mutablePropertyValues.addPropertyValue("id",1);
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }

}



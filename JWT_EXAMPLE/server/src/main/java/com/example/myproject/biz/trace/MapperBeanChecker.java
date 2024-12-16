package com.example.myproject.biz.trace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MapperBeanChecker {

    @Autowired
    private ApplicationContext context;

    public void check() {
        boolean beanExists = context.containsBean("userMapper");
        System.out.println("exit Result:"+ beanExists);

    }
}

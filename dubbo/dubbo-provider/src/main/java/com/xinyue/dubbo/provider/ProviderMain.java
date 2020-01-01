package com.xinyue.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderMain {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-provider.xml");
        context.start();
        System.in.read();
    }
}

package com.xinyue.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableZuulProxy
@SpringBootApplication
public class ZuulGatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayMain.class, args);
    }
}

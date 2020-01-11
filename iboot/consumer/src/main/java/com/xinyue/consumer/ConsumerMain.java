package com.xinyue.consumer;

import com.xinyue.ribbon.config.ProviderLBRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


@RibbonClient(name = "IBOOT-PROVIDER", configuration = ProviderLBRuleConfig.class)
@EnableEurekaClient
@SpringBootApplication
public class ConsumerMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain.class, args);
    }
}

package com.xinyue.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.xinyue.consumer.rule.MyCustomizedRule;
import org.springframework.context.annotation.Bean;

public class ProviderLBRuleConfig {

    @Bean
    public IRule iRule() {
        return new MyCustomizedRule();
    }
}


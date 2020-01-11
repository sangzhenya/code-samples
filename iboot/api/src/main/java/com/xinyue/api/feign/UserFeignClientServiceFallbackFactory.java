package com.xinyue.api.feign;

import com.xinyue.model.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientServiceFallbackFactory implements FallbackFactory<UserFeignService> {
    @Override
    public UserFeignService create(Throwable throwable) {
        return new UserFeignService() {
            @Override
            public User getById(Integer id) {
                return new User();
            }
        };
    }

}

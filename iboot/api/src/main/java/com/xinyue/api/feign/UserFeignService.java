package com.xinyue.api.feign;

import com.xinyue.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "IBOOT-PROVIDER", fallbackFactory = UserFeignClientServiceFallbackFactory.class)
public interface UserFeignService {
    @GetMapping("/user/{id}")
    User getById(@PathVariable("id") Integer id);
}

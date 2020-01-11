package com.xinyue.api;

import com.xinyue.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("IBOOT-PROVIDER")
public interface UserClientService {
    @GetMapping("/user/{id}")
    User getById(@PathVariable("id") Integer id);
}

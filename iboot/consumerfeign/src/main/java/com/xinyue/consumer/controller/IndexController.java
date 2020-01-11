package com.xinyue.consumer.controller;

import com.xinyue.api.feign.UserFeignService;
import com.xinyue.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private UserFeignService userFeignService;

    @ResponseBody
    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @ResponseBody
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userFeignService.getById(id);
    }


}

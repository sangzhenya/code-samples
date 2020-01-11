package com.xinyue.consumer.controller;

import com.xinyue.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {
    private static final String REST_URL = "http://IBOOT-PROVIDER";

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @ResponseBody
    @GetMapping("/user")
    public User getUser() {
        return restTemplate.getForObject(REST_URL + "/user", User.class);
    }


}

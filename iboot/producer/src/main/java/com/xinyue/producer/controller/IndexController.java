package com.xinyue.producer.controller;

import com.xinyue.api.service.UserService;
import com.xinyue.model.User;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {
    private Log log = LogFactory.getLog(IndexController.class);
    private final DiscoveryClient client;

    private final UserService userService;

    public IndexController(UserService userService, DiscoveryClient client) {
        this.userService = userService;
        this.client = client;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User index(@PathVariable("id") Integer id) {
        User sUser = userService.getById(id);
        if (sUser == null) {
            throw new RuntimeException("Cannot find user by id:" + id);
        }
        System.out.println(sUser);
        return sUser;
    }


    @GetMapping("/create")
    @ResponseBody
    public String create() {
        log.info("Get from 002 provider.");
        User user = new User();
        user.setName("LinXinyue");
        user.setPassword("123456");
        user.setFirstName("Lin");
        user.setLastName("Xinyue");
        return userService.createUser(user).toString();
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = client.getServices();
        log.info(services);
        List<ServiceInstance> instances = client.getInstances("IBOOT-PROVIDER");
        for (ServiceInstance instance : instances) {
            log.info(instance);
        }
        return this.client;
    }
}

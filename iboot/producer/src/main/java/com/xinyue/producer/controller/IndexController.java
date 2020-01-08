package com.xinyue.producer.controller;

import com.xinyue.model.User;
import com.xinyue.producer.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() {
        User sUser = userService.getById(1);
        System.out.println(sUser);
        return sUser.toString();
    }


    @GetMapping("/create")
    @ResponseBody
    public String create() {
        User user = new User();
        user.setName("LinXinyue");
        user.setPassword("123456");
        user.setFirstName("Lin");
        user.setLastName("Xinyue");
        return userService.createUser(user).toString();
    }
}

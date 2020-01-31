package org.xinyue.xsecurity.controller;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xinyue.xsecurity.dto.User;
import org.xinyue.xsecurity.dto.UserQueryCriteria;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {
    private Log log = LogFactory.getLog(IndexController.class);

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/user")
    public List<User> query(UserQueryCriteria criteria, @PageableDefault(page = 1, size = 7, sort = "name,asc") Pageable pageable) {
        log.info(ReflectionToStringBuilder.toString(criteria, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable);
        List<User> users = new ArrayList<>();
        users.add(new User(1, criteria.getName()));
        users.add(new User(2, criteria.getName()));
        users.add(new User(3, criteria.getName()));
        return users;
    }
}

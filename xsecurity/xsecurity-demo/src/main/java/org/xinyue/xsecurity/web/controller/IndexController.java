package org.xinyue.xsecurity.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xinyue.xsecurity.dto.User;
import org.xinyue.xsecurity.dto.UserQueryCriteria;
import org.xinyue.xsecurity.exception.CustomizedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {
    private static String folder = "/Users/xinyue/temp";
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

    //    @GetMapping("/user/{id:\\d+}")   // 可以通过正则表达式限制类型
    @JsonView(User.UserDetailView.class)
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id) {
        User user = new User();
        user.setId(id);
        user.setName("Xinyue");
        return user;
    }

    @PostMapping("/create/user")
    public User createUser(@RequestBody User user) {
        log.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        return user;
    }

    @PutMapping("/update/user")
    public User updateUser(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(item -> log.error(item.getDefaultMessage()));
        }
        log.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        return user;
    }

    @DeleteMapping("/delete/user/{id}")
    public User deleteUser(@PathVariable("id") int id) {
        if (id < 1) {
            throw new CustomizedException("User not exist", id);
        }
        return new User(1, "Xinyue");
    }

    @PostMapping("/file")
    public String upload(MultipartFile file) throws Exception {
        File localFile = new File(folder, System.currentTimeMillis() + ".txt");
        file.transferTo(localFile);
        return localFile.getAbsolutePath();
    }

    @GetMapping("/file/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + id + ".txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}

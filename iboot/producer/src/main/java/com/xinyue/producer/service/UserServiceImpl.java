package com.xinyue.producer.service;

import com.xinyue.model.User;
import com.xinyue.producer.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Integer id) {
        User byId = userMapper.getById(id);
        User userById = userMapper.getUserById(id);
        System.out.println(byId);
        System.out.println(userById);
        return byId;
    }

    @Override
    public User createUser(User user) {
        userMapper.createUser(user);
        return user;
    }
}

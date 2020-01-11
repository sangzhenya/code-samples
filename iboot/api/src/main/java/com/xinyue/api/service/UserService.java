package com.xinyue.api.service;

import com.xinyue.model.User;

public interface UserService {
    User getById(Integer id);

    User createUser(User user);
}

package com.xinyue.producer.mapper;

import com.xinyue.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    User getUserById(Integer id);

    @Select("select * from user where id = ${id}")
    User getById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert  into user (name, password, first_name, last_name) values (#{name}, #{password}, #{firstName}, #{lastName})")
    void createUser(User user);

}

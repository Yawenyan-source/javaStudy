package com.wen.mapper;

import com.wen.pojo.User;

import java.util.List;

/**
 * @author Wen
 * @date 2022年10月01日 1:25 PM
 */
public interface UserMapper {
    List<User> findAll();

    List<User> findUserByCondition(User user);

    List<User> findByIds(Integer[] ids);
}

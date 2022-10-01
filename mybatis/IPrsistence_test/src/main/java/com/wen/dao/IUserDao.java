package com.wen.dao;

import com.wen.pojo.User;

import java.util.List;

/**
 * @author Wen
 * @date 2022年09月30日 9:49 PM
 */
public interface IUserDao {
    /**
     * 查询所有
     *
     * @return
     */
    List<User> findAll();

    /**
     * 根据条件查询
     *
     * @param user
     * @return
     */
    User findByCondition(User user);
}

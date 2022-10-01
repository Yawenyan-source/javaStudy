package com.wen.mapper;

import com.wen.pojo.User;

import java.util.List;

/**
 * @author Wen
 * @date 2022年10月01日 4:25 PM
 */
public interface IUserMapper {
    //    查询所有用户信息，并且查出每个用户关联的订单信息
    List<User> findUserAndOrder();

    //    查询所有用户、同时查询每个用户关联的角色信息
    List<User> findAllUserAndRole();
}

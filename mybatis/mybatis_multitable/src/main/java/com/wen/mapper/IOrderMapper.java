package com.wen.mapper;

import com.wen.pojo.Order;
import com.wen.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Wen
 * @date 2022年10月01日 3:56 PM
 */
public interface IOrderMapper {
    /**
     * 查询订单的同时还查询该订单所属的用户
     * user字段下的column的uid 传给 com.wen.mapper.IUserMapper.findUserById
     *
     * @return
     */
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "orderTime", column = "ordertime"),
            @Result(property = "total", column = "total"),
            @Result(property = "user", column = "uid",
                    javaType = User.class,
                    one = @One(select = "com.wen.mapper.IUserMapper.findUserById")),
    })
    @Select("select * from zdy_mybatis.orders")
    List<Order> findOrderAndUser();

    @Select("select * from zdy_mybatis.orders where uid = #{uid}")
    List<Order> findOrderByUid(Integer uid);
}

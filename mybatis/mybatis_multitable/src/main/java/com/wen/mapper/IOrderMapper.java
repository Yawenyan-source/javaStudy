package com.wen.mapper;

import com.wen.pojo.Order;

import java.util.List;

/**
 * @author Wen
 * @date 2022年10月01日 3:56 PM
 */
public interface IOrderMapper {
    //    查询订单的同时还查询该订单所属的用户
    List<Order> findOrderAndUser();
}

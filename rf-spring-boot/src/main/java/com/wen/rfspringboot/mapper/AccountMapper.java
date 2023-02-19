package com.wen.rfspringboot.mapper;

import com.wen.rfspringboot.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 测试springboot整合mybatis
 * @since 2023/2/13 20:58:20
 */
@Mapper
public interface AccountMapper {

    @Select("SELECT * from account where name = #{name}")
    Account findByName(String name);

    List<Account> findAll();
}

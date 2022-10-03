package com.wen.mapper;

import com.wen.pojo.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;

/**
 * @author Wen
 * @date 2022年10月01日 4:25 PM
 */
@CacheNamespace(implementation = RedisCache.class) //开启二级缓存
public interface IUserMapper {
    //    查询所有用户信息，并且查出每个用户关联的订单信息
    @Select("select * from zdy_mybatis.user")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "orders", column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.wen.com.wen.mapper.IOrderMapper.findOrderByUid")),
    })
    List<User> findUserAndOrder();

    //    查询所有用户、同时查询每个用户关联的角色信息
    List<User> findAllUserAndRole();

    /**
     * 添加用户
     *
     * @param user
     */
    @Insert("insert into zdy_mybatis.user values (#{id},#{username},#{password},#{birthday});")
    void addUser(User user);

    /**
     * 注解修改用户
     *
     * @param user
     */
    @Update("update zdy_mybatis.user set username = #{username},password = #{password} where id = #{id}")
    void updateUser(User user);

    /**
     * 注解查询用户
     *
     * @return
     */
    @Select("select * from zdy_mybatis.user")
    List<User> selectUser();

    /**
     * 删除用户
     *
     * @param id
     */
    @Delete("delete from zdy_mybatis.user where id = #{id};")
    void deleteUser(Integer id);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Select("select * from zdy_mybatis.user where id = #{id}")
    @Options(useCache = false)
    User findUserById(Integer id);

}

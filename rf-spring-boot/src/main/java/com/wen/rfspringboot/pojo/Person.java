package com.wen.rfspringboot.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.util.Objects;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 测试springboot整合redis
 * @since 2023/2/13 22:28:30
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@RedisHash(value = "persons") //指定实体类在redis中的存储空间
public class Person {
    @Id //标识实体类主键
    private Integer id;
    @Indexed // 用来标识对应属性在redis中生成二级索引
    private String firstname;
    @Indexed
    private String lastname;
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return id != null && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

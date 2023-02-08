package com.wen.pojo;

import javax.persistence.*;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 简历实体类 在类中要使用注解建立实体类和数据表中的映射关系以及属性和字段的关系
 * @since 2022/10/30 13:57:23
 */
@Entity         //实体类
@Table(name = "tb_resume")      //实体类和表映射关系
public class Resume {
    @Id        //主键
    // 主键的生成策略 GenerationType.IDENTITY 主键自增 GenerationType.SEQUENCE 主键按序列
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

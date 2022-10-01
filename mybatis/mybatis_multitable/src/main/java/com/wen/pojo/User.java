package com.wen.pojo;

import java.util.List;

/**
 * @author Wen
 * @date 2022年10月01日 3:50 PM
 * 用户
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String birthday;

    /**
     * 代表当前用户具备哪些订单
     * 一对多的关系
     */
    private List<Order> orders;

    /**
     * 代表当前用户具备哪些角色
     */
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", orders=" + orders +
                //", roles=" + roles +
                '}';
    }
}

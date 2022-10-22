package com.wen.pojo;

import java.io.Serializable;

/**
 * @author Wen
 * @since 2022年10月15日 5:38 PM
 */
public class User implements Serializable  {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

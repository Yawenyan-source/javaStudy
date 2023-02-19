package com.wen.rfspringboot.pojo;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description
 * @since 2023/2/13 22:29:59
 */
@Data
@RedisHash
public class Address {
    @Indexed
    private String city;
    @Indexed
    private String country;
}

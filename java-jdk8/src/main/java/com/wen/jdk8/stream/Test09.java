package com.wen.jdk8.stream;

import java.time.Duration;
import java.time.Instant;

public class Test09 {
    public static void main(String[] args) {
        Instant start = Instant.now();
        long sum = 0;
        for (long i = 0; i < 50000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        //串行流，单线程：五百亿求和花费的时间为18秒
        System.out.println("五百亿求和花费的时间为：" + Duration.between(start, end).toMillis());
    }
}

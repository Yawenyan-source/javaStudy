package com.wen.jdk8.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;
import java.util.stream.LongStream;

public class Test10 {
    public static void main(String[] args) {
        Instant start = Instant.now();
        LongStream longStream = LongStream.rangeClosed(0, 50000000000L);
        OptionalLong result = longStream.parallel().reduce(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left + right;
            }
        });
        System.out.println(result.getAsLong());
        Instant end = Instant.now();
        //并行流，多线程：五百亿求和花费的时间为10315
        //大的任务拆分成多个小的任务同时处理
        System.out.println("五百亿求和花费的时间为：" + Duration.between(start, end).toMillis());
    }
}

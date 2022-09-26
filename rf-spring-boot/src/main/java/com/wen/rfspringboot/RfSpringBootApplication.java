package com.wen.rfspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RfSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RfSpringBootApplication.class, args);

    }

}

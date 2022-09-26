package com.wen.rfspringboot.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Wen
 * @date 2022年09月25日 12:30 PM
 */
@Component
@Slf4j
public class TaskJob01 {
    @Scheduled(cron = "0/5 * * * * ?")
    @Async
    public void task1() {
        log.info("任务1: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }
    @Scheduled(cron = "0/5 * * * * ?")
    @Async
    public void task2() {
        log.info("任务2: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }
}

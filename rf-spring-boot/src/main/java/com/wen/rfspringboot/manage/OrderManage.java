package com.wen.rfspringboot.manage;

import com.wen.rfspringboot.annotation.WenAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderManage {
    @WenAsync
    public void asyncLog() {
        try {
            log.info("目标方法开始执行，正在阻塞三秒时间");
            Thread.sleep(3000);
            log.info("<2>");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

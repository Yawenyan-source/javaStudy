package com.wen.rfspringboot.manage;

import com.wen.rfspringboot.utils.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 手写日框架
 */
@Component
public class LogManage {
    /**
     * 定义一个缓存容器，缓存日志内容
     */
    private BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<String>();
    private LogThread logThread;
    private String filePath = "/Users/wen/Desktop/temp/file.log";

    public LogManage() {
        logThread = new LogThread();
        logThread.start();
    }

    public void info(String log) {
        //将日志缓存起来
        blockingDeque.offer(log);
    }

    class LogThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //缓冲池，批量写入到磁盘中
                String log = blockingDeque.poll();
                if (StringUtils.isEmpty(log)) {
                    continue;
                }
                //继续获取消息
                StringBuilder sbLog = new StringBuilder(log);
                for (int i = 0; i < 9; i++) {
                    String logTemp = blockingDeque.poll();
                    if (StringUtils.isEmpty(logTemp)) {
                        break;
                    }
                    sbLog.append(logTemp);
                }
                String logAppend = sbLog.toString();
                if (!StringUtils.isEmpty(logAppend)) {
                    //再将日志写入到磁盘中
                    FileUtils.writeText(filePath, logAppend, true);
                }
            }
        }
    }

}

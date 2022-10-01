package com.wen.io;

import java.io.InputStream;

/**
 * @author Wen
 * @date 2022年09月27日 10:25 PM
 * 加载资源配置文件
 */
public class Resources {
    /**
     * 根据配置文件的路径，将配置文件加载成字节输入流，存储在内存中
     *
     * @param path
     * @return
     */
    public static InputStream getResourceAsStream(String path) {
        InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return inputStream;
    }
}

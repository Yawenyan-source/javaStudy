package com.wen;

import com.wen.mvcframework.servlet.WenDispatchServlet;
import org.junit.Test;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description
 * @since 2022/10/18 22:18:19
 */
public class TestA {
    @Test
    public void testDoScan(){
        WenDispatchServlet wenDispatchServlet = new WenDispatchServlet();
        wenDispatchServlet.doScan("com.wen");
    }
}

package com.wen.ssm.proxy.dynamicProxy;

/**
 * @author Wen
 * @date 2021年12月22日 9:46 PM
 */
public class RentingHouseImpl implements IRentingHouse {
    @Override
    public void rentingHouse() {
        System.out.println("我想要租一个一室一厅的房子");
    }
}

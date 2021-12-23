package com.wen.ssm.proxy.dynamicProxy;

/**
 * @author Wen
 * @date 2021年12月22日 9:48 PM
 */
public class JdkProxy {
    public static void main(String[] args) {
        //委托对象---委托方
        RentingHouseImpl rentingHouse = new RentingHouseImpl();
        //从代理工厂获取代理对象
        IRentingHouse o = (IRentingHouse) ProxyFactory.getInstance().getJdkProxy(rentingHouse);
        o.rentingHouse();
    }
}

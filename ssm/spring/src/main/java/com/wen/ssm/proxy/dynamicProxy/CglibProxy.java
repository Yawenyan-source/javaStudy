package com.wen.ssm.proxy.dynamicProxy;

/**
 * @author Wen
 * @date 2021年12月22日 10:31 PM
 */
public class CglibProxy {
    public static void main(String[] args) {
        //委托对象
        RentingHouseImpl rentingHouse = new RentingHouseImpl();

        //获取rentingHouse对象的代理对象
        RentingHouseImpl cglibProxy = (RentingHouseImpl) ProxyFactory.getInstance().getCglibProxy(rentingHouse);
        cglibProxy.rentingHouse();
    }
}

package com.example.lin.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lin on 17/12/1.
 */

public class VipPayFactory {

    public static IVipPayCenter createVipPayCenter() {

        Class service = IVipPayCenter.class;
        return (IVipPayCenter) Proxy.newProxyInstance(service.getClassLoader(),
                new Class[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("method: " + method.getName() + " name: ");
                        return null;
                    }
                }

        );
    }

    public static IVipPayCenter createVipPayCenter2() {

        if (VipType.VIP_TYPE == 1) {
            return new CrmVipPayCenter();
        } else {
            return new PosVipPayCenter();
        }
    }
}

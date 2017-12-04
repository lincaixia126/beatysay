package com.example.lin.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lin on 17/12/1.
 */

public class Demo {

    private static final String TAG = "Demo";

    /**
     * 测试代理接口类
     */
    public void test() {
       IVipPayCenter center = VipPayFactory.createVipPayCenter();
       center.showCampaign("hello .....camaign.....");
       center.showVipAsset("hello.......vipAsset....");
    }

    /**
     * 测试代理实现类
     */
    public void test1() {
        IVipPayCenter center = VipPayFactory.createVipPayCenter2();

        ProxyHandler handler = new ProxyHandler(center);

        IVipPayCenter proxyCenter = (IVipPayCenter)Proxy.newProxyInstance(
                CrmVipPayCenter.class.getClassLoader(),
                CrmVipPayCenter.class.getInterfaces(),
                handler
        );
        proxyCenter.showVipAsset("...asset");
        proxyCenter.showCampaign("...campaign");
    }

    class ProxyHandler implements InvocationHandler {

        private IVipPayCenter mIVipPayCenter;

        public ProxyHandler(IVipPayCenter vipPayCenter) {
            this.mIVipPayCenter = vipPayCenter;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(TAG + "invoke: before....");
            Object result = method.invoke(mIVipPayCenter, args);
            System.out.println(TAG + "invoke: after....");
            return result;
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.test();
        demo.test1();
    }

}
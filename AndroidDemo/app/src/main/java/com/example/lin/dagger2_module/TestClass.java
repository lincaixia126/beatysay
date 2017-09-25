package com.example.lin.dagger2_module;

import android.util.Log;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * Created by lin on 17/9/22.
 * 测试一个根据modeule提供出来的类
 * 是否可以，直接在其他类里采用Inject方式
 * 使用。
 * 为什么，我在demo里无法成功。初始化这里不能直接调用user。会崩溃。。。。。崩溃是因为我直接在构造函数里面输出了mUser。构造时候是null。
 * 等到调用方法时已经可以使用了。
 * 但是pos代码里，VIPPayManager就是这样直接引用的。。。。
 * 目前看源代码，应该确实是可行的。。。。
 */

public class TestClass {

    @Inject
    User mUser;

    @Inject
    public TestClass() {

        //Log.d("TEST", "TestClass: " + mUser.toString());
    }

    public String test() {
        return  (mUser.toString() + ": " + this);
    }
}

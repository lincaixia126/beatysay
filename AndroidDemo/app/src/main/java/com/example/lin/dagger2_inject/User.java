package com.example.lin.dagger2_inject;

import javax.inject.Inject;

/**
 * Created by lin on 17/9/20.
 */

public class User {
    private String mName = "lcx";

    private int age  = 16;

    //使用这个注解会生成 User_Factory
    @Inject
    public User() {

    }

}
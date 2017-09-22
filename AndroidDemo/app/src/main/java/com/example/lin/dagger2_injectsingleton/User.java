package com.example.lin.dagger2_injectsingleton;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by lin on 17/9/20.
 */
@Singleton
public class User {
    private String mName = "lcx";

    private int age  = 16;

    @Inject
    public User() {

    }

}

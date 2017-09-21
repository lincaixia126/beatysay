package com.example.lin.dagger2_inject;

import javax.inject.Inject;

/**
 * Created by lin on 17/9/20.
 */

public class User {
    private String mName = "lcx";

    private int age  = 16;

    @Inject
    public User() {

    }

}

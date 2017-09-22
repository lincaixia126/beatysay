package com.example.lin.dagger2_module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lin on 17/9/20.
 */
@Module
public class LoginModule {

    //每一个接口都生成一个类。是LoginModule_***Factory....
    @Singleton
    @Provides
    public User getUser() {
        return new User();
    }

    //每一个接口都生成一个类。是LoginModule_***Factory....
    @Provides
    public Person getUPerson() {
        return new Person();
    }
}

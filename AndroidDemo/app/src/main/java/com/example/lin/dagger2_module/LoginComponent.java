package com.example.lin.dagger2_module;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by lin on 17/9/20.
 */
@Singleton
@Component(modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}

package com.example.lin.dagger2_module;

import dagger.Component;

/**
 * Created by lin on 17/9/20.
 */
@Component
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}

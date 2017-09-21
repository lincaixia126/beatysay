package com.example.lin.dagger2_inject;

import dagger.Component;

/**
 * Created by lin on 17/9/20.
 */
@Component
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}

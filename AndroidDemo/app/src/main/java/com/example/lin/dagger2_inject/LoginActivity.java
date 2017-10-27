package com.example.lin.dagger2_inject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lin.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.root)
    LinearLayout mRoot;
    @BindView(R.id.http_test)
    Button mHttpTest;

    //没有使用单例， 下面两个是两个不同的对象
    @Inject
    User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerLoginComponent.builder()
                .build()
                .inject(this);

//        mHttpTest.setText(mUser.toString());

    }
}
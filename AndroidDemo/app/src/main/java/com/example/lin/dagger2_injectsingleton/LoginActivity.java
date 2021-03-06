package com.example.lin.dagger2_injectsingleton;

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

    //使用了单例， 下面两个是两个相同的对象
    @Inject
    User mUser;

    @Inject
    User mUser2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerLoginComponent.builder()
                .build()
                .inject(this);

        for (int i = 0; i < 4; i++) {
            TextView textView = new TextView(this);
            textView.setText("测试： " + i);
            mRoot.addView(textView);
        }

        mHttpTest.setText(mUser.toString() + " 2: " + mUser2.toString());
    }
}
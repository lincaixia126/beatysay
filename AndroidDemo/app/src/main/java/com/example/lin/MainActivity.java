package com.example.lin;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.root)
    LinearLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        for (int i = 0; i < 4; i++) {
            TextView textView = new TextView(this);
            textView.setText("测试： " +  i);
            mRoot.addView(textView);
        }

        Uri.Builder params = new Uri.Builder();
        params.appendQueryParameter("serial", "test");
        //默认前面会加 ？ 
        // TODO: 17/10/10 没有找到何处加了问号。。。。 
        Log.d(TAG, "onCreate: " +  params.toString());

    }
}

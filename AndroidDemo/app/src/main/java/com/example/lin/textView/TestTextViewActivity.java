package com.example.lin.textView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.example.lin.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lin on 18/1/22.
 */

public class TestTextViewActivity extends AppCompatActivity {

    private static final String TAG = "TestTextViewActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_textview);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.butn)
    public void onViewClicked() {
        TestTextViewDialog dialog = new TestTextViewDialog(this);
        //dialog.hideKey();
        dialog.show();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d(TAG, "dispatchKeyEvent: " + event.getKeyCode()
                    + " str: " + KeyEvent.keyCodeToString(event.getKeyCode()));
        }
        return super.dispatchKeyEvent(event);
    }

}
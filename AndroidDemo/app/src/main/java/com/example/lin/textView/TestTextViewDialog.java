package com.example.lin.textView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

import com.example.lin.R;
import com.sankuai.erp.support.widget.VipCardEditText;

import java.lang.reflect.Method;

import butterknife.ButterKnife;

/**
 * Created by lin on 18/1/22.
 */

public class TestTextViewDialog extends Dialog {


    public TestTextViewDialog(@NonNull Context context) {
        super(context);
        this.setContentView(R.layout.activity_textview_test);
    }

    public void hideKey() {
        AppCompatEditText vipText = (AppCompatEditText)findViewById(R.id.search_edit);
        hideKeybord(vipText);
    }

    /**
     * 禁止Edittext弹出软件盘，光标依然正常显示。
     */
    public static void hideKeybord(AppCompatEditText editText) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                //ELog.e("hideKeybord setShowSoftInputOnFocus " + e.getMessage());
                //do nothing
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                //ELog.e("hideKeybord setSoftInputShownOnFocus " + e.getMessage());

                //do nothing
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d("lcx", "dispatchKeyEvent: " + event.getKeyCode()
                    + " str: " + KeyEvent.keyCodeToString(event.getKeyCode()));
        }
        return super.dispatchKeyEvent(event);
    }
}
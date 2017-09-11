package com.example.lin.text;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

public class DcTextUtil {

    /**
     * 这里的文本以 ： 分割
     *
     * @param text
     */
    public static SpannableString getText(String text, SpannableString msp) {
        if (text == null) {
            return msp;
        }
        int index = text.indexOf("：");

        if (index == -1) {
            msp.setSpan(new ForegroundColorSpan(0xFF333333), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            msp.setSpan(new ForegroundColorSpan(0xFF999999), 0, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            msp.setSpan(new ForegroundColorSpan(0xFF333333), index, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return msp;

    }

    public static SpannableString getText(String text) {
        SpannableString msp = new SpannableString(text);
        if (text == null) {
            return msp;
        }
        int index = text.indexOf("：");

        if (index == -1) {
            msp.setSpan(new ForegroundColorSpan(0xFF333333), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            msp.setSpan(new ForegroundColorSpan(0xFF999999), 0, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            msp.setSpan(new ForegroundColorSpan(0xFF333333), index, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return msp;

    }


}

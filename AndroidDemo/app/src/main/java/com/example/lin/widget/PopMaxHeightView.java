package com.example.lin.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.lin.R;

public class PopMaxHeightView extends FrameLayout {

    private int mMaxHeight;
    private int defaultHeight = 200;
    public PopMaxHeightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PopMaxHeightViewLayout);
            float maxHeight = ta.getInteger(R.styleable.PopMaxHeightViewLayout_maxHeight, defaultHeight);
            mMaxHeight = (int)maxHeight;
            ta.recycle();
        }
    }

    public PopMaxHeightView(Context context) {
        super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mMaxHeight <= 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        final int mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode == MeasureSpec.UNSPECIFIED) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (this.getMeasuredHeight() > this.mMaxHeight) {
                super.onMeasure(widthMeasureSpec,
                        MeasureSpec.makeMeasureSpec(this.mMaxHeight, MeasureSpec.EXACTLY));
            }
        } else {
            super.onMeasure(widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(this.mMaxHeight, MeasureSpec.AT_MOST));
        }
    }

}
package com.example.lin.campaign;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lin.R;


/**
 * Created by lin on 17/11/21.
 */

public class DcDetailCampaignLayout extends LinearLayout {

    public DcDetailCampaignLayout(Context context) {
        super(context);
    }

    public DcDetailCampaignLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DcDetailCampaignLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        ViewGroup root = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.dc_detail_campaininfo, null);

        View child = LayoutInflater.from(context).inflate(R.layout.dc_detail_campaininfo_item, null);

        root.addView(child);
        root.addView(child);
        root.addView(child);
        root.addView(child);
        root.addView(child);
    }
}

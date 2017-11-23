package com.example.lin.campaign;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 17/11/21.
 */

public class TestCampaignActivity extends Activity {

    @BindView(R.id.dc_detail_campaign_info_root)
    LinearLayout mDcDetailCampaignInfoRoot;
    @BindView(R.id.test)
    TextView mTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_campaign);
        ButterKnife.bind(this);

        View child1 = LayoutInflater.from(this).inflate(R.layout.dc_detail_campaininfo_item, null);


        View child2 = LayoutInflater.from(this).inflate(R.layout.dc_detail_campaininfo_item, null);

        View child3 = LayoutInflater.from(this).inflate(R.layout.dc_detail_campaininfo_item, null);

        View child4 = LayoutInflater.from(this).inflate(R.layout.dc_detail_campaininfo_item, null);

        mDcDetailCampaignInfoRoot.addView(child1);
        mDcDetailCampaignInfoRoot.addView(child2);
        mDcDetailCampaignInfoRoot.addView(child3);
        mDcDetailCampaignInfoRoot.addView(child4);


        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0,0,10,10);
        //mTest.setCompoundDrawables(drawable, drawable, drawable, drawable);

        //mTest.setCompoundDrawablePadding(100);
        mTest.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable, drawable, drawable);
    }
}

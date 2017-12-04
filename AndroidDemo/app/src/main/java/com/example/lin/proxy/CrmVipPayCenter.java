package com.example.lin.proxy;

import android.util.Log;

/**
 * Created by lin on 17/12/1.
 */

public class CrmVipPayCenter implements IVipPayCenter {

    private static final String TAG = "CrmVipPayCenter";
    
    @Override
    public void showCampaign(String param) {
        System.out.println(TAG +  "showCampaign: ");
    }

    @Override
    public void showVipAsset(String param) {
        System.out.println(TAG +  "showVipAsset: ");
    }
}

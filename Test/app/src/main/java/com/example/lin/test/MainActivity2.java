package com.example.lin.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webview);
        WebSettings webSettings= webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        String url = "http://waimai.sjst.test.sankuai.com/v/1.0?f=android&serial=6ade5fab16e322214be8f405981cd11a&version=1090304&bizLoginToken=gRySSKGrsVRQ5llZZTUM2UIfvBoztoseDQqdDCXKwFCekWghH1crwTv-79e2vPdenp5nQIvkA0ynafYCtqqPKQ&poiId=1489730518&poiName=%E7%8E%8B%E7%B4%AB%E5%8D%83%E7%9A%84%E6%B5%8B%E8%AF%95%E9%97%A8%E5%BA%97&tenantId=223561&source=androidpos&role=MasterPos&mpos_id=1215244&device_id=1215244";
        webView.loadUrl(url);
        

    }

}

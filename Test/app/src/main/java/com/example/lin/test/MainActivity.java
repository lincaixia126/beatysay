package com.example.lin.test;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webview);

        webView.setWebChromeClient(new ErpWebChromeClient());
        webView.setWebViewClient(new ErpWebViewClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        WebSettings webSettings = webView.getSettings();
        if (null != webSettings) {
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setGeolocationEnabled(true);
        }

        //String url = "http://waimai.sjst.test.sankuai.com/v/1.0?f=android&serial=6ade5fab16e322214be8f405981cd11a&version=1090304&bizLoginToken=gRySSKGrsVRQ5llZZTUM2UIfvBoztoseDQqdDCXKwFCekWghH1crwTv-79e2vPdenp5nQIvkA0ynafYCtqqPKQ&poiId=1489730518&poiName=%E7%8E%8B%E7%B4%AB%E5%8D%83%E7%9A%84%E6%B5%8B%E8%AF%95%E9%97%A8%E5%BA%97&tenantId=223561&source=androidpos&role=MasterPos&mpos_id=1215244&device_id=1215244";

        String url = "http://waimai.sjst.test.sankuai.com/v/1.0?f=android&serial=597e95d2fc0a4c039a2ecf4e9a69e5a90000000000000225910&version=1090309&bizLoginToken=IOYpW4N_fA4Si50XawaCmlh4bEkpuuTgus5LO7dSOqM79AAfWcZeMnsBDY2sK89m1Ff1jF4HC-gIrJWg8N89MQ&poiId=1489730518&poiName=王紫千的测试门店&tenantId=223561&source=androidpos&role=MasterPos&mpos_id=1215174&device_id=1215174";

        webView.loadUrl(url);


    }

    class ErpWebChromeClient extends WebChromeClient {


        public ErpWebChromeClient() {
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {

            return super.onConsoleMessage(consoleMessage);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }


    }

    class ErpWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            super.shouldOverrideUrlLoading(view, url);
            return false;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);

        }

    }


}

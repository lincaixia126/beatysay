package com.example.lin.testdemo2;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        WebView mSafeWebView = (WebView) findViewById(R.id.webview);
        mSafeWebView.setWebChromeClient(new ErpWebChromeClient());
        mSafeWebView.setWebViewClient(new ErpWebViewClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        WebSettings webSettings = mSafeWebView.getSettings();
        if (null != webSettings) {
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setGeolocationEnabled(true);
        }
        //mSafeWebView.loadUrl("https://waimai-erp.meituan.com/v/1.0?f=android&serial=20f20008717a4e01a6b60ddc6bfa7a6e&version=1090308&bizLoginToken=9i2IQRCujGVv36DPoGwrtz9kf7Ld5EMPi8Np_eZqYTwZq8BL1wiSq_H-o9XkI_ykOCELE6yiV_r0jjwouqW4YA&poiId=162915822&poiName=拉丝维斯西餐厅&tenantId=668290&source=androidpos&role=MasterPos&mpos_id=29582&device_id=29582");
        mSafeWebView.loadUrl("https://waimai-erp.meituan.com/v/1.0?f=android&serial=1dfc34e3f66f447a9003888bb52a1fce&version=1090307&bizLoginToken=cbu8xGB-9cyhSvNIZrJe8E1vl2vptPvnhF0Yv7DfdGx3jey-hnz1LMH_4Gb5CfDzFgu9EXuTeTWiT2-phfUBVA&poiId=159298363&poiName=秦少爷肉夹馍&tenantId=12565&source=androidpos&role=MasterPos&mpos_id=9503&device_id=9503");
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

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

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

        // 旧版本，会在新版本中也可能被调用，所以加上一个判断，防止重复显示
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return;
            }
            // 在这里显示自定义错误页
            Log.d("lcx", "onReceivedError: errorCode: "
                    + errorCode + " ;description: "
                    + description + " ;failingUrl: "
                    + failingUrl);
        }

        // 新版本，只会在Android6及以上调用
        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (request.isForMainFrame()) {
                // 在这里显示自定义错误页
                Log.d("lcx", "onReceivedError: code: " + error.getErrorCode()
                        + " des: " + error.getDescription());
            }
        }

    }

}

package com.example.lin.net.okhttp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lin on 17/9/19.
 */

public class OkhttpTest {

    public static void main(String[] args) {
        new OkhttpTest().test();
    }

    public String test() {
        String url = "http://www.baidu.com";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}





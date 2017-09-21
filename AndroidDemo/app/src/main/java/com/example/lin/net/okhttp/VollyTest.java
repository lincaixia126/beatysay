package com.example.lin.net.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lin.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lin on 17/9/20.
 */

public class VollyTest extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.http_test)
    public void onViewClicked() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.baidu.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error....");
            }
        }

        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}

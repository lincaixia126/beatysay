package com.example.lin.image.imgloaderror;

/**
 * Created by lin on 17/7/21.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.lin.R;


/**
 * 原文地址: http://blog.csdn.net/guolin_blog/article/details/45586553
 * @author guolin
 */
public class MainActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_img_error_load);
        listView = (ListView) findViewById(R.id.list_view);
        ImageAdapter adapter = new ImageAdapter(this, 0, Images.imageUrls);
        listView.setAdapter(adapter);
    }


}
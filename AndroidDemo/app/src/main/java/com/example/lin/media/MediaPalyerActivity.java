package com.example.lin.media;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lin.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lin on 17/9/6.
 */

public class MediaPalyerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_media);
        ButterKnife.bind(this);
        WMMusicPlayer.startPlayer(this);
    }

    @OnClick(R.id.play_btn)
    public void onViewClicked() {
//        testYanhuaPlayer();

        testMyPlayer();
    }

    private void testYanhuaPlayer() {
        final String url = "http://s3.meituan.net/v1/mss_a4650d8569ee45fb9f0f36a36e96e4e4/static/auto_receive_order_success.mp3";
        for (int i = 0; i < 10000; i++)
            WMMusicPlayer.playUrl(getApplicationContext(), url);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 100; i++) {
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < 10000; i++)
//                        WMMusicPlayer.playUrl(getApplicationContext(), url);
//                }
//            });
//        }
    }

    private void testMyPlayer() {
        for (int i = 0; i < 4000; i++) {
            String musicPath = "http://s3.meituan.net/v1/mss_a4650d8569ee45fb9f0f36a36e96e4e4/static/auto_receive_order_success.mp3";
            Intent intent = new Intent(this, MusicPlayerService.class);
            intent.setData(Uri.parse(musicPath));
            //启动服务
            startService(intent);
        }

    }


}

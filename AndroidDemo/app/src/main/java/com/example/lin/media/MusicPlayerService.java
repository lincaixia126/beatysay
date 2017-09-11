package com.example.lin.media;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 播放Service
 * Created by lin on 16/7/27.
 */
public class MusicPlayerService extends Service {

    private static final String TAG = "MusicPlayerService";

    public static boolean isServiceEnable;

    private MediaPlayer mMediaPlayer;
    //
    private MusicFileQueue mMusicFileQueue;

    private ExecutorService mExecutorService;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MusicPlayerService ..onCreate");
        isServiceEnable = true;
        mMusicFileQueue = MusicFileQueue.getInstance();
        mExecutorService = Executors.newCachedThreadPool();
    }


    public synchronized void stop() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isServiceEnable) {
            Log.w(TAG, "当前播放service不可用 isServiceEnable " + isServiceEnable);
            return super.onStartCommand(intent, flags, startId);
        }
        Log.d(TAG, "onStartCommand。。。。。。");
        if (intent != null && intent.getData() != null) {
            //加入队列
            Log.d(TAG, "onStartCommand, 把 " + intent.getData().toString() + " 加入队列");
            mMusicFileQueue.addMusicToList(intent.getData().toString());
            if (TextUtils.isEmpty(mMusicFileQueue.getMusicUrl())) {
                return super.onStartCommand(intent, flags, startId);
            }
            if (mMediaPlayer == null || !mMediaPlayer.isPlaying()) {
                Log.d(TAG, "mMediaPlayer == null ? " + mMediaPlayer);
                Uri url = Uri.parse(mMusicFileQueue.getMusicUrl());
                Log.d(TAG, "onStartCommand,播放第0个 " + intent.getData().toString() + " 播放");
                play(url);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.w(TAG, "MusicPlayerService ..onDestroy");
        isServiceEnable = false;
        stop();
        if (mExecutorService != null) {
            mExecutorService.shutdown();
        }
        Log.d(TAG, "onDestroy: " + "mExecutorService: " + mExecutorService);
        if (mExecutorService != null) {
            Log.d(TAG, " isShutdown: " + mExecutorService.isShutdown());
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public synchronized void play(final Uri uri) {
        //check null
        if (checkMusicFileQueueNull(uri)) return;

        // check service
        if (mExecutorService.isShutdown()) {
            mExecutorService = Executors.newSingleThreadExecutor();
        }

        //excute play
        Log.d(TAG, "......execute before....: " + uri.toString());
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                playMedia(uri);
                Log.d(TAG, "......execute after....: " + uri.toString());
            }
        });

    }

    private synchronized void playMedia(final Uri uri) {
        Log.d(TAG, "Now start to play: " + uri.toString());
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
        Log.d(TAG, "......reset: " + uri.toString());
        mMediaPlayer.reset();
        Log.d(TAG, "......setDataSource: " + uri.toString());
        try {
            mMediaPlayer.setDataSource(getApplicationContext(), uri);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, " onPrepared: " + uri.toString());
                mMediaPlayer.start();
            }
        });
        //播放成功回调
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, " onCompleted: " + uri.toString());
                //删除当前音乐
                mMusicFileQueue.deleteMusic();
                //播放下一首音乐
                if (!mMusicFileQueue.isEmptyList()) {
                    String url = mMusicFileQueue.getMusicUrl();
                    if (TextUtils.isEmpty(url)) {
                        return;
                    }
                    play(Uri.parse(url));
                }
            }
        });

        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d(TAG, "onError: " + " what: " + what + " extra: " + extra);
                return false;
            }
        });
    }

    private boolean checkMusicFileQueueNull(Uri uri) {
        if (uri == null) {
            Log.d(TAG, " uri is null ");
            return true;
        }
        if (mMusicFileQueue.isEmptyList()) {
            Log.d(TAG, " the music file list is empty...");
            return true;
        }
        return false;
    }


}

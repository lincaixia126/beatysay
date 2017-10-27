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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static android.R.attr.id;


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
        Log.w(TAG, "MusicPlayerService ..onCreate");
        isServiceEnable = true;
        mMusicFileQueue = MusicFileQueue.getInstance();
        //这里改成newCacheThreadExcutor会crash，因为播放状态错乱。只能是单线程
        mExecutorService = Executors.newSingleThreadExecutor();
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
        Log.w(TAG, "onStartCommand。。。。。。");
        if (intent != null && intent.getData() != null) {
            //加入队列
            Log.w(TAG, "onStartCommand, 把 " + intent.getData().toString() + " 加入队列");
            mMusicFileQueue.addMusicToList(intent.getData().toString());
            if (TextUtils.isEmpty(mMusicFileQueue.getMusicUrl())) {
                return super.onStartCommand(intent, flags, startId);
            }
            if (mMediaPlayer == null || !mMediaPlayer.isPlaying()) {
                Log.w(TAG, "mMediaPlayer == null ? " + mMediaPlayer);
                Uri url = Uri.parse(mMusicFileQueue.getMusicUrl());
                Log.w(TAG, "onStartCommand,播放第0个 " + intent.getData().toString() + " 播放");
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
        Log.w(TAG, "onDestroy: " + "mExecutorService: " + mExecutorService);
        if (mExecutorService != null) {
            Log.w(TAG, " isShutdown: " + mExecutorService.isShutdown());
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    //这个方法的synchronized去掉，可以避免ANR
    //有可能是因为get方法，会默认去取当前的锁，导致，下面的方法再去拿锁时被lock了。。。。。。
    //submit 是阻塞方法，很容易ANR
    public void play(final Uri uri) {
        //check null
        if (checkMusicFileQueueNull(uri)) return;

        // check service
        if (mExecutorService.isShutdown()) {
            mExecutorService = Executors.newSingleThreadExecutor();
        }

        //excute play
        Log.w(TAG, "......execute before....: " + uri.toString());
        Future<String> fs = mExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String result =   "   id:  " + Thread.currentThread().getName() ;
                Log.w(TAG, "......call after....: " + uri.toString() + result);
                playMedia(uri);
                Log.w(TAG, "......execute after....: " + uri.toString() + result);
                return result;
            }
        });
        // TODO: 17/10/9 加上下面代码会引起ANR
        try {
            Log.w(TAG, " 打印线程执行结果: " + fs.get());  //打印各个线程（任务）执行的结果
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
            //mExecutorService.shutdown();
        }
    }

    private synchronized void playMedia(final Uri uri) {
        Log.w(TAG, "Now start to play: " + uri.toString());
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
        Log.w(TAG, "......reset: " + uri.toString());
        mMediaPlayer.reset();
        Log.w(TAG, "......setDataSource: " + uri.toString());
        try {
            mMediaPlayer.setDataSource(getApplicationContext(), uri);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.w(TAG, " onPrepared: " + uri.toString());
                mMediaPlayer.start();
            }
        });
        //播放成功回调
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.w(TAG, " onCompleted: " + uri.toString());
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
                Log.w(TAG, "onError: " + " what: " + what + " extra: " + extra);
                return false;
            }
        });
    }

    private boolean checkMusicFileQueueNull(Uri uri) {
        if (uri == null) {
            Log.w(TAG, " uri is null ");
            return true;
        }
        if (mMusicFileQueue.isEmptyList()) {
            Log.w(TAG, " the music file list is empty...");
            return true;
        }
        return false;
    }


}

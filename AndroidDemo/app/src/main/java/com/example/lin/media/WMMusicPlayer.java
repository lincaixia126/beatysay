package com.example.lin.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WMMusicPlayer extends Service {
    private static final String TAG = "WMMusicPlayer";
    private static final int PLAY_STATUS_NONE = 0;
    private static final int PLAY_STATUS_DOING = 100;

    private Queue<String> mQueue = new ConcurrentLinkedQueue<>();
    private final byte[] mQueueLock = new byte[0];

    private int mPlayStatus = PLAY_STATUS_NONE;
    private final byte[] mPlayStatusLock = new byte[0];

    private ExecutorService mExecutorService;
    private MediaPlayer mMediaPlayer;
    private PlayTask mPlayTask;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean start = intent.getBooleanExtra("start", false);
        if (start) {
            start();
            return START_NOT_STICKY;
        }

        boolean stop = intent.getBooleanExtra("stop", false);
        if (stop) {
            stop();
            return START_NOT_STICKY;
        }

        String url = intent.getDataString();
        if (!TextUtils.isEmpty(url)) {
            addUrlToQueue(url);
        }
        return START_NOT_STICKY;
    }


    public static void playUrl(Context context, String url) {
        Intent intent = new Intent(context, WMMusicPlayer.class);
        intent.setData(Uri.parse(url));
        context.startService(intent);
    }


    public static void startPlayer(Context context) {
        Intent intent = new Intent(context, WMMusicPlayer.class);
        intent.putExtra("start", true);
        context.startService(intent);
    }

    public static void stopPlayer(Context context) {
        Intent intent = new Intent(context, WMMusicPlayer.class);
        intent.putExtra("stop", true);
        context.startService(intent);
    }


    private synchronized void start() {
        reset();
        Log.d(TAG, "player start()");
        mPlayStatus = PLAY_STATUS_NONE;
        mMediaPlayer = new MediaPlayer();
        mPlayTask = new PlayTask(new Runnable() {
            @Override
            public void run() {
                play();
            }
        });
        mExecutorService = Executors.newSingleThreadExecutor();
        mExecutorService.execute(mPlayTask);
    }

    private synchronized void stop() {
        Log.d(TAG, "player stop()");
        reset();
    }


    private void reset() {
        mQueue.clear();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        if (mPlayTask != null) {
            mPlayTask.stopTask();
            mPlayTask = null;
        }
        if (mExecutorService != null) {
            mExecutorService.shutdownNow();
            mExecutorService = null;
        }
        mPlayStatus = PLAY_STATUS_NONE;
    }


    private void play() {
        synchronized (mPlayStatusLock) {
            if (mPlayStatus == PLAY_STATUS_DOING) {
                try {
                    mPlayStatusLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        final String url;
        synchronized (mQueueLock) {
            url = getUrlFromQueue();
            if (TextUtils.isEmpty(url)) {
                try {
                    mQueueLock.wait();
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }
                return;
            }
        }
        Log.d(TAG, "\n");
        Log.d(TAG, "PLAY START ***********************************");
        Log.d(TAG, "player play() begin        url=" + url);
        mPlayStatus = PLAY_STATUS_DOING;
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(getApplicationContext(), Uri.parse(url));
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Log.d(TAG, "player play() onPrepared   url=" + url);
                    mediaPlayer.start();
                }
            });
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Log.d(TAG, "player play() onCompletion url=" + url);
                    onPlayOneEnd();
                }
            });
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    Log.d(TAG, "player play() onError      url=" + url);
                    onPlayOneEnd();
                    return false;
                }
            });
            mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            onPlayOneEnd();
            Log.e(TAG, "player play() Exception    url=" + url + "\n", e);
        }
    }

    private void onPlayOneEnd() {
        Log.d(TAG, "PLAY END   ***********************************");
        Log.d(TAG, "\n");
        mPlayStatus = PLAY_STATUS_NONE;
        synchronized (mPlayStatusLock) {
            mPlayStatusLock.notifyAll();
        }
    }


    private void addUrlToQueue(String url) {
        Log.d(TAG, "player addUrlToQueue() url=" + url);
        synchronized (mQueue) {
            if (mQueue.contains(url)) {
                return;
            }
            mQueue.add(url);
        }
        synchronized (mQueueLock) {
            mQueueLock.notifyAll();
        }
    }

    private String getUrlFromQueue() {
        synchronized (mQueue) {
            return mQueue.poll();
        }
    }


    private static class PlayTask implements Runnable {
        private static final long DURATION = 0;
        private final Runnable mTask;
        private boolean mStop;

        public PlayTask(Runnable runnable) {
            mTask = runnable;
        }

        public void stopTask() {
            mStop = true;
        }

        @Override
        public void run() {
            while (true) {
                if (mStop) {
                    return;
                }
                if (mTask != null) {
                    mTask.run();
                }
                try {
                    Thread.sleep(DURATION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

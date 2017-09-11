package com.example.lin.media;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 外卖播放声音队列
 * 单例
 * Created by lincaixia on 16/11/21.
 */
public class MusicFileQueue  {

    private List<String> mMusicUrlList;

    private static MusicFileQueue mInstance;

    public static MusicFileQueue getInstance() {
        if (null == mInstance) {
            synchronized (MusicFileQueue.class) {
                if (null == mInstance) {
                    mInstance = new MusicFileQueue();
                }
            }
        }
        return mInstance;
    }

    private MusicFileQueue() {
        mMusicUrlList =  Collections.synchronizedList(new ArrayList<String>());
    }

    public boolean isEmptyList() {
        return mMusicUrlList == null || mMusicUrlList.size() == 0;
    }

    /**
     * 当接受到H5消息播放声音请求时,
     * 播放队列不存在此URL,则往列队后面增加
     * @param url 传递的Url
     */
    public void addMusicToList(String url) {
        synchronized (MusicFileQueue.this) {
            if (mMusicUrlList.contains(url)) {
                Log.d("","Music Player service the url: " + url + " is already in the list...");
                return;
            }
            mMusicUrlList.add(url);
        }
    }

    /**
     *从当前队列中弹出当前的URL
     *用于当播放完成时回调
     */
    public void deleteMusic(){
        synchronized (MusicFileQueue.this) {
            if (mMusicUrlList.size() > 0) {
                mMusicUrlList.remove(0);
            }
        }
    }

    /**
     * 获取当前Musci Url
     * @return 当前播放url
     */
    public String getMusicUrl() {
        synchronized (MusicFileQueue.this) {
            if (mMusicUrlList.size() > 0) {
                return mMusicUrlList.get(0);
            } else {
                return "";
            }
        }
    }
}

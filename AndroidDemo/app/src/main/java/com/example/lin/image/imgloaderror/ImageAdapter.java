package com.example.lin.image.imgloaderror;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.lin.R;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lin on 17/7/21.
 */

public class ImageAdapter extends ArrayAdapter<String> {

    private LruCache<String, BitmapDrawable> mMemoryCache;

    ///////////////////////////
    private ListView mListView;
    ///////////////////////////

    public ImageAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);

        int maxMemory = (int) Runtime.getRuntime().maxMemory();

        int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, BitmapDrawable>(cacheSize) {
            @Override
            protected int sizeOf(String key, BitmapDrawable drawable) {
                return drawable.getBitmap().getByteCount();
            }
        };

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ///////////////////////////
        if (mListView == null) {
            mListView = (ListView) parent;
        }
        ///////////////////////////


        String url = getItem(position);

        View view;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.image_item, null);
        } else {
            view = convertView;
        }

        ImageView image = (ImageView) view.findViewById(R.id.image);

        ///////////////
        image.setImageResource(R.drawable.image_round_gray);
        image.setTag(url);

        BitmapDrawable drawable = getBitmapFromMemoryCache(url);

        if (drawable != null) {
            image.setImageDrawable(drawable);
        } else {
            BitmapWorkerTask task = new BitmapWorkerTask();
            task.execute(url);
        }
        return view;
    }


    public void addBitmapToMemoryCache(String key, BitmapDrawable drawable) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, drawable);
        }
    }


    public BitmapDrawable getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }


    class BitmapWorkerTask extends AsyncTask<String, Void, BitmapDrawable> {

        ///////////////////////////
        String imageUrl;
        ///////////////////////////


        @Override
        protected BitmapDrawable doInBackground(String... params) {
            imageUrl = params[0];

            Bitmap bitmap = downloadBitmap(imageUrl);

            BitmapDrawable bitmapDrawable = new BitmapDrawable(getContext().getResources(), bitmap);
            addBitmapToMemoryCache(imageUrl, bitmapDrawable);


            return bitmapDrawable;
        }


        @Override
        protected void onPostExecute(BitmapDrawable drawable) {
            ImageView imageView = (ImageView) mListView.findViewWithTag(imageUrl);
            if (imageView != null && drawable != null) {
                imageView.setImageDrawable(drawable);
            }
        }

        private Bitmap downloadBitmap(String imageUrl) {

            Bitmap bitmap = null;

            HttpURLConnection con = null;

            try {
                URL url = new URL(imageUrl);

                con = (HttpURLConnection) url.openConnection();

                con.setConnectTimeout(5 * 1000);

                con.setReadTimeout(10 * 1000);

                bitmap = BitmapFactory.decodeStream(con.getInputStream());

            } catch (Exception e) {

            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            return bitmap;
        }

    }


}

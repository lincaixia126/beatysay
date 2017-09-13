package com.example.lin.image.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.example.lin.R;

public class ImageLoader {

    public static final int IMAGE_LOADING = R.drawable.image_round_gray;   //loading占位图
    public static final int IMAGE_ERROR = R.drawable.image_round_gray;  //error图

    // 加载网络图片
    public static void displayByUrl(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(IMAGE_LOADING)
                .error(IMAGE_ERROR)
                .crossFade()   //淡入淡出
                .into(imageView);
    }


    public static void displayCircleImagByUrl(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(IMAGE_LOADING)
                .error(IMAGE_ERROR)
                .crossFade()//淡入淡出
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    /**
     * Glide-所使用显示圆形图片的转换类
     */
    static class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }
        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }
        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }
        @Override
        public String getId() {
            return getClass().getName();
        }
    }


}
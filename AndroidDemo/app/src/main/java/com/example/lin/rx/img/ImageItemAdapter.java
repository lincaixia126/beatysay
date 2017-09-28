package com.example.lin.rx.img;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lin.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 17/9/27.
 */

public class ImageItemAdapter extends RecyclerView.Adapter {

    public List<ImageItemVo> mImageItemVos;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rx_image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageViewHolder) {
            ImageItemVo imageItemVo = mImageItemVos.get(position);
            ((ImageViewHolder)holder).bind(imageItemVo);
        }
    }

    @Override
    public int getItemCount() {
        return mImageItemVos == null ? 0 : mImageItemVos.size();
    }


    public void setImageItemVos(List<ImageItemVo> imageItemVos) {
        this.mImageItemVos = imageItemVos;
        notifyDataSetChanged();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageIv)
        ImageView mImageIv;
        @BindView(R.id.descriptionTv)
        TextView mDescriptionTv;

        private View mItemView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            this.mItemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bind(ImageItemVo imageItemVo) {
            mDescriptionTv.setText(imageItemVo.description);
            Glide.with(mItemView.getContext()).load(imageItemVo.image_url)
                    .into(mImageIv);
        }
    }

}

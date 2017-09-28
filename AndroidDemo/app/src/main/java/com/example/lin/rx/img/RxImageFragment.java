package com.example.lin.rx.img;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.lin.R;
import com.example.lin.rx.img.net.ImageService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lin on 17/9/27.
 */

public class RxImageFragment extends Fragment {


    @BindView(R.id.search_edittv)
    EditText mSearchEdittv;

    ImageItemAdapter mImageItemAdapter = new ImageItemAdapter();
    @BindView(R.id.img_recyclerView)
    RecyclerView mImgRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rx_image, container, false);
        ButterKnife.bind(this, view);
        mImgRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mImgRecyclerView.setAdapter(mImageItemAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.search_btn)
    public void onViewClicked() {

        if (!TextUtils.isEmpty(mSearchEdittv.getText().toString())) {
            String text = mSearchEdittv.getText().toString();
            ImageService.getImageApi()
                    .search(text)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<List<ImageItemVo>>() {
                        @Override
                        public void call(List<ImageItemVo> imageItemVos) {
                            mImageItemAdapter.setImageItemVos(imageItemVos);
                        }
                    });
        }


    }
}

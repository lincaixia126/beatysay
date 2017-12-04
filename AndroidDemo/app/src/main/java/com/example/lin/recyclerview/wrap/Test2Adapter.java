package com.example.lin.recyclerview.wrap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 17/11/28.
 */

public class Test2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<DataItem2> mDataItem2s;

    private List<DataItem2> mSaveItems;

    public static int TEST2_TYPE = 0x1103;

    private TestAdapter mTestAdapter;

    public Test2Adapter(TestAdapter testAdapter, List<DataItem2> dataItem2s) {
        this.mDataItem2s = dataItem2s;
        mTestAdapter = testAdapter;
        mSaveItems = new ArrayList<>();
        mSaveItems.addAll(mDataItem2s);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.test2_rv_item, parent, false);
        return new BottomViewHolder(item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BottomViewHolder) {
            ((BottomViewHolder) holder).onBind(mDataItem2s.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDataItem2s == null ? 0 : mDataItem2s.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TEST2_TYPE;
    }

    class BottomViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.value)
        TextView mValue;
        @BindView(R.id.img1)
        ImageView mImg1;
        @BindView(R.id.img2)
        ImageView mImg2;

        public BottomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(final DataItem2 dataItem2) {
            mTitle.setText(dataItem2.getCampainName());
            mValue.setText(dataItem2.getCampainValue());

            mImg1.setVisibility(dataItem2.isLeftImageVisible() ? View.VISIBLE : View.GONE);
            mImg2.setVisibility(dataItem2.isRightImageVisible() ? View.VISIBLE : View.GONE);

            mImg2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeData();
                }
            });

        }

        private void changeData() {
            if (mDataItem2s.size() > 1) {
                mDataItem2s.clear();
                mDataItem2s.add(mSaveItems.get(0));
            } else {
                mDataItem2s.clear();
                mDataItem2s.addAll(mSaveItems);
            }
            mTestAdapter.notifyDataSetChanged();
        }
    }
}

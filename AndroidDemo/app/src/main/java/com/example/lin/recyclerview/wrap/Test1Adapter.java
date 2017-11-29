package com.example.lin.recyclerview.wrap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lin.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 17/11/28.
 */

public class Test1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<DataItem1> mDataItem1List;

    public static int TEST1_TYPE = 0x1102;

    public Test1Adapter(List<DataItem1> dataItem1s) {
        this.mDataItem1List = dataItem1s;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.test1_rv_item, parent, false);
        return new UpViewHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UpViewHolder) {
            ((UpViewHolder) holder).onBind(mDataItem1List.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDataItem1List == null ? 0 : mDataItem1List.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TEST1_TYPE;
    }

    class UpViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.value)
        TextView mValue;

        public UpViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(DataItem1 dataItem1) {
            mTitle.setText(dataItem1.getDishName());
            mValue.setText(dataItem1.getDishCount());
        }
    }

}

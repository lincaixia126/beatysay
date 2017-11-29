package com.example.lin.recyclerview.wrap;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lin on 17/11/28.
 */

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Test1Adapter mTest1Adapter;

    private Test2Adapter mTest2Adapter;


    public TestAdapter(List<DataItem1> dataItem1s, List<DataItem2> dataItem2s) {
        mTest1Adapter = new Test1Adapter(dataItem1s);

        mTest2Adapter = new Test2Adapter(this, dataItem2s);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Test1Adapter.TEST1_TYPE) {
            return mTest1Adapter.onCreateViewHolder(parent, viewType);
        } else {
            return mTest2Adapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < mTest1Adapter.getItemCount()) {
            mTest1Adapter.onBindViewHolder(holder, position);
        } else {
            mTest2Adapter.onBindViewHolder(holder, position - mTest1Adapter.getItemCount());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mTest1Adapter.getItemCount()) {
            return mTest1Adapter.getItemViewType(position);
        } else {
           return mTest2Adapter.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        return mTest1Adapter.getItemCount() + mTest2Adapter.getItemCount();
    }
}

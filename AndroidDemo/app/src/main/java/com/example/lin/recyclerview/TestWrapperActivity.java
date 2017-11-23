package com.example.lin.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.lin.R;
import com.example.lin.campaign.TestCampaignActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 17/11/21.
 */

public class TestWrapperActivity extends Activity {


    @BindView(R.id.test_recyclerView)
    RecyclerView mTestRecyclerView;

    private List<DataItem> mDataItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recyclerview);
        ButterKnife.bind(this);


        mDataItems = new ArrayList<>();
        mDataItems.add(new DataItem("1123........", "11111"));
        mDataItems.add(new DataItem("2223........", "22111"));
        mDataItems.add(new DataItem("3323........", "33111"));
        mDataItems.add(new DataItem("4423........", "44111"));
        mTestRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        RealAdapter realAdapter = new RealAdapter();
        WrapAdapter<RealAdapter> wrapAdapter = new WrapAdapter(realAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.recyclerview_footer_item, null);
        wrapAdapter.addFooterView(view);

        mTestRecyclerView.setAdapter(wrapAdapter);
    }


    class RealAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_real_item, parent, false);
            return new NormalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            DataItem dataItem = mDataItems.get(position);
            if (holder instanceof NormalViewHolder) {
                ((NormalViewHolder) holder).onBind(dataItem);
            }
        }


        @Override
        public int getItemCount() {
            return (mDataItems == null || mDataItems.size() == 0) ? 0 : mDataItems.size();
        }
    }


    class NormalViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        TextView mValue;

        public NormalViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView)itemView.findViewById(R.id.title);
            mValue = (TextView)itemView.findViewById(R.id.value);

        }

        public void onBind(DataItem dataItem) {
            mTitle.setText(dataItem.getName());
            mValue.setText(dataItem.getValue());
        }
    }


    class DataItem {
        private String name;
        private String value;

        public DataItem(String name, String value) {
            this.name = name;
            this.value = value;
        }


        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

}

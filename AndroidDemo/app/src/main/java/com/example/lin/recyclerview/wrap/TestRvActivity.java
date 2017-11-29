package com.example.lin.recyclerview.wrap;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 试验一个adapter包装其他adapter.....
 * Created by lin on 17/11/28.
 */

public class TestRvActivity extends Activity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wrap_two_adapter_activity);
        ButterKnife.bind(this);


        List<DataItem1>  dataItem1s = new ArrayList<>();
        dataItem1s.add(new DataItem1("西红柿鸡蛋","鸡蛋西红柿","2"));
        dataItem1s.add(new DataItem1("西红柿鸡蛋","鸡蛋西红柿","2"));
        dataItem1s.add(new DataItem1("西红柿鸡蛋","鸡蛋西红柿","2"));
        dataItem1s.add(new DataItem1("西红柿鸡蛋","鸡蛋西红柿","2"));
        dataItem1s.add(new DataItem1("西红柿鸡蛋","鸡蛋西红柿","2"));
        dataItem1s.add(new DataItem1("西红柿鸡蛋","鸡蛋西红柿","2"));

        List<DataItem2> dataItem2s = new ArrayList<>();
        dataItem2s.add(new DataItem2(true, "优惠","8块钱",true));

        dataItem2s.add(new DataItem2(false, "会员折扣","8块钱",false));

        dataItem2s.add(new DataItem2(false, "优惠券","8块钱",false));


        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(new TestAdapter(dataItem1s, dataItem2s));
    }
}

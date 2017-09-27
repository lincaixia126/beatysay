package com.example.lin.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.lin.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lin on 17/9/26.
 *
 * Button  上  setText没有被保存
 * EditText里面输入的内容，栈回退回来会展示。
 */

public class FragmentStack extends AppCompatActivity
        implements
        FragmentOneFragment.FragmentOneInterface
        , FragmentTwoFragment.FragmentTwoInterface
        , FragmentThreeFragment.FragmentThreeInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.http_test)
    public void onViewClicked() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentOneFragment oneFragment = new FragmentOneFragment();
        oneFragment.setFragmentInterface(this);
        fragmentTransaction.add(R.id.root, oneFragment);

        fragmentTransaction.commit();
    }

    @Override
    public void onClickTwoNext() {
        //跳转到Fragment3
        FragmentThreeFragment f3 = new FragmentThreeFragment();
        f3.setFragmentInterface(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.root, f3);
        tx.addToBackStack(null);
        tx.commit();

    }

    @Override
    public void onClickTwoBack() {
        //回退到Fragment1
        FragmentManager fm = getSupportFragmentManager();
        //将当前的事务退出回退栈
        fm.popBackStack();
    }

    @Override
    public void onClickOneNext() {
        FragmentTwoFragment f2 = new FragmentTwoFragment();
        f2.setFragmentInterface(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.root, f2);
        //将当前的事务添加到了回退栈
        tx.addToBackStack(null);
        tx.commit();

    }

    @Override
    public void onClickThreeBack() {
        //回退到Fragment2
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }
}


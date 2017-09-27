package com.example.lin.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;

import com.example.lin.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentThreeFragment extends Fragment  {

    private FragmentThreeInterface mFragmentInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Log.d("lcx", "FragmentThreeFragment onViewCreated: " );
    }

    @Override
    public void onDestroyView() {
        Log.d("lcx", "FragmentThreeFragment onDestroyView: " );
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("lcx", "FragmentThreeFragment onStart: " );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lcx", "FragmentThreeFragment onResume: " );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("lcx", "FragmentThreeFragment onPause: " );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("lcx", "FragmentThreeFragment onStop: " );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lcx", "FragmentThreeFragment onDestroy: " );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("lcx", "FragmentThreeFragment onDetach: " );
    }

    @OnClick(R.id.button) void onButtonClick() {
        //TODO implement
        if (this.mFragmentInterface != null) {
            mFragmentInterface.onClickThreeBack();
        }
    }


    public void setFragmentInterface(FragmentThreeInterface fragmentInterface) {
        this.mFragmentInterface = fragmentInterface;
    }

    public interface FragmentThreeInterface {

        void onClickThreeBack();
    }

}

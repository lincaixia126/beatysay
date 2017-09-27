package com.example.lin.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;

import com.example.lin.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class FragmentOneFragment extends Fragment {


    private FragmentOneInterface mFragmentInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Log.d("lcx", "FragmentOneFragment onViewCreated: " );
    }

    @Override
    public void onDestroyView() {
        Log.d("lcx", "FragmentOneFragment onDestroyView: " );
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("lcx", "FragmentOneFragment onStart: " );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lcx", "FragmentOneFragment onResume: " );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("lcx", "FragmentOneFragment onPause: " );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("lcx", "FragmentOneFragment onStop: " );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lcx", "FragmentOneFragment onDestroy: " );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("lcx", "FragmentOneFragment onDetach: " );
    }

    @OnClick(R.id.button)
    void onButtonClick() {
        if (mFragmentInterface != null) {
            mFragmentInterface.onClickOneNext();
        }
    }

    public void setFragmentInterface(FragmentOneInterface fragmentInterface) {
        this.mFragmentInterface = fragmentInterface;
    }


    public interface FragmentOneInterface {

        void onClickOneNext();
    }

}

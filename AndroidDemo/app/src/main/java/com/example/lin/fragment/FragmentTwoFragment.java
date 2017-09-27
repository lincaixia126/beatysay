package com.example.lin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.os.Build.VERSION_CODES.M;

public class FragmentTwoFragment extends Fragment {


    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.button2)
    Button mButton2;
    Unbinder unbinder;
    private FragmentTwoInterface mFragmentInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Log.d("lcx", "FragmentTwoFragment onViewCreated: ");
    }

    @Override
    public void onDestroyView() {
        Log.d("lcx", "FragmentTwoFragment onDestroyView: ");
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("lcx", "FragmentTwoFragment onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lcx", "FragmentTwoFragment onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("lcx", "FragmentTwoFragment onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("lcx", "FragmentTwoFragment onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lcx", "FragmentTwoFragment onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("lcx", "FragmentTwoFragment onDetach: ");
    }

    @OnClick(R.id.button)
    void onButtonClick() {
        //TODO implement
        if (this.mFragmentInterface != null) {
            mFragmentInterface.onClickTwoNext();
            mButton.setText("测试是否保存....next...");
        }
    }


    @OnClick(R.id.button2)
    void onButton2Click() {
        //TODO implement
        if (this.mFragmentInterface != null) {
            mFragmentInterface.onClickTwoBack();
            mButton2.setText("测试是否保存..back...");
        }
    }

    public void setFragmentInterface(FragmentTwoInterface fragmentInterface) {
        this.mFragmentInterface = fragmentInterface;
    }

    public interface FragmentTwoInterface {

        void onClickTwoNext();

        void onClickTwoBack();
    }

}

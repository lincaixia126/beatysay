package c.vippay;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * 这个UI是什么组件
 * FrameLayout？
 * Fragment？
 * Created by lin on 17/10/27.
 */

public abstract class CalcuteDefaultUI extends Fragment {

    EditText mNum1;
    EditText mNum2;
    TextView mResult;

    private int mResouceId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mResouceId = getResouceId();
        if (mResouceId == 0) {
            mResouceId = R.layout.fragment_calculate_default;
        }
        View view = inflater.inflate(mResouceId, null);
        mNum1 = (EditText) view.findViewById(R.id.num1);
        mNum2 = (EditText) view.findViewById(R.id.num2);
        mResult = (TextView) view.findViewById(R.id.result);
        mResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int num1 = Integer.parseInt(mNum1.getText().toString());
                    int num2 = Integer.parseInt(mNum2.getText().toString());
                    mResult.setText(String.valueOf(num1 + num2));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }


    public abstract int getResouceId();

}

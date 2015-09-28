package com.dxkj.ejiaofei.ejiaofei.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dxkj.ejiaofei.R;
import com.tencent.tauth.Tencent;

/**
 * 客服fragment
 * Created by dxkj on 2015/9/25.
 */
public class ServierFragment extends Fragment {

    private View mView;
    private static ServierFragment mServierFragment;
    private Context mContext;

    private TextView mPhone;
    private Button mCallPhone;
    private Button mCallQQ;

    Tencent mTencent;

    public static ServierFragment newInstance() {
        if (mServierFragment == null) {
            mServierFragment = new ServierFragment();
        }
        return mServierFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_servier, container, false);
        init();
        setTvPhone();
        return mView;
    }

    public void init() {
        mContext = getActivity().getApplicationContext();
        mPhone = (TextView) mView.findViewById(R.id.tv_phone);
        mCallPhone = (Button) mView.findViewById(R.id.callPhone);
        mCallQQ = (Button) mView.findViewById(R.id.callQQ);
        setBtnOnClick();
    }

    public void setTvPhone() {
        mPhone.setText("400-081-7007");
    }

    public void setBtnOnClick() {
        mCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNb = mPhone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNb));
                startActivity(intent);
                Toast.makeText(mContext, "拨打电话", Toast.LENGTH_SHORT).show();
            }
        });
        mCallQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "调用QQ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

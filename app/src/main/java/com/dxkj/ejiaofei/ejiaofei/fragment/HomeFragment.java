package com.dxkj.ejiaofei.ejiaofei.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dxkj.ejiaofei.R;
import com.dxkj.ejiaofei.ejiaofei.activity.PhoneActivity;
import com.dxkj.ejiaofei.ejiaofei.adapter.GridViewAdapter;


/**
 * 首页fragment
 * Created by dxkj on 2015/9/25.
 */
public class HomeFragment extends Fragment {

    private View mView;
    private GridView gv;
    private GridViewAdapter mAdapter;
    private Context mContext;
    public int[] str_picture = {R.drawable.m170, R.drawable.phone, R.drawable.gprs, R.drawable.qq, R.drawable.guhua,
            R.drawable.kami, R.drawable.shuidianmei, R.drawable.game, R.drawable.more};

    private static HomeFragment mHomeFragment;

    public static HomeFragment newInstance() {
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }
        return mHomeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        setClick();
        return mView;
    }

    public void initView() {
        mContext = this.getActivity().getApplication();
        gv = (GridView) mView.findViewById(R.id.gv_goods);
        mAdapter = new GridViewAdapter(mContext, str_picture);
        gv.setAdapter(mAdapter);
    }

    public void setClick() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent1 = new Intent();
                        intent1.setClass(mContext, PhoneActivity.class);
                        startActivity(intent1);
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;

                }
            }
        });
    }
}

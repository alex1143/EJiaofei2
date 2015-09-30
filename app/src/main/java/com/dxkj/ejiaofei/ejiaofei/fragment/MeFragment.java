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
import android.widget.ListView;

import com.dxkj.ejiaofei.R;
import com.dxkj.ejiaofei.ejiaofei.activity.AboutActivity;
import com.dxkj.ejiaofei.ejiaofei.activity.HelpActivity;
import com.dxkj.ejiaofei.ejiaofei.activity.ChangePwActivity;
import com.dxkj.ejiaofei.ejiaofei.activity.WalletActivity;
import com.dxkj.ejiaofei.ejiaofei.adapter.MeListAdapter;


/**
 * 个人信息fragment
 * Created by dxkj on 2015/9/25.
 */
public class MeFragment extends Fragment {

    private View mView;
    private static MeFragment mMeFragment;
    private Context mContext;

    private ListView user_items;
    private MeListAdapter adapter;

    private String[] userItems = {"钱包", "修改密码", "帮助", "关于我们"};
    public int[] icon = {R.drawable.wallet,R.drawable.xiugai,R.drawable.bangzhu,R.drawable.guanyu};

    public static MeFragment newInstance() {
        if (mMeFragment == null) {
            mMeFragment = new MeFragment();
        }
        return mMeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_me, container, false);
        initView();
        setListViewClick();
        return mView;
    }

    private void initView() {
        mContext = getActivity().getApplication();
        user_items = (ListView) mView.findViewById(R.id.user_items);
        adapter = new MeListAdapter(mContext, userItems, icon);
        user_items.setAdapter(adapter);
    }

    public void setListViewClick(){
        user_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent1 = new Intent();
                        intent1.setClass(mContext, WalletActivity.class);   //跳转钱包
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent();
                        intent2.setClass(mContext, ChangePwActivity.class);  //跳转修改密码
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent();
                        intent3.setClass(mContext, HelpActivity.class);  //跳转帮助
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent();
                        intent4.setClass(mContext, AboutActivity.class);  //跳转帮助
                        startActivity(intent4);
                        break;
                }
            }
        });
    }
}

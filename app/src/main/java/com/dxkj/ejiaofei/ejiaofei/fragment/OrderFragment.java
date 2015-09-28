package com.dxkj.ejiaofei.ejiaofei.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dxkj.ejiaofei.R;
import com.dxkj.ejiaofei.ejiaofei.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单fragment
 * Created by dxkj on 2015/9/25.
 */
public class OrderFragment extends Fragment {

    private View mView;
    private static OrderFragment mOrderFragment;

    private ListView mListView;
    private List<String> listData;
    private OrderListAdapter adapter;
    private Context mContext;


    public static OrderFragment newInstance() {
        if (mOrderFragment == null) {
            mOrderFragment = new OrderFragment();
        }
        return mOrderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_order, container, false);
        initView();
        return mView;
    }

    public void initView() {
        mContext = this.getActivity().getApplication();
        listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listData.add("订单1---------------->" + i);
        }
        mListView = (ListView) mView.findViewById(R.id.list_order);
        adapter = new OrderListAdapter(mContext,listData);
        mListView.setAdapter(adapter);
    }
}

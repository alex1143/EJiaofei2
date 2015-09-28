package com.dxkj.ejiaofei.ejiaofei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dxkj.ejiaofei.R;

import java.util.List;

/**
 * 订单fragment listview适配器
 * Created by dxkj on 2015/9/25.
 */
public class OrderListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mListData;

    public OrderListAdapter(Context mContext,List<String> mListData){
        this.mContext = mContext;
        this.mListData = mListData;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.recylcer_order_view,parent,false);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.order_items);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(mListData.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}

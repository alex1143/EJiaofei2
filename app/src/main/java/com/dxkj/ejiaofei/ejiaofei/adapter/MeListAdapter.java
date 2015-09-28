package com.dxkj.ejiaofei.ejiaofei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxkj.ejiaofei.R;


/**
 *
 * Created by dxkj on 2015/9/25.
 */
public class MeListAdapter extends BaseAdapter{

    private Context mContext;
    private String[] userItems;
    private int[] user_Icon;

    public MeListAdapter(Context mContext, String[] userItems, int[] user_Icon){
        this.mContext = mContext;
        this.userItems = userItems;
        this.user_Icon = user_Icon;
    }

    @Override
    public int getCount() {
        return userItems.length;
    }

    @Override
    public Object getItem(int position) {
        return userItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.recylcer_me_view,parent,false);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.tv_title);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_user_icon);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(userItems[position]);
        holder.iv.setBackgroundResource(user_Icon[position]);
        return convertView;
    }

    class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}

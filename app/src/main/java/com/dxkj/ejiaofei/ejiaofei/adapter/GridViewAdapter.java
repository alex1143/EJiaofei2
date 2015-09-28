package com.dxkj.ejiaofei.ejiaofei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dxkj.ejiaofei.R;

/**
 * gridview适配器
 * Created by dxkj on 2015/9/28.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    public int[] str_picture;

    public GridViewAdapter(Context mContext, int[] str_picture) {
        this.mContext = mContext;
        this.str_picture = str_picture;
    }

    @Override
    public int getCount() {
        return str_picture.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.recylcer_home_view, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.grid_pivture);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(str_picture[position]);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }
}

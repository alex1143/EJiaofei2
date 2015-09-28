package com.dxkj.ejiaofei.ejiaofei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dxkj.ejiaofei.R;


/**
 * homefragment的RecylcerView适配器
 * Created by dxkj on 2015/9/25.
 */
public class RecylcerViewAdapter extends RecyclerView.Adapter<RecylcerViewAdapter.ViewHolder> {

    //    private String[] str_name;
    private Context mContext;
    public int[] str_picture;
    private View mView;

    public RecylcerViewAdapter(int[] str_picture, Context mContext) {
        this.str_picture = str_picture;
        this.mContext = mContext;
    }

    //创建新View
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.recylcer_home_view, parent, false);
        return new ViewHolder(mView);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.grid_picture.setBackgroundResource(str_picture[position]);
    }

    @Override
    public int getItemCount() {
        return str_picture.length;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView grid_picture;

        public ViewHolder(View itemView) {
            super(itemView);
            grid_picture = (ImageView) itemView.findViewById(R.id.grid_pivture);
        }
    }
}

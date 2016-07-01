package com.sune.appmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sun
 *
 * 2016-07-01
 */
public class AppAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<AppInfo> appInfos;
    private OnRecyclerViewItemClickListener listener;

    public AppAdapter(Context context, List<AppInfo> appInfos){
        this.context = context;
        this.appInfos = appInfos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.app_list_item, parent, false);
        final MyViewHolder holder = new MyViewHolder(itemView);

        //设置点击效果，模拟5.0水波波纹，兼容5.0以下
        itemView.setBackgroundResource(R.drawable.recycler_bg);
        //向外传递点击事件
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(itemView, holder.getAdapterPosition());
                }
            }
        });
        //向外传递长按事件
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(listener != null){
                    listener.onItemLongClick(itemView, holder.getAdapterPosition());
                }
                return true;
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AppInfo app = appInfos.get(position);
        if(holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.icon.setBackgroundDrawable(app.icon);
            myViewHolder.appName.setText(app.appName);
            myViewHolder.pkgName.setText(app.pkgName);
        }
    }

    @Override
    public int getItemCount() {
        return appInfos.size();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener){
        this.listener = listener;
    }

    public interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView appName;
        TextView pkgName;


        public MyViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.iv_app_icon);
            appName = (TextView) itemView.findViewById(R.id.tv_app_name);
            pkgName = (TextView) itemView.findViewById(R.id.tv_app_pkg);
        }
    }
}

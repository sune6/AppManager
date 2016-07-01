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

    public AppAdapter(Context context, List<AppInfo> appInfos){
        this.context = context;
        this.appInfos = appInfos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
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

    class MyViewHolder extends RecyclerView.ViewHolder{
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

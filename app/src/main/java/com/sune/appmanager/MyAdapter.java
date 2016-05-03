package com.sune.appmanager;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	protected Context context;
	protected List<AppInfo> list;

	public MyAdapter(List<AppInfo> list, Context context) {
		this.list = list;
		this.context = context;
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.my_list_item, null);
			holder = new ViewHolder();
			holder.icon = (ImageView) convertView.findViewById(R.id.iv_program_icon);
			holder.appName = ((TextView) convertView.findViewById(R.id.tv_program_name));
			holder.pkgName = ((TextView) convertView.findViewById(R.id.tv_pkg_name));
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		AppInfo app = list.get(position);
		holder.icon.setImageDrawable(app.icon);
		holder.appName.setText(app.appName);
		holder.pkgName.setText(app.packageName);
		
		return convertView;
	}
	
	private static class ViewHolder {
		ImageView icon;
		TextView appName;
		TextView pkgName;
	}
}

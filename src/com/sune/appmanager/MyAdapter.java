package com.sune.appmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

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
		RelativeLayout layout = (RelativeLayout) LayoutInflater.from(
				context).inflate(R.layout.my_list_item, null);
		
		ImageView icon = ((ImageView) layout.findViewById(R.id.iv_program_icon));
		icon.setImageDrawable(list.get(position).icon);
		
		TextView appName = ((TextView) layout.findViewById(R.id.tv_program_name));
		appName.setText(list.get(position).appName);
		
		TextView packageName = ((TextView) layout.findViewById(R.id.tv_pkg_name));
		packageName.setText(list.get(position).packageName);
		
		return layout;
	}
}

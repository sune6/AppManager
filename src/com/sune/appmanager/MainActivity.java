package com.sune.appmanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
	private MyAdapter adapter;
	private List<AppInfo> list = new ArrayList<AppInfo>();
	private ListView listView;
	private TextView total;

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.my);
		
		listView = ((ListView) findViewById(R.id.lv_my_apps));
		total = (TextView) findViewById(R.id.tv_app_total);
		
		adapter = new MyAdapter(list, MainActivity.this);
		listView.setAdapter(MainActivity.this.adapter);
		listView.setOnItemClickListener(MainActivity.this);
		
		new MyAsyncTast().execute();
	}


	public void onItemClick(AdapterView<?> paramAdapterView, View paramView,
			int paramInt, long paramLong) {
		String pkg = ((AppInfo) list.get(paramInt)).packageName;
		AppUtil.showAppDetails(this, pkg);
	}
	
	private class MyAsyncTast extends AsyncTask<Void, AppInfo, Void>{
		private ProgressDialog pd;
		
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(MainActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
			pd.setTitle("");
			pd.setMessage("Loading...");
			pd.show();
			
		}

		@Override
		protected Void doInBackground(Void... param) {
			getInstallApps();
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			pd.dismiss();
		}
		
		@Override
		protected void onProgressUpdate(AppInfo... apps) {
			list.add(apps[0]);
			total.setText("已安装 " + list.size() + " 个应用");
		    adapter.notifyDataSetChanged();
		}

		private void getInstallApps() {
			List<PackageInfo> packages = getPackageManager().getInstalledPackages(8192);
			for (int i = 0; i < packages.size(); i++) {
				PackageInfo packageInfo = packages.get(i);
				AppInfo app = new AppInfo();
				app.appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
				app.packageName = packageInfo.packageName;
				app.icon = packageInfo.applicationInfo.loadIcon(getPackageManager());
				// 如果属于非系统程序，则添加到列表显示
				if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
					publishProgress(new AppInfo[]{app});
				}
			}
		}
		
	}


}
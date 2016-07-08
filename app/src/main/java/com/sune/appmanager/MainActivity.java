package com.sune.appmanager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private AppAdapter adapter;
    private List<AppInfo> list = new ArrayList<>();
    private TextView total;

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);

        initView();
        new MyAsyncTast().execute();
    }

    private void initView() {
        RecyclerView recyclerView = ((RecyclerView) findViewById(R.id.rv));
        total = (TextView) findViewById(R.id.tv_app_total);

        adapter = new AppAdapter(this, list);
        adapter.setOnRecyclerViewItemClickListener(new AppAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String pkg = list.get(position).pkgName;
                AppUtil.showAppDetails(MainActivity.this, pkg);
            }

            @Override
            public void onItemLongClick(View view, int position) {
//                Toast.makeText(MainActivity.this, "long click", Toast.LENGTH_SHORT).show();
            }
        });
        DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(decor);//分隔线暂时没效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    //TODO 替代AsyncTask
    private class MyAsyncTast extends AsyncTask<Void, AppInfo, Void> {
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
//            adapter.notifyDataSetChanged();
            adapter.notifyItemInserted(list.size());
        }

        private void getInstallApps() {
            List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < packages.size(); i++) {
                PackageInfo pi = packages.get(i);
                AppInfo app = new AppInfo();
                app.appName = pi.applicationInfo.loadLabel(getPackageManager()).toString();
                app.pkgName = pi.packageName;
                app.icon = pi.applicationInfo.loadIcon(getPackageManager());

                if ((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    // 如果属于非系统程序，则添加到列表显示
                    publishProgress(app);
                }
            }
        }

    }


}
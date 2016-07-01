package com.sune.appmanager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;

public class AppUtil {

    /**
     * 显示 系统设置-应用管理-app详情 界面
     *
     * @param context context
     * @param pkg app包名
     */
    public static void showAppDetails(@NonNull Context context, @NonNull String pkg) {
        int version = Build.VERSION.SDK_INT;
        Intent intent = new Intent();
        if (version <= 7) {
            intent.setAction("android.intent.action.VIEW");
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", pkg);
        }
        if (version == 8) {
            intent.setAction("android.intent.action.VIEW");
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("pkg", pkg);
        } else if (version >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", pkg, null));
        }
        context.startActivity(intent);
    }

    /**
     * 启动应用
     *
     * @param context context
     * @param pkg app包名
     */
    public static void startApp(@NonNull Context context, @NonNull String pkg) {
        PackageManager localPackageManager = context.getPackageManager();
        Intent localIntent = localPackageManager.getLaunchIntentForPackage(pkg);
        context.startActivity(localIntent);
    }

    /**
     * 显示app卸载界面
     *
     * @param context context
     * @param pkg app包名
     */
    public static void uninstallApp(@NonNull Context context, @NonNull String pkg) {
        Intent localIntent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + pkg));
        context.startActivity(localIntent);
    }
}

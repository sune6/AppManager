package com.sune.appmanager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

public class AppUtil {
	
	public static void showAppDetails(Context context, String pkg) {
		int i = Build.VERSION.SDK_INT;
		Intent intent = new Intent();
		if (i <= 7) {
			intent.setAction("android.intent.action.VIEW");
			intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
			intent.putExtra("com.android.settings.ApplicationPkgName", pkg);
		}
		if (i == 8) {
			intent.setAction("android.intent.action.VIEW");
			intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
			intent.putExtra("pkg", pkg);
		} else if (i >= 9) {
			intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
			intent.setData(Uri.fromParts("package", pkg, null));
		}
		context.startActivity(intent);
	}

	public static void startApp(Context context, String pkg) {
		PackageManager localPackageManager = context.getPackageManager();
		new Intent("android.intent.action.VIEW");
		Intent localIntent = localPackageManager
				.getLaunchIntentForPackage(pkg);
		context.startActivity(localIntent);
	}


	public static void uninstallApp(Context context, String pkg) {
		Intent localIntent = new Intent("android.intent.action.DELETE",
				Uri.parse("package:" + pkg));
		context.startActivity(localIntent);
	}
}

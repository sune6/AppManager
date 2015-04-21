package com.sune.appmanager;

import java.text.Collator;
import java.util.Comparator;

import android.graphics.drawable.Drawable;

public class AppInfo {
	public Drawable icon;
	public String packageName;
	public String appName;
	public long size;

	static class ProgramName implements Comparator<AppInfo> {
		private Collator collator = Collator.getInstance();

		public int compare(AppInfo paramProgram1, AppInfo paramProgram2) {
			return this.collator.compare(paramProgram1.appName,
					paramProgram2.appName);
		}
	}

	static class ProgramSize implements Comparator<AppInfo> {
		public int compare(AppInfo paramProgram1, AppInfo paramProgram2) {
			boolean bool = paramProgram1.size < paramProgram2.size;
			int i = 0;
			if (bool)
				i = 1;
			if (paramProgram1.size == paramProgram2.size)
				i = 0;
			if (paramProgram1.size < paramProgram2.size)
				i = -1;
			return i;
		}
	}
}

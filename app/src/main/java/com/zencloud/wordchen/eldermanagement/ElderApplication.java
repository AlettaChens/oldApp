package com.zencloud.wordchen.eldermanagement;

import android.os.Build;

import com.dyhdyh.manager.ActivityManager;
import com.gizwits.energy.android.lib.base.BaseApplication;
import com.zencloud.wordchen.eldermanagement.web.WebApiManager;

import org.xutils.x;

public class ElderApplication extends BaseApplication implements  Thread.UncaughtExceptionHandler{
	private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;
	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
		WebApiManager.getInstance().init();
		ActivityManager.getInstance().register(this);
	}
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (ex != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Device Model: ").append(Build.MODEL).append("\n");
			sb.append("OS: ").append(Build.VERSION.RELEASE).append("\n");
			sb.append("App Code: ").append(BuildConfig.VERSION_CODE).append("\n");
			sb.append("App Name: ").append(BuildConfig.VERSION_NAME).append("\n");
			sb.append(printExceptionStack(ex));
		}
		mUncaughtExceptionHandler.uncaughtException(thread, ex);
	}

	private String printExceptionStack(Throwable ex) {
		Throwable th = ex;
		StringBuilder sb = new StringBuilder();
		while (th != null) {
			sb.append("Caused By: ").append(th.getClass().getName()).append(":").append(th.getMessage()).append("\n");
			sb.append("Caused Detail: ");
			for (StackTraceElement ste : th.getStackTrace()) {
				sb.append("\tat ").append(ste).append("\n");
			}
			th = th.getCause();
		}
		return sb.toString();
	}
}

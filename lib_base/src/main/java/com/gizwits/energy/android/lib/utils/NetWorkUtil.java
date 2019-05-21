package com.gizwits.energy.android.lib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



public class NetWorkUtil {
	public static boolean isWifiConnected(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo net = conn.getActiveNetworkInfo();
		if (net != null && net.getType() == ConnectivityManager.TYPE_WIFI && net.isConnected()) {
			return true;
		}
		return false;
	}
}

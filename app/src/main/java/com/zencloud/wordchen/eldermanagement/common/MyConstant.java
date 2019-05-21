package com.zencloud.wordchen.eldermanagement.common;

import android.os.Environment;

import com.gizwits.energy.android.lib.base.AbstractConstantClass;

/**
 * Created by black-Gizwits on 2016/10/20.
 */
public final class MyConstant extends AbstractConstantClass {
	public static final String PATH;
	public static final String WEB_LOG_DIR = "/log_web";
	public static final String CRASH_LOG_DIR = "/log_crash";

	static {
		PATH = Environment.getExternalStorageDirectory().getPath() + "/ykGizwitsAdapter";
	}

	private MyConstant() {
		super();
	}
}

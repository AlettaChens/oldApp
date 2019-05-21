package com.zencloud.wordchen.eldermanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dyhdyh.manager.ActivityManager;
import com.gizwits.energy.android.lib.base.BaseActivity;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.common.OldSp;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_settings)
public class SettingsActivity extends BaseActivity {
	@ViewInject(R.id.ly_about_app)
	private LinearLayout ly_about_app;
	@ViewInject(R.id.ly_exit)
	private LinearLayout ly_exit;
	@ViewInject(R.id.iv_backend)
	private ImageView iv_backend;
	private OldSp oldSp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		oldSp = new OldSp(SettingsActivity.this);
	}

	@Event(value = {R.id.ly_about_app, R.id.iv_backend, R.id.ly_exit})
	private void OnAboutClick(View view) {
		switch (view.getId()) {
			case R.id.ly_about_app: {
				Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
				startActivity(intent);
				break;
			}
			case R.id.iv_backend: {
				onBackPressed();
				break;
			}
			case R.id.ly_exit: {
				clearInfo();
				ActivityManager.getInstance().finishAllActivity();
				break;
			}
		}
	}

	private void clearInfo() {
		oldSp.putIsLogin(false);
		oldSp.putSex("");
		oldSp.putNickName("");
		oldSp.putAddress("");
		oldSp.putPhone("");
		oldSp.putImage_url("");
		oldSp.putAge("");
	}
}

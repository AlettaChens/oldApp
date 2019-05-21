package com.zencloud.wordchen.eldermanagement.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gizwits.energy.android.lib.base.BaseActivity;
import com.zencloud.wordchen.eldermanagement.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activtiy_aboutapp)
public class AboutActivity extends BaseActivity {
	@ViewInject(R.id.backend)
	private ImageView backend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		backend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
	}
}

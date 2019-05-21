package com.zencloud.wordchen.eldermanagement.activity;

import android.content.Intent;
import android.os.Bundle;

import com.gizwits.energy.android.lib.base.BaseActivity;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.common.OldSp;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity {
	private OldSp oldSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    oldSp = new OldSp(WelcomeActivity.this);
        x.task().postDelayed(new Runnable() {
            @Override
            public void run() {
	            if (oldSp.getIsLogin()) {
		            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
		            startActivity(intent);
	            } else {
		            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
		            startActivity(intent);
	            }
                finish();
            }
        }, 3000);
    }
}

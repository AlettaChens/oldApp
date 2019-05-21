package com.zencloud.wordchen.eldermanagement.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gizwits.energy.android.lib.base.BaseActivity;
import com.gizwits.energy.android.lib.utils.MessageUtils;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.presenter.UserPresenter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

	@ViewInject(R.id.et_input_name)
	private EditText et_input_name;
	@ViewInject(R.id.iv_cancel_register)
	private ImageView iv_cancel_register;
	@ViewInject(R.id.et_input_password)
	private EditText et_input_password;
	@ViewInject(R.id.et_confirm_password)
	private EditText et_confirm_password;
	@ViewInject(R.id.btn_register)
	private Button btn_toRegister;

	private UserPresenter userPresenter;
	private RegisterHandle registerHandle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerHandle = new RegisterHandle();
		userPresenter = new UserPresenter(RegisterActivity.this);

	}

	@Event(value = {R.id.btn_register, R.id.iv_cancel_register})
	private void onRegisterClick(View view) {
		switch (view.getId()) {
			case R.id.btn_register: {
				if (!TextUtils.isEmpty(et_input_name.getText()) && !TextUtils.isEmpty(et_input_password.getText()) && !TextUtils.isEmpty(et_confirm_password
						.getText())) {
					if (et_input_password.getText().toString().equals(et_confirm_password.getText().toString())) {
						userPresenter.register(et_input_name.getText().toString(), et_input_password.getText().toString(), registerHandle);
					} else {
						MessageUtils.showShortToast(RegisterActivity.this, "两次输入的密码不一致");
					}
				} else {
					MessageUtils.showShortToast(RegisterActivity.this, "注册信息不完整");
				}

				break;
			}
			case R.id.iv_cancel_register: {
				onBackPressed();
				break;
			}
		}
	}

	public class RegisterHandle implements UserPresenter.IRegisterHandler {
		@Override
		public void onRegisterSuccess(String message) {
			MessageUtils.showShortToast(RegisterActivity.this, message);
		}

		@Override
		public void onRegisterFail(String message) {
			MessageUtils.showShortToast(RegisterActivity.this, message);
		}

		@Override
		public void onRegisterError(Throwable e) {

		}

		@Override
		public void onRequestStarted() {
			showLoadingDialog("注册用户中...");
		}

		@Override
		public void onRequestCanceled() {

		}

		@Override
		public void onRequestFinished() {
			dismissLoadingDialog();
		}
	}
}

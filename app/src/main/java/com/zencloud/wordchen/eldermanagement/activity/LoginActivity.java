package com.zencloud.wordchen.eldermanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gizwits.energy.android.lib.base.BaseActivity;
import com.gizwits.energy.android.lib.utils.MessageUtils;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.presenter.UserPresenter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.iv_cancel)
    private ImageView iv_cancel;
    @ViewInject(R.id.et_input_name)
    private EditText et_input_name;
    @ViewInject(R.id.et_input_password)
    private EditText et_input_password;
    @ViewInject(R.id.tv_to_register)
    private TextView tv_to_register;
    @ViewInject(R.id.btn_register)
    private Button btn_register;

    private UserPresenter userPresenter;
    private LoginHandler loginHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPresenter = new UserPresenter(LoginActivity.this);
        loginHandler = new LoginHandler();
    }

    @Event(value = {R.id.iv_cancel, R.id.btn_login, R.id.tv_to_register})
    private void onLoginClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel: {
	            finish();
                break;
            }
            case R.id.btn_login: {
                if (!TextUtils.isEmpty(et_input_name.getText()) && !TextUtils.isEmpty(et_input_password.getText())) {
                    userPresenter.login(et_input_name.getText().toString(), et_input_password.getText().toString(), loginHandler);
                }
                break;
            }
            case R.id.tv_to_register: {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            }

        }
    }

    public class LoginHandler implements UserPresenter.ILoginHandler {

        @Override
        public void onLoginSuccess() {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
	        finish();
        }

        @Override
        public void onLoginFail(String message) {
            MessageUtils.showShortToast(LoginActivity.this, message);
        }

        @Override
        public void onLoginError(Throwable e) {
            MessageUtils.showShortToast(LoginActivity.this, e.toString());
        }

        @Override
        public void onRequestStarted() {
            showLoadingDialog("登录中...");
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




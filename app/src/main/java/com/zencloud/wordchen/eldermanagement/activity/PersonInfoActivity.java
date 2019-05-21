package com.zencloud.wordchen.eldermanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gizwits.energy.android.lib.base.BaseActivity;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.common.OldSp;
import com.zencloud.wordchen.eldermanagement.dialog.UpdateUserInfoDialog;
import com.zencloud.wordchen.eldermanagement.presenter.CallBack.CommonCalback;
import com.zencloud.wordchen.eldermanagement.presenter.UserPresenter;
import com.zencloud.wordchen.eldermanagement.utils.GlideX;
import com.zencloud.wordchen.eldermanagement.utils.MessageUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.activity_personmassage)
public class PersonInfoActivity extends BaseActivity {
	@ViewInject(R.id.backend)
	private ImageView backend;

	@ViewInject(R.id.iv_personal_message_head_portrait)
	private ImageView iv_personal_message_head_portrait;

	@ViewInject(R.id.tv_user_name)
	private TextView tv_user_name;

	@ViewInject(R.id.tv_user_phone)
	private TextView tv_user_phone;

	@ViewInject(R.id.tv_user_sex)
	private TextView tv_user_sex;

	@ViewInject(R.id.tv_user_age)
	private TextView tv_user_age;

	@ViewInject(R.id.change_info)
	private Button change_info;

	@ViewInject(R.id.tv_user_address)
	private TextView tv_user_address;

	private OldSp oldSp;

	private UpdateUserInfoDialog updateUserInfoDialog;
	private UserPresenter userPresenter;


	@ViewInject(R.id.ll_address)
	private LinearLayout ll_address;

	@ViewInject(R.id.ll_age)
	private LinearLayout ll_age;

	@ViewInject(R.id.ll_phone)
	private LinearLayout ll_phone;

	@ViewInject(R.id.ll_name)
	private LinearLayout ll_name;

	@ViewInject(R.id.ll_sex)
	private LinearLayout ll_sex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		oldSp = new OldSp(PersonInfoActivity.this);
		userPresenter = new UserPresenter(PersonInfoActivity.this);
		tv_user_name.setText(oldSp.getNickName());
		tv_user_age.setText(oldSp.getAge());
		tv_user_phone.setText(oldSp.getPhone());
		tv_user_sex.setText(oldSp.getSex());
		tv_user_address.setText(oldSp.getAddress());
	}

	@Override
	protected void onResume() {
		super.onResume();
		GlideX.getInstance().loadCircleImage(PersonInfoActivity.this, oldSp.getImage_url(), iv_personal_message_head_portrait);
	}

	@Event({R.id.change_info, R.id.backend, R.id.iv_personal_message_head_portrait})
	private void OnChangeClick(View view) {
		switch (view.getId()) {
			case R.id.backend: {
				onBackPressed();
				break;
			}
			case R.id.change_info: {
				userPresenter.updateInfo(tv_user_address.getText().toString(), tv_user_age.getText().toString(), tv_user_phone.getText().toString(),
						tv_user_sex.getText().toString(), tv_user_name.getText().toString(), oldSp.getUserId(), new updateInfoHandle());
				break;
			}
			case R.id.iv_personal_message_head_portrait: {
				startActivity(new Intent(PersonInfoActivity.this, ChangeAvatarActivity.class));
				break;
			}
		}
	}


	@Event({R.id.ll_address, R.id.ll_sex, R.id.ll_name, R.id.ll_phone, R.id.ll_age})
	private void OnInfoChangeClick(View view) {
		switch (view.getId()) {
			case R.id.ll_phone: {
				updateUserInfoDialog = new UpdateUserInfoDialog(PersonInfoActivity.this, new changeHandle(), "手机号");
				updateUserInfoDialog.show();
				break;
			}
			case R.id.ll_name: {
				updateUserInfoDialog = new UpdateUserInfoDialog(PersonInfoActivity.this, new changeHandle(), "昵称");
				updateUserInfoDialog.show();
				break;
			}
			case R.id.ll_age: {
				updateUserInfoDialog = new UpdateUserInfoDialog(PersonInfoActivity.this, new changeHandle(), "年龄");
				updateUserInfoDialog.show();
				break;
			}
			case R.id.ll_sex: {
				updateUserInfoDialog = new UpdateUserInfoDialog(PersonInfoActivity.this, new changeHandle(), "性别");
				updateUserInfoDialog.show();
				break;
			}
			case R.id.ll_address: {
				updateUserInfoDialog = new UpdateUserInfoDialog(PersonInfoActivity.this, new changeHandle(), "地址");
				updateUserInfoDialog.show();
				break;
			}
		}
	}


	public class updateInfoHandle implements CommonCalback {
		@Override
		public void onSuccess(String message) {
			MessageUtils.showLongToast(PersonInfoActivity.this, message);
		}

		@Override
		public void onFail(String message) {
			MessageUtils.showLongToast(PersonInfoActivity.this, message);
		}

		@Override
		public void onError(String message) {
			MessageUtils.showLongToast(PersonInfoActivity.this, message);
		}

		@Override
		public void onRequestStarted() {
			showLoadingDialog("更新数据中...");
		}

		@Override
		public void onRequestCanceled() {

		}

		@Override
		public void onRequestFinished() {
			dismissLoadingDialog();
		}
	}

	public class changeHandle implements UpdateUserInfoDialog.UpdateCompelet {
		@Override
		public void onCompelet(String type, String info) {
			switch (type) {
				case "手机号": {
					tv_user_phone.setText(info);
					break;
				}
				case "昵称": {
					tv_user_name.setText(info);
					break;
				}
				case "年龄": {
					tv_user_age.setText(info);
					break;
				}
				case "性别": {
					tv_user_sex.setText(info);
					break;
				}
				case "地址": {
					tv_user_address.setText(info);
					break;
				}
			}
		}
	}
}

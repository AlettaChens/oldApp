package com.zencloud.wordchen.eldermanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.gizwits.energy.android.lib.base.BaseActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.common.OldSp;
import com.zencloud.wordchen.eldermanagement.dialog.PhotoSelectDialog;
import com.zencloud.wordchen.eldermanagement.presenter.CallBack.CommonCalback;
import com.zencloud.wordchen.eldermanagement.presenter.UserPresenter;
import com.zencloud.wordchen.eldermanagement.utils.GlideX;
import com.zencloud.wordchen.eldermanagement.utils.MessageUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.List;

@ContentView(R.layout.activity_change_avatar)
public class ChangeAvatarActivity extends BaseActivity {
	@ViewInject(R.id.iv_change_avatar)
	private ImageView iv_change_avatar;

	@ViewInject(R.id.backend)
	private ImageView backend;
	PhotoSelectDialog photoSelectDialog;
	UserPresenter userPresenter;
	OldSp oldSp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userPresenter = new UserPresenter(ChangeAvatarActivity.this);
		oldSp = new OldSp(ChangeAvatarActivity.this);
		GlideX.getInstance().loadCircleImage(ChangeAvatarActivity.this, oldSp.getImage_url(), iv_change_avatar);
		iv_change_avatar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showPhotoChoice();
			}
		});
		backend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
	}

	private void showPhotoChoice() {
		photoSelectDialog = new PhotoSelectDialog(ChangeAvatarActivity.this, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				photoSelectDialog.dismiss();
				switch (v.getId()) {
					case R.id.btn_take_photo:
						PictureSelector.create(ChangeAvatarActivity.this).openCamera(PictureMimeType.ofImage()).enableCrop(true).compress(true).forResult
								(PictureConfig.CHOOSE_REQUEST);
						break;
					case R.id.btn_pick_photo:
						PictureSelector.create(ChangeAvatarActivity.this).openGallery(PictureMimeType.ofImage()).enableCrop(true).compress(true).selectionMode
								(PictureConfig.SINGLE).forResult(PictureConfig.CHOOSE_REQUEST);
						break;
					default:
						break;
				}
			}
		});
		photoSelectDialog.showAtLocation(ChangeAvatarActivity.this.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		List<LocalMedia> images;
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
				case PictureConfig.CHOOSE_REQUEST:
					images = PictureSelector.obtainMultipleResult(data);
					File file = new File(images.get(0).getCompressPath());
					userPresenter.updateImg(file, oldSp.getUserId(), new UpdateImageHandle());
					break;
			}
		}
	}

	public class UpdateImageHandle implements CommonCalback {
		@Override
		public void onSuccess(String message) {
			GlideX.getInstance().loadCircleImage(ChangeAvatarActivity.this, message, iv_change_avatar);
		}

		@Override
		public void onFail(String message) {
			MessageUtils.showLongToast(ChangeAvatarActivity.this, message);
		}

		@Override
		public void onError(String message) {
			MessageUtils.showLongToast(ChangeAvatarActivity.this, message);
		}

		@Override
		public void onRequestStarted() {
			showLoadingDialog("上传中...");
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

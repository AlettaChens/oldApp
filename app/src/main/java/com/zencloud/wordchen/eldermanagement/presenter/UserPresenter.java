package com.zencloud.wordchen.eldermanagement.presenter;

import android.content.Context;

import com.gizwits.energy.android.lib.presenter.BasePresenter;
import com.gizwits.energy.android.lib.presenter.IRequestLifeCycleHandler;
import com.gizwits.energy.android.lib.web.base.WebRequestCancelHandler;
import com.zencloud.wordchen.eldermanagement.bean.User;
import com.zencloud.wordchen.eldermanagement.common.OldSp;
import com.zencloud.wordchen.eldermanagement.presenter.CallBack.CommonCalback;
import com.zencloud.wordchen.eldermanagement.web.ApiResponse;
import com.zencloud.wordchen.eldermanagement.web.WebApiManager;

import org.xutils.common.Callback;

import java.io.File;

import static com.gizwits.energy.android.lib.web.base.BaseWebAPI.BaseErrorCode.CODE_SUCCESS;

public class UserPresenter extends BasePresenter {
	private OldSp oldSp;

	public UserPresenter(Context context) {
		super(context);
		oldSp = new OldSp(context);
	}

	public WebRequestCancelHandler register(String nickname, String password, final IRegisterHandler handler) {
		final WebRequestCancelHandler webRequestCancelHandlerRegister = new WebRequestCancelHandler();
		handler.onRequestStarted();
		Callback.Cancelable cancelable = WebApiManager.getInstance().register(nickname, password, new Callback.CommonCallback<ApiResponse<Object>>() {
			@Override
			public void onSuccess(ApiResponse<Object> result) {
				if (result.getCode().equals(CODE_SUCCESS)) {
					handler.onRegisterSuccess("注册成功");
				} else {
					handler.onRegisterFail("注册失败");
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				handler.onRegisterError(ex);
			}

			@Override
			public void onCancelled(CancelledException cex) {
				handler.onRequestCanceled();
			}

			@Override
			public void onFinished() {
				webRequestCancelHandlerRegister.setFinished(true);
				handler.onRequestFinished();
			}
		});
		webRequestCancelHandlerRegister.setCancelable(cancelable);

		addCancelHandler(webRequestCancelHandlerRegister);
		return webRequestCancelHandlerRegister;
	}

	public WebRequestCancelHandler login(String nickname, String password, final ILoginHandler iLoginHandler) {
		final WebRequestCancelHandler webRequestCancelHandlerLogin = new WebRequestCancelHandler();
		iLoginHandler.onRequestStarted();
		Callback.Cancelable cancelable = WebApiManager.getInstance().login(nickname, password, new Callback.CommonCallback<ApiResponse<User>>() {
			@Override
			public void onSuccess(ApiResponse<User> result) {
				if (result.getCode().equals(CODE_SUCCESS)) {
					iLoginHandler.onLoginSuccess();
					saveUserInfo(result.getData());
				} else {
					iLoginHandler.onLoginFail("登录失败");
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				iLoginHandler.onLoginError(ex);
			}

			@Override
			public void onCancelled(CancelledException cex) {
				iLoginHandler.onRequestCanceled();
			}

			@Override
			public void onFinished() {
				webRequestCancelHandlerLogin.setFinished(true);
				iLoginHandler.onRequestFinished();
			}

		});
		webRequestCancelHandlerLogin.setCancelable(cancelable);
		addCancelHandler(webRequestCancelHandlerLogin);
		return webRequestCancelHandlerLogin;
	}


	public WebRequestCancelHandler updateImg(File file, int userId, final CommonCalback commonCalback) {
		final WebRequestCancelHandler webRequestCancelHandlerLogin = new WebRequestCancelHandler();
		commonCalback.onRequestStarted();
		Callback.Cancelable cancelable = WebApiManager.getInstance().updateImage(file, userId, new Callback.CommonCallback<ApiResponse<String>>() {
			@Override
			public void onSuccess(ApiResponse<String> result) {
				if (result.getCode().equals(CODE_SUCCESS)) {
					commonCalback.onSuccess(result.getData());
					oldSp.putImage_url(result.getData());
				} else {
					commonCalback.onFail("修改失败");
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				commonCalback.onError(ex.toString());
			}

			@Override
			public void onCancelled(CancelledException cex) {
				commonCalback.onRequestCanceled();
			}

			@Override
			public void onFinished() {
				webRequestCancelHandlerLogin.setFinished(true);
				commonCalback.onRequestFinished();
			}

		});
		webRequestCancelHandlerLogin.setCancelable(cancelable);
		addCancelHandler(webRequestCancelHandlerLogin);
		return webRequestCancelHandlerLogin;
	}


	public WebRequestCancelHandler updateInfo(final String address, final String age, final String phone, final String sex, final String nickname, int userId,
	                                          final CommonCalback commonCalback) {
		final WebRequestCancelHandler webRequestCancelHandlerLogin = new WebRequestCancelHandler();
		commonCalback.onRequestStarted();
		Callback.Cancelable cancelable = WebApiManager.getInstance().updateInfo(address, age, phone, sex, nickname, userId, new Callback
				.CommonCallback<ApiResponse>() {
			@Override
			public void onSuccess(ApiResponse result) {
				if (result.getCode().equals(CODE_SUCCESS)) {
					commonCalback.onSuccess("修改成功");
					oldSp.putAddress(address);
					oldSp.putAge(age);
					oldSp.putPhone(phone);
					oldSp.putNickName(nickname);
					oldSp.putSex(sex);
				} else {
					commonCalback.onFail("修改失败");
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				commonCalback.onError(ex.toString());
			}

			@Override
			public void onCancelled(CancelledException cex) {
				commonCalback.onRequestCanceled();
			}

			@Override
			public void onFinished() {
				webRequestCancelHandlerLogin.setFinished(true);
				commonCalback.onRequestFinished();
			}

		});
		webRequestCancelHandlerLogin.setCancelable(cancelable);
		addCancelHandler(webRequestCancelHandlerLogin);
		return webRequestCancelHandlerLogin;
	}


	private void saveUserInfo(User user) {
		oldSp.putIsLogin(true);
		oldSp.putUserId(user.getUser_id());
		oldSp.putNickName(user.getNickname());
		oldSp.putPassword(user.getPassword());
		oldSp.putSex(user.getSex());
		oldSp.putAge(user.getAge());
		oldSp.putPhone(user.getPhone());
		oldSp.putImage_url(user.getImage_url());
		oldSp.putAddress(user.getAddress());
	}


	public interface IRegisterHandler extends IRequestLifeCycleHandler {
		void onRegisterSuccess(String message);

		void onRegisterFail(String message);

		void onRegisterError(Throwable e);
	}

	public interface ILoginHandler extends IRequestLifeCycleHandler {
		void onLoginSuccess();

		void onLoginFail(String message);

		void onLoginError(Throwable e);
	}
}

package com.zencloud.wordchen.eldermanagement.presenter;

import android.content.Context;

import com.gizwits.energy.android.lib.presenter.BasePresenter;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;
import com.gizwits.energy.android.lib.web.base.WebRequestCancelHandler;
import com.zencloud.wordchen.eldermanagement.bean.ShoppingCar;
import com.zencloud.wordchen.eldermanagement.presenter.CallBack.CommonCalback;
import com.zencloud.wordchen.eldermanagement.utils.MessageUtils;
import com.zencloud.wordchen.eldermanagement.web.ApiResponse;
import com.zencloud.wordchen.eldermanagement.web.WebApiManager;

import org.xutils.common.Callback;

import java.util.List;

import static com.gizwits.energy.android.lib.web.base.BaseWebAPI.BaseErrorCode.CODE_SUCCESS;

public class ShoppingCarPresenter extends BasePresenter {
	public ShoppingCarPresenter(Context context) {
		super(context);
	}

	public WebRequestCancelHandler getShopCartByUserId(int UserId, final IDefaultListLoader<ShoppingCar> handle) {
		final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
		Callback.Cancelable cancelable = WebApiManager.getInstance().getShopCartByUserId(UserId, new Callback.CommonCallback<ApiResponse<List<ShoppingCar>>>
				() {
			@Override
			public void onSuccess(ApiResponse<List<ShoppingCar>> result) {
				if (result.getCode().equals("200")) {
					handle.onLoadSuccess(result.getData());
				} else {
					handle.onLoadFail("加载失败");
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				handle.onLoadFail(ex.toString());
			}

			@Override
			public void onCancelled(CancelledException cex) {

			}

			@Override
			public void onFinished() {
				webRequestCancelHandler.setFinished(true);
				handle.onRequestFinished();
			}

		});
		webRequestCancelHandler.setCancelable(cancelable);
		addCancelHandler(webRequestCancelHandler);
		return webRequestCancelHandler;
	}

	public WebRequestCancelHandler deleteShopCartService(int shopId, final CommonCalback commonCalback) {
		final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
		Callback.Cancelable cancelable = WebApiManager.getInstance().deleteShopCartService(shopId, new Callback.CommonCallback<ApiResponse>() {
			@Override
			public void onSuccess(ApiResponse result) {
				if (result.getCode().equals(CODE_SUCCESS)) {
					commonCalback.onSuccess(result.getMessage());
				} else {
					commonCalback.onFail(result.getMessage());
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				commonCalback.onError(ex.toString());
			}

			@Override
			public void onCancelled(CancelledException cex) {

			}

			@Override
			public void onFinished() {
				webRequestCancelHandler.setFinished(true);
				commonCalback.onRequestFinished();
			}
		});
		webRequestCancelHandler.setCancelable(cancelable);
		addCancelHandler(webRequestCancelHandler);
		return webRequestCancelHandler;
	}

	public WebRequestCancelHandler addShop(int serviceId, int UserId, final CommonCalback commonCalback) {
		final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
		Callback.Cancelable cancelable = WebApiManager.getInstance().addShop(serviceId, UserId, new Callback.CommonCallback<ApiResponse>() {
			@Override
			public void onSuccess(ApiResponse result) {
				if (result.getCode().equals(CODE_SUCCESS)) {
					commonCalback.onSuccess(result.getMessage());
				} else {
					commonCalback.onFail(result.getMessage());
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				commonCalback.onError(ex.toString());
			}

			@Override
			public void onCancelled(CancelledException cex) {

			}

			@Override
			public void onFinished() {
				webRequestCancelHandler.setFinished(true);
				commonCalback.onRequestFinished();
			}
		});
		webRequestCancelHandler.setCancelable(cancelable);
		addCancelHandler(webRequestCancelHandler);
		return webRequestCancelHandler;
	}
}

package com.zencloud.wordchen.eldermanagement.presenter;

import android.content.Context;

import com.gizwits.energy.android.lib.presenter.BasePresenter;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;
import com.gizwits.energy.android.lib.web.base.WebRequestCancelHandler;
import com.zencloud.wordchen.eldermanagement.bean.Order;
import com.zencloud.wordchen.eldermanagement.presenter.CallBack.CommonCalback;
import com.zencloud.wordchen.eldermanagement.web.ApiResponse;
import com.zencloud.wordchen.eldermanagement.web.WebApiManager;

import org.xutils.common.Callback;

import java.util.List;

import static com.gizwits.energy.android.lib.web.base.BaseWebAPI.BaseErrorCode.CODE_SUCCESS;

public class OrderPresenter extends BasePresenter {
	public OrderPresenter(Context context) {
		super(context);
	}


	public WebRequestCancelHandler getOrder(String state, int userId, final IDefaultListLoader commonCalback) {
		final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
		Callback.Cancelable cancelable = WebApiManager.getInstance().getOrder(state, userId, new Callback.CommonCallback<ApiResponse<List<Order>>>() {
				@Override
			public void onSuccess(ApiResponse<List<Order>> result) {
				if (result.getCode().equals(CODE_SUCCESS)) {
					commonCalback.onLoadSuccess(result.getData());
				} else {
					commonCalback.onLoadFail(result.getMessage());
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				commonCalback.onLoadFail(ex.toString());
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


	public WebRequestCancelHandler addOrder(int serviceId, int userId, final CommonCalback commonCalback) {
		final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
		Callback.Cancelable cancelable = WebApiManager.getInstance().addOrder(serviceId, userId, new Callback.CommonCallback<ApiResponse>() {
			@Override
			public void onSuccess(ApiResponse result) {
				if (result.getCode().equals(CODE_SUCCESS)) {
					commonCalback.onSuccess("添加成功");
				} else {
					commonCalback.onFail("添加失败");
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

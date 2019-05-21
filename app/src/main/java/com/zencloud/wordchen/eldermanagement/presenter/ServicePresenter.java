package com.zencloud.wordchen.eldermanagement.presenter;

import android.content.Context;

import com.gizwits.energy.android.lib.presenter.BasePresenter;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;
import com.gizwits.energy.android.lib.web.base.WebRequestCancelHandler;
import com.zencloud.wordchen.eldermanagement.bean.Service;
import com.zencloud.wordchen.eldermanagement.web.ApiResponse;
import com.zencloud.wordchen.eldermanagement.web.WebApiManager;

import org.xutils.common.Callback;

import java.util.List;


public class ServicePresenter extends BasePresenter {
	public ServicePresenter(Context context) {
		super(context);
	}

	public WebRequestCancelHandler getServiceByType(String type, final IDefaultListLoader<Service> handle) {
		final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
		Callback.Cancelable cancelable = WebApiManager.getInstance().getServiceByType(type, new Callback.CommonCallback<ApiResponse<List<Service>>>() {
			@Override
			public void onSuccess(ApiResponse<List<Service>> result) {
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


	/**
	 * 获取热门服务
	 *
	 * @param hot
	 * @param handle
	 * @return
	 */
	public WebRequestCancelHandler getServiceByHot(String hot, final IDefaultListLoader<Service> handle) {
		final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
		Callback.Cancelable cancelable = WebApiManager.getInstance().getServiceByHot(hot, new Callback.CommonCallback<ApiResponse<List<Service>>>() {
			@Override
			public void onSuccess(ApiResponse<List<Service>> result) {
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
}

package com.zencloud.wordchen.eldermanagement.presenter.CallBack;

import com.gizwits.energy.android.lib.presenter.IRequestLifeCycleHandler;

public interface CommonCalback extends IRequestLifeCycleHandler {
	void onSuccess(String message);

	void onFail(String message);

	void onError(String message);
}

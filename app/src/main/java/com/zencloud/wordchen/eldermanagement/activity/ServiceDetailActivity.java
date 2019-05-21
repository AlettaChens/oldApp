package com.zencloud.wordchen.eldermanagement.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gizwits.energy.android.lib.base.BaseActivity;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.bean.Service;
import com.zencloud.wordchen.eldermanagement.common.OldSp;
import com.zencloud.wordchen.eldermanagement.presenter.CallBack.CommonCalback;
import com.zencloud.wordchen.eldermanagement.presenter.ShoppingCarPresenter;
import com.zencloud.wordchen.eldermanagement.utils.GlideX;
import com.zencloud.wordchen.eldermanagement.utils.MessageUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_service_detail)
public class ServiceDetailActivity extends BaseActivity {

	@ViewInject(R.id.backend)
	private ImageView backend;
	@ViewInject(R.id.tv_set_head_title)
	private TextView tv_set_head_title;
	@ViewInject(R.id.iv_type_service_pic)
	private ImageView iv_type_service_pic;
	@ViewInject(R.id.service_name)
	private TextView service_name;
	@ViewInject(R.id.service_price)
	private TextView service_price;
	@ViewInject(R.id.service_des)
	private TextView service_des;
	@ViewInject(R.id.addShopCart)
	private Button addShopCart;
	private Service service;
	private ShoppingCarPresenter shoppingCarPresenter;
	private OldSp oldSp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shoppingCarPresenter = new ShoppingCarPresenter(ServiceDetailActivity.this);
		oldSp = new OldSp(ServiceDetailActivity.this);
		service = (Service) getIntent().getSerializableExtra("service");
		GlideX.getInstance().loadImage(ServiceDetailActivity.this, service.getService_image_url(), iv_type_service_pic);
		tv_set_head_title.setText(service.getService_name());
		service_name.setText("服务类容:" + service.getService_name());
		service_price.setText(Html.fromHtml("价格:<font color='#FF0000'>" + String.valueOf(service.getService_price() + "</font>")));
		service_des.setText("简介:" + service.getService_description());
	}

	@Event({R.id.addShopCart, R.id.backend})
	private void OnServiceDetailEvent(View view) {
		switch (view.getId()) {
			case R.id.addShopCart: {
				shoppingCarPresenter.addShop(service.getService_id(), oldSp.getUserId(), new AddshopHandle());
				break;
			}
			case R.id.backend: {
				onBackPressed();
				break;
			}
		}
	}

	private class AddshopHandle implements CommonCalback {
		@Override
		public void onSuccess(String message) {
			MessageUtils.showLongToast(ServiceDetailActivity.this, "添加购物车成功");
			onBackPressed();
		}

		@Override
		public void onFail(String message) {
			MessageUtils.showLongToast(ServiceDetailActivity.this, message);
		}

		@Override
		public void onError(String message) {
			MessageUtils.showLongToast(ServiceDetailActivity.this, message);
		}

		@Override
		public void onRequestStarted() {

		}

		@Override
		public void onRequestCanceled() {

		}

		@Override
		public void onRequestFinished() {

		}
	}


}

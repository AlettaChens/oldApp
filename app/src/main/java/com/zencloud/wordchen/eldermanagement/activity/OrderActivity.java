package com.zencloud.wordchen.eldermanagement.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyhdyh.manager.ActivityManager;
import com.gizwits.energy.android.lib.base.BaseActivity;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.adapter.OrderListAdapter;
import com.zencloud.wordchen.eldermanagement.bean.ShoppingCar;
import com.zencloud.wordchen.eldermanagement.common.OldSp;
import com.zencloud.wordchen.eldermanagement.presenter.CallBack.CommonCalback;
import com.zencloud.wordchen.eldermanagement.presenter.OrderPresenter;
import com.zencloud.wordchen.eldermanagement.presenter.ShoppingCarPresenter;
import com.zencloud.wordchen.eldermanagement.utils.MessageUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.activity_order)
public class OrderActivity extends BaseActivity {
	@ViewInject(R.id.backend)
	private ImageView backend;
	@ViewInject(R.id.order_user)
	private TextView order_user;
	@ViewInject(R.id.order_address)
	private TextView order_address;
	@ViewInject(R.id.order_phone)
	private TextView order_phone;
	@ViewInject(R.id.rv_order_list)
	private RecyclerView rv_order_list;
	@ViewInject(R.id.total_order_price)
	private TextView total_order_price;
	@ViewInject(R.id.publish_order)
	private Button publish_order;

	private OrderListAdapter orderListAdapter;
	private List<ShoppingCar> shoppingCars;
	private OldSp oldSp;
	private String total;
	private OrderPresenter orderPresenter;
	private ShoppingCarPresenter shoppingCarPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		orderPresenter = new OrderPresenter(OrderActivity.this);
		shoppingCarPresenter = new ShoppingCarPresenter(OrderActivity.this);
		oldSp = new OldSp(OrderActivity.this);
		shoppingCars = (List<ShoppingCar>) getIntent().getSerializableExtra("shopcart");
		total = getIntent().getStringExtra("total");
		orderListAdapter = new OrderListAdapter(OrderActivity.this, shoppingCars);
		order_user.setText("购买人:" + oldSp.getNickName());
		order_address.setText("地址:" + oldSp.getAddress());
		order_phone.setText("联系电话:" + oldSp.getPhone());
		total_order_price.setText(total);
		orderListAdapter = new OrderListAdapter(OrderActivity.this, shoppingCars);
		rv_order_list.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
		rv_order_list.addItemDecoration(new DividerItemDecoration(OrderActivity.this, DividerItemDecoration.HORIZONTAL));
		rv_order_list.setAdapter(orderListAdapter);
	}

	@Event({R.id.backend, R.id.publish_order})
	private void OrderClick(View view) {
		switch (view.getId()) {
			case R.id.backend: {
				//startActivity(new Intent(OrderActivity.this, MainActivity.class));
				onBackPressed();
				break;
			}
			case R.id.publish_order: {
				if (!TextUtils.isEmpty(oldSp.getAddress()) && !TextUtils.isEmpty(oldSp.getPhone())) {
					requestOrder();
				} else {
					MessageUtils.showLongToast(OrderActivity.this, "请先完善个人信息，以便我们能找到您！");
				}
				break;
			}
		}
	}

	private void requestOrder() {
		for (int i = 0; i < shoppingCars.size(); i++) {
			ShoppingCar shoppingCar = shoppingCars.get(i);
			orderPresenter.addOrder(shoppingCar.getService_id(), shoppingCars.get(i).getUser_id(), new CommonCalback() {
				@Override
				public void onSuccess(String message) {
					//MessageUtils.showLongToast(OrderActivity.this, "订购成功");
					//onBackPressed();
				}

				@Override
				public void onFail(String message) {
					MessageUtils.showLongToast(OrderActivity.this, message);
				}

				@Override
				public void onError(String message) {
					MessageUtils.showLongToast(OrderActivity.this, message);
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
			});
			shoppingCarPresenter.deleteShopCartService(shoppingCar.getShopcart_id(), new deleteH());
		}
	}

	public class deleteH implements CommonCalback {
		@Override
		public void onSuccess(String message) {
			ActivityManager.getInstance().finishActivity(MainActivity.class);
			Intent intent = new Intent(OrderActivity.this, MainActivity.class);
			intent.putExtra("enter2", "shop");
			startActivity(intent);
			MessageUtils.showLongToast(OrderActivity.this, "订购成功");
		}

		@Override
		public void onFail(String message) {

		}

		@Override
		public void onError(String message) {

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

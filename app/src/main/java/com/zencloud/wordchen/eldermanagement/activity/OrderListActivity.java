package com.zencloud.wordchen.eldermanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gizwits.energy.android.lib.base.BaseActivity;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.adapter.StateOrderAdapter;
import com.zencloud.wordchen.eldermanagement.common.OldSp;
import com.zencloud.wordchen.eldermanagement.presenter.OrderPresenter;
import com.zencloud.wordchen.eldermanagement.presenter.ShoppingCarPresenter;
import com.zencloud.wordchen.eldermanagement.utils.MessageUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.activity_order_stute)
public class OrderListActivity extends BaseActivity {
	@ViewInject(R.id.backend)
	private ImageView backend;

	@ViewInject(R.id.tv_set_head_title)
	private TextView tv_set_head_title;


	@ViewInject(R.id.state_list)
	private RecyclerView state_list;

	private String text_title;

	private OrderPresenter orderPresenter;
	private OldSp oldSp;
	private StateOrderAdapter stateOrderAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		oldSp = new OldSp(OrderListActivity.this);
		text_title = getIntent().getStringExtra("title");
		orderPresenter = new OrderPresenter(OrderListActivity.this);
		tv_set_head_title.setText(text_title);
		backend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
		orderPresenter.getOrder(text_title, oldSp.getUserId(), new IDefaultListLoader() {
			@Override
			public void onLoadFail(String msg) {
				MessageUtils.showLongToast(OrderListActivity.this, msg);
			}

			@Override
			public void onLoadSuccess(@NonNull List list) {
				stateOrderAdapter = new StateOrderAdapter(OrderListActivity.this, list);
				state_list.addItemDecoration(new DividerItemDecoration(OrderListActivity.this,0));
				state_list.setLayoutManager(new LinearLayoutManager(OrderListActivity.this));
				state_list.setAdapter(stateOrderAdapter);

			}

			@Override
			public void onListEnd() {

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
	}
}

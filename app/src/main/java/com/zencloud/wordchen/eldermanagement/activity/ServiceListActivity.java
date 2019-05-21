package com.zencloud.wordchen.eldermanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gizwits.energy.android.lib.base.BaseActivity;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.adapter.TypeServiceAdapter;
import com.zencloud.wordchen.eldermanagement.bean.Service;
import com.zencloud.wordchen.eldermanagement.common.OldSp;
import com.zencloud.wordchen.eldermanagement.presenter.CallBack.CommonCalback;
import com.zencloud.wordchen.eldermanagement.presenter.ServicePresenter;
import com.zencloud.wordchen.eldermanagement.presenter.ShoppingCarPresenter;
import com.zencloud.wordchen.eldermanagement.utils.MessageUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.activity_service)
public class ServiceListActivity extends BaseActivity {

	@ViewInject(R.id.backend)
	private ImageView backend;
	@ViewInject(R.id.tv_set_head_title)
	private TextView tv_set_head_title;
	@ViewInject(R.id.rv_type_service_list)
	private RecyclerView rv_type_service_list;

	private ServicePresenter servicePresenter;
	private TypeServiceAdapter typeServiceAdapter;
	private AddshopHandle addshopHandle;
	private ShoppingCarPresenter shoppingCarPresenter;
	private OldSp oldSp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		servicePresenter = new ServicePresenter(ServiceListActivity.this);
		shoppingCarPresenter= new ShoppingCarPresenter(ServiceListActivity.this);
		oldSp= new OldSp(ServiceListActivity.this);
		addshopHandle= new AddshopHandle();
		Intent typeIntent = getIntent();
		String type = typeIntent.getStringExtra("type");
		tv_set_head_title.setText(type);
		getTypeService(type);
	}

	private void getTypeService(final String type) {
		servicePresenter.getServiceByType(type, new IDefaultListLoader<Service>() {
			@Override
			public void onLoadFail(String msg) {
				MessageUtils.showLongToast(ServiceListActivity.this, msg);
			}

			@Override
			public void onLoadSuccess(@NonNull List<Service> list) {
				typeServiceAdapter = new TypeServiceAdapter(ServiceListActivity.this, list);
				typeServiceAdapter.setOnTypeItemClickHandle(new TypeAdapterHandle());
				rv_type_service_list.setLayoutManager(new GridLayoutManager(ServiceListActivity.this, 2));
				rv_type_service_list.setAdapter(typeServiceAdapter);
			}

			@Override
			public void onListEnd() {

			}

			@Override
			public void onRequestStarted() {
				showLoadingDialog("加载中...");
			}

			@Override
			public void onRequestCanceled() {

			}

			@Override
			public void onRequestFinished() {
				dismissLoadingDialog();
			}
		});
	}

	@Event({R.id.backend})
	private void OnServiceActivityClick(View view) {
		onBackPressed();
	}

	public class TypeAdapterHandle implements TypeServiceAdapter.OnTypeItemClickHandle {
		@Override
		public void addShopCart(@NonNull Service service) {
			shoppingCarPresenter.addShop(service.getService_id(), oldSp.getUserId(), new CommonCalback() {
				@Override
				public void onSuccess(String message) {
					MessageUtils.showLongToast(ServiceListActivity.this,"添加购物车成功");
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
			});
		}

		@Override
		public void showItemDetail(@NonNull Service service) {
			Intent intent = new Intent(ServiceListActivity.this, ServiceDetailActivity.class);
			intent.putExtra("service", service);
			startActivity(intent);
		}
	}


	private class AddshopHandle implements CommonCalback {
		@Override
		public void onSuccess(String message) {
			MessageUtils.showLongToast(ServiceListActivity.this, "添加购物车成功");
			onBackPressed();
		}

		@Override
		public void onFail(String message) {
			MessageUtils.showLongToast(ServiceListActivity.this, message);
		}

		@Override
		public void onError(String message) {
			MessageUtils.showLongToast(ServiceListActivity.this, message);
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

package com.zencloud.wordchen.eldermanagement.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gizwits.energy.android.lib.base.BaseFragment;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.activity.OrderActivity;
import com.zencloud.wordchen.eldermanagement.adapter.ShopCartAdapter;
import com.zencloud.wordchen.eldermanagement.bean.ShoppingCar;
import com.zencloud.wordchen.eldermanagement.common.OldSp;
import com.zencloud.wordchen.eldermanagement.presenter.CallBack.CommonCalback;
import com.zencloud.wordchen.eldermanagement.presenter.ShoppingCarPresenter;
import com.zencloud.wordchen.eldermanagement.utils.MessageUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_shoppingcarpage)
public class ShoppingCarFragment extends BaseFragment {
	private ShoppingCarPresenter shoppingCarPresenter;
	private OldSp oldSp;
	@ViewInject(R.id.rv_shopCart)
	private RecyclerView rv_shopCart;
	@ViewInject(R.id.selectAll)
	private CheckBox selectAll;
	@ViewInject(R.id.total_price)
	private TextView total_price;
	@ViewInject(R.id.compute)
	private Button compute;
	private ShopCartAdapter shopCartAdapter;
	private ShoppingCar messageShop;
	private List<ShoppingCar> shoppingCars;


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (shoppingCars == null) {
			shoppingCars = new ArrayList<>();
		}
		shoppingCarPresenter = new ShoppingCarPresenter(getActivity());
		oldSp = new OldSp(getActivity());
		getShopCart(oldSp.getUserId());
		total_price.setText(Html.fromHtml("价格:<font color='#FF0000'>0.0</font>"));
		selectAll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				shopCartAdapter.allSelect(selectAll.isChecked());
			}
		});
		shopCartAdapter = new ShopCartAdapter(getActivity(), shoppingCars, new ShopHandler());
		rv_shopCart.addItemDecoration(new ShopCartAdapter.GridSpacingItemDecoration(20));
		rv_shopCart.setLayoutManager(new LinearLayoutManager(getActivity()));
		rv_shopCart.setAdapter(shopCartAdapter);
	}


	@Event({R.id.compute})
	private void OnShopCartClick(View view) {
		List<ShoppingCar> orderService = shopCartAdapter.getselectShopCart();
		if (orderService.size() > 0) {
			Intent intent = new Intent(getContext(), OrderActivity.class);
			intent.putExtra("shopcart", (Serializable) orderService);
			intent.putExtra("total", total_price.getText().toString());
			startActivity(intent);
		} else {
			MessageUtils.showLongToast(getContext(), "请先选择服务后结算");
		}
	}

	private void getShopCart(int userId) {
		shoppingCarPresenter.getShopCartByUserId(userId, new IDefaultListLoader<ShoppingCar>() {
			@Override
			public void onLoadFail(String msg) {
				MessageUtils.showLongToast(getActivity(), msg);
			}

			@Override
			public void onLoadSuccess(@NonNull List<ShoppingCar> list) {
				shopCartAdapter.addAll(list);
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

	public class ShopHandler implements ShopCartAdapter.ShopCarthandler {
		@Override
		public void updateToatalCash(float totalPrice) {
			total_price.setText(Html.fromHtml("价格:<font color='#FF0000'>" + String.valueOf(totalPrice) + "</font>"));
		}


		@Override
		public void isAllSelect(boolean isCheck) {
			selectAll.setChecked(isCheck);
		}

		@Override
		public void deleteShopCart(ShoppingCar shoppingCar) {
			messageShop = shoppingCar;
			shoppingCarPresenter.deleteShopCartService(shoppingCar.getShopcart_id(), new deleteShopHandle());
		}
	}

	public class deleteShopHandle implements CommonCalback {
		@Override
		public void onSuccess(String message) {
			MessageUtils.showLongToast(getContext(), "移除购物车");
			shopCartAdapter.remove(messageShop);
		}

		@Override
		public void onFail(String message) {
			MessageUtils.showLongToast(getContext(), message);
		}

		@Override
		public void onError(String message) {
			MessageUtils.showLongToast(getContext(), message);
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

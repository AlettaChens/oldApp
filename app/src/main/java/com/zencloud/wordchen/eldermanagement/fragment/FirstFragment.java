package com.zencloud.wordchen.eldermanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gizwits.energy.android.lib.base.BaseFragment;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;
import com.youth.banner.Banner;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.activity.ServiceListActivity;
import com.zencloud.wordchen.eldermanagement.activity.ServiceDetailActivity;
import com.zencloud.wordchen.eldermanagement.adapter.HotServiceAdapter;
import com.zencloud.wordchen.eldermanagement.bean.Service;
import com.zencloud.wordchen.eldermanagement.presenter.ServicePresenter;
import com.zencloud.wordchen.eldermanagement.utils.GlideImageLoader;
import com.zencloud.wordchen.eldermanagement.utils.MessageUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_homepage)
public class FirstFragment extends BaseFragment {

	@ViewInject(R.id.vp_banner)
	private Banner vp_banner;
	@ViewInject(R.id.ly_Close_Service)
	private LinearLayout ly_Close_Service;
	@ViewInject(R.id.ly_Coke_Service)
	private LinearLayout ly_Coke_Service;
	@ViewInject(R.id.ly_Mouth_Service)
	private LinearLayout ly_Mouth_Service;
	@ViewInject(R.id.ly_Cut_Service)
	private LinearLayout ly_Cut_Service;
	@ViewInject(R.id.ly_Physical_Service)
	private LinearLayout ly_Physical_Service;
	@ViewInject(R.id.ly_Chart_Service)
	private LinearLayout ly_Chart_Service;
	@ViewInject(R.id.lv_hot_service)
	private ListView lv_hot_service;
	private List<Integer> bannerResouce;
	private List<Service> serviceList;

	private ServicePresenter servicePresenter;
	private HotServiceAdapter hotServiceAdapter;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (serviceList == null) {
			serviceList = new ArrayList<>();
		}
		servicePresenter = new ServicePresenter(getActivity());
		getHotList();
		initResouce();
		vp_banner.setImageLoader(new GlideImageLoader());
		vp_banner.setImages(bannerResouce);
		vp_banner.start();
		lv_hot_service.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
				intent.putExtra("service", serviceList.get(i));
				startActivity(intent);
			}
		});
	}

	private void getHotList() {
		servicePresenter.getServiceByHot("热门", new IDefaultListLoader<Service>() {
			@Override
			public void onLoadFail(String msg) {
				MessageUtils.showLongToast(getActivity(), msg);
			}

			@Override
			public void onLoadSuccess(@NonNull List<Service> list) {
				serviceList = list;
				hotServiceAdapter = new HotServiceAdapter(list, getActivity());
				lv_hot_service.setAdapter(hotServiceAdapter);
			}

			@Override
			public void onListEnd() {

			}

			@Override
			public void onRequestStarted() {
				showLoadingDialog("获取数据");
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

	private void initResouce() {
		if (bannerResouce == null) {
			bannerResouce = new ArrayList<>();
		}
		bannerResouce.add(R.mipmap.first_banner);
		bannerResouce.add(R.mipmap.second_banner);
		bannerResouce.add(R.mipmap.third_banner);
		bannerResouce.add(R.mipmap.fourth_banner);
	}

	@Event({R.id.ly_Close_Service, R.id.ly_Coke_Service, R.id.ly_Mouth_Service, R.id.ly_Cut_Service, R.id.ly_Physical_Service, R.id.ly_Chart_Service, R.id
			.ly_Chart_Service})
	private void onServiceClicked(View view) {
		switch (view.getId()) {
			case R.id.ly_Close_Service:
				Intent intent1 = new Intent(getActivity(), ServiceListActivity.class);
				intent1.putExtra("type", "衣着服务");
				startActivity(intent1);
				break;
			case R.id.ly_Coke_Service:
				Intent intent2 = new Intent(getActivity(), ServiceListActivity.class);
				intent2.putExtra("type", "做食服务");
				startActivity(intent2);
				break;
			case R.id.ly_Mouth_Service:
				Intent intent3 = new Intent(getActivity(), ServiceListActivity.class);
				intent3.putExtra("type", "清洁服务");
				startActivity(intent3);
				break;
			case R.id.ly_Cut_Service:
				Intent intent4 = new Intent(getActivity(), ServiceListActivity.class);
				intent4.putExtra("type", "修饰服务");
				startActivity(intent4);
				break;
			case R.id.ly_Physical_Service:
				Intent intent5 = new Intent(getActivity(), ServiceListActivity.class);
				intent5.putExtra("type", "健康服务");
				startActivity(intent5);
				break;
			case R.id.ly_Chart_Service:
				Intent intent6 = new Intent(getActivity(), ServiceListActivity.class);
				intent6.putExtra("type", "助乐服务");
				startActivity(intent6);
				break;
		}
	}
}

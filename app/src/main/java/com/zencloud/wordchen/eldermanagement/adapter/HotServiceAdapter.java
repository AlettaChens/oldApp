package com.zencloud.wordchen.eldermanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.bean.Service;
import com.zencloud.wordchen.eldermanagement.utils.GlideX;

import java.util.List;

public class HotServiceAdapter extends BaseAdapter {
	private List<Service> serviceList;
	private Context context;

	public HotServiceAdapter(List<Service> serviceList, Context context) {
		this.serviceList = serviceList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return serviceList.size();
	}

	@Override
	public Object getItem(int i) {
		return serviceList.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		ViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.item_hot_part_list, viewGroup, false); //加载布局
			holder = new ViewHolder();
			holder.hot_service_pic = view.findViewById(R.id.hot_service_pic);
			holder.hot_service_name = view.findViewById(R.id.hot_service_name);
			holder.hot_service_price = view.findViewById(R.id.hot_service_price);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		Service service = serviceList.get(i);
		GlideX.getInstance().loadCircleImage(context, service.getService_image_url(), holder.hot_service_pic);
		holder.hot_service_name.setText(service.getService_name());
		holder.hot_service_price.setText("价格:" + service.getService_price());
		return view;
	}

	 private class ViewHolder {
		 ImageView hot_service_pic;
		 TextView hot_service_name;
		 TextView hot_service_price;
	}
}

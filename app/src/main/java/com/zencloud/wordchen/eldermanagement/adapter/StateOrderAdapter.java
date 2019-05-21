package com.zencloud.wordchen.eldermanagement.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.bean.Order;
import com.zencloud.wordchen.eldermanagement.utils.GlideX;

import java.util.List;

public class StateOrderAdapter extends RecyclerView.Adapter<StateOrderAdapter.ShopCartViewHolder> {

	private Context context;
	private List<Order> orders;

	public StateOrderAdapter(Context context, List<Order> orders) {
		this.context = context;
		this.orders = orders;
	}

	@NonNull
	@Override
	public ShopCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new ShopCartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order_list, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull ShopCartViewHolder holder, int position) {
		holder.update(orders.get(position), position);
	}

	@Override
	public int getItemCount() {
		return orders.size();
	}


	public class ShopCartViewHolder extends RecyclerView.ViewHolder {
		private int position;
		private ImageView iv_wash_clothes;
		private TextView tv_service_name;
		private TextView tv_service_price;

		public ShopCartViewHolder(View itemView) {
			super(itemView);
			iv_wash_clothes = itemView.findViewById(R.id.iv_wash_clothes);
			tv_service_name = itemView.findViewById(R.id.tv_service_name);
			tv_service_price = itemView.findViewById(R.id.tv_service_price);
		}

		public void update(final Order order, final int position) {
			this.position = position;
			GlideX.getInstance().loadImage(context, order.getService_image_url(), iv_wash_clothes);
			tv_service_name.setText(order.getService_name());
			tv_service_price.setText(Html.fromHtml("价格:<font color='#FF0000'>" + String.valueOf(order.getPrice() + "</font>")));
		}
	}
}

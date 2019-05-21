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
import com.zencloud.wordchen.eldermanagement.bean.ShoppingCar;
import com.zencloud.wordchen.eldermanagement.utils.GlideX;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ShopCartViewHolder> {
	private Context context;
	private List<ShoppingCar> shoppingCars;

	public OrderListAdapter(Context context, List<ShoppingCar> shoppingCars) {
		this.context = context;
		this.shoppingCars = shoppingCars;
	}

	@NonNull
	@Override
	public ShopCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new ShopCartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order_list, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull ShopCartViewHolder holder, int position) {
		holder.update(shoppingCars.get(position), position);
	}

	@Override
	public int getItemCount() {
		return shoppingCars.size();
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

		public void update(final ShoppingCar shoppingCar, final int position) {
			this.position = position;
			GlideX.getInstance().loadCircleImage(context, shoppingCar.getService_image_url(), iv_wash_clothes);
			tv_service_name.setText(shoppingCar.getService_name());
			tv_service_price.setText(Html.fromHtml("价格:<font color='#FF0000'>" + String.valueOf(shoppingCar.getPrice() + "</font>")));
		}
	}
}


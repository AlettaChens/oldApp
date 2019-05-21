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
import com.zencloud.wordchen.eldermanagement.bean.Service;
import com.zencloud.wordchen.eldermanagement.utils.GlideX;

import java.util.List;

public class TypeServiceAdapter extends RecyclerView.Adapter<TypeServiceAdapter.TypeServiceHolder> {
	private Context context;
	private List<Service> serviceList;
	private OnTypeItemClickHandle onTypeItemClickHandle;

	public TypeServiceAdapter(Context context, List<Service> serviceList) {
		this.context = context;
		this.serviceList = serviceList;
	}

	@NonNull
	@Override
	public TypeServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new TypeServiceHolder(LayoutInflater.from(context).inflate(R.layout.item_type_service, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull TypeServiceHolder holder, int position) {
		holder.update(serviceList.get(position), position);
	}

	@Override
	public int getItemCount() {
		return serviceList.size();
	}

	public class TypeServiceHolder extends RecyclerView.ViewHolder {
		private ImageView iv_type_service_pic;
		private ImageView addShopCart;
		private TextView service_name;
		private TextView service_price;
		private int position;

		public TypeServiceHolder(View itemView) {
			super(itemView);
			iv_type_service_pic = itemView.findViewById(R.id.iv_type_service_pic);
			addShopCart = itemView.findViewById(R.id.addShopCart);
			service_name = itemView.findViewById(R.id.service_name);
			service_price = itemView.findViewById(R.id.service_price);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (onTypeItemClickHandle != null) {
						onTypeItemClickHandle.showItemDetail(serviceList.get(position));
					}
				}
			});
		}

		public void update(final Service service, int position) {
			this.position = position;
			if (service.getService_image_url() != null) {
				GlideX.getInstance().loadImage(context, service.getService_image_url(), iv_type_service_pic);
			}
			service_name.setText(service.getService_name());
			service_price.setText(Html.fromHtml("价格:<font color='#FF0000'>" + String.valueOf(service.getService_price() + "</font>")));
			addShopCart.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (onTypeItemClickHandle != null) {
						onTypeItemClickHandle.addShopCart(service);
					}
				}
			});
		}
	}

	public void setOnTypeItemClickHandle(OnTypeItemClickHandle onTypeItemClickHandle) {
		this.onTypeItemClickHandle = onTypeItemClickHandle;
	}

	public interface OnTypeItemClickHandle {
		void addShopCart(@NonNull Service service);

		void showItemDetail(@NonNull Service service);
	}
}

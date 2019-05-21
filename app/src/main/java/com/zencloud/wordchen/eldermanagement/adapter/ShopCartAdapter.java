package com.zencloud.wordchen.eldermanagement.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.transition.Scene;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.bean.ShoppingCar;
import com.zencloud.wordchen.eldermanagement.utils.GlideX;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.ShopCartViewHolder> {
	private Context context;
	private List<ShoppingCar> shoppingCars;
	private ShopCarthandler shopCarthandler;

	public ShopCartAdapter(Context context, List<ShoppingCar> shoppingCars, ShopCarthandler shopCarthandler) {
		this.context = context;
		this.shoppingCars = new ArrayList<>();
		if (shoppingCars != null) {
			this.shoppingCars.addAll(shoppingCars);
		}
		this.shopCarthandler = shopCarthandler;
	}

	@NonNull
	@Override
	public ShopCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new ShopCartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shoppingcar, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull ShopCartViewHolder holder, int position) {
		holder.update(shoppingCars.get(position), position);
	}

	@Override
	public int getItemCount() {
		return shoppingCars.size();
	}


	public void allSelect(boolean flag) {
		for (int i = 0; i < shoppingCars.size(); i++) {
			shoppingCars.get(i).setSelect(flag);
		}
		shopCarthandler.updateToatalCash(compute());
		notifyDataSetChanged();
	}


	private boolean isAllCheck() {
		for (int i = 0; i < shoppingCars.size(); i++) {
			if (!shoppingCars.get(i).isSelect()) {
				return false;
			}
		}
		return true;
	}

	private float compute() {
		int price = 0;
		for (int i = 0; i < shoppingCars.size(); i++) {
			if (shoppingCars.get(i).isSelect()) {
				price += shoppingCars.get(i).getPrice();
			}
		}
		return price;
	}

	public boolean isEmpty() {
		return shoppingCars.isEmpty();
	}

	public boolean add(ShoppingCar item) {
		boolean success = shoppingCars.add(item);
		notifyDataSetChanged();
		return success;
	}

	public List<ShoppingCar> getselectShopCart() {
		List<ShoppingCar> shoppingCarstemp = new ArrayList<>();
		for (ShoppingCar shoppingCar : shoppingCars) {
			if (shoppingCar.isSelect()) {
				shoppingCarstemp.add(shoppingCar);
			}
		}
		return shoppingCarstemp;
	}

	public boolean remove(ShoppingCar item) {
		boolean success = shoppingCars.remove(item);
		notifyDataSetChanged();
		return success;
	}

	public boolean addAll(@NonNull Collection<? extends ShoppingCar> c) {
		boolean success = shoppingCars.addAll(c);
		notifyDataSetChanged();
		return success;
	}

	public void clear() {
		shoppingCars.clear();
		notifyDataSetChanged();
	}

	public void add(int index, ShoppingCar element) {
		shoppingCars.add(index, element);
		notifyDataSetChanged();
	}

	public ShoppingCar remove(int index) {
		ShoppingCar item = shoppingCars.remove(index);
		notifyDataSetChanged();
		return item;
	}

	public int indexOf(Scene item) {
		return shoppingCars.indexOf(item);
	}

	public class ShopCartViewHolder extends RecyclerView.ViewHolder {
		private int position;
		private CheckBox single_check;
		private ImageView iv_wash_clothes;
		private TextView tv_service_name;
		private TextView tv_service_price;
		private TextView tv_delete;

		public ShopCartViewHolder(View itemView) {
			super(itemView);
			single_check = itemView.findViewById(R.id.single_check);
			iv_wash_clothes = itemView.findViewById(R.id.iv_wash_clothes);
			tv_service_name = itemView.findViewById(R.id.tv_service_name);
			tv_service_price = itemView.findViewById(R.id.tv_service_price);
			tv_delete = itemView.findViewById(R.id.tv_delete);
		}

		public void update(final ShoppingCar shoppingCar, final int position) {
			this.position = position;
			GlideX.getInstance().loadCircleImage(context, shoppingCar.getService_image_url(), iv_wash_clothes);
			tv_service_name.setText(shoppingCar.getService_name());
			single_check.setChecked(shoppingCar.isSelect());
			tv_service_price.setText(Html.fromHtml("价格:<font color='#FF0000'>" + String.valueOf(shoppingCar.getPrice() + "</font>")));
			tv_delete.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (shopCarthandler != null) {
						shopCarthandler.deleteShopCart(shoppingCars.get(position));
					}
				}
			});

			single_check.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					shoppingCar.setSelect(single_check.isChecked());
					shopCarthandler.updateToatalCash(compute());
					if (single_check.isChecked()) {
						if (isAllCheck()) {
							shopCarthandler.isAllSelect(true);
						} else {
							shopCarthandler.isAllSelect(false);
						}
					} else {
						shopCarthandler.isAllSelect(false);
					}
					notifyDataSetChanged();
				}
			});
		}
	}

	public interface ShopCarthandler {
		void updateToatalCash(float totalPrice);

		void isAllSelect(boolean selectAll);

		void deleteShopCart(ShoppingCar shoppingCar);
	}


	public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
		private int verticalSpacing;

		public GridSpacingItemDecoration(int verticalSpacing) {
			this.verticalSpacing = verticalSpacing;
		}

		@Override
		public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
			outRect.left = 0;
			outRect.right = 0;
			outRect.top = verticalSpacing;
			outRect.bottom = verticalSpacing;
		}
	}
}

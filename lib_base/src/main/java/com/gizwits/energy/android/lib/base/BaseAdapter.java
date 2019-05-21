package com.gizwits.energy.android.lib.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;


import org.xutils.x;

import java.util.List;


public abstract class BaseAdapter<T> extends EasyAdapter<T> {
	public BaseAdapter(Context context) {
		super(context);
	}

	public BaseAdapter(Context context, List<T> items) {
		super(context, items);
	}

	public abstract class xViewHolder extends ViewHolder {
		@Override
		protected View init(LayoutInflater layoutInflater) {
			View view = initView(layoutInflater);
			x.view().inject(this, view);
			return view;
		}

		protected abstract View initView(LayoutInflater layoutInflater);
	}

}

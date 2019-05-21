package com.gizwits.energy.android.lib.utils;

import android.view.View;
import android.view.ViewGroup;

import com.gizwits.energy.android.lib.base.AbstractConstantClass;


public class ViewHelper extends AbstractConstantClass {
	public static void setViewGroupSelected(ViewGroup viewGroup, boolean selected) {
		viewGroup.setSelected(selected);
		int count = viewGroup.getChildCount();
		for (int i = 0; i < count; i++) {
			View view = viewGroup.getChildAt(i);
			view.setSelected(selected);
		}
	}
}

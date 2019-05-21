package com.gizwits.energy.android.lib.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


public class ScreenUtil {
	/**
	 * 计算inflate过来的view的高和宽，计算完之后可以调用child.getMeasuredHeight()getMeasuredWidth()获取view内部控件的宽和高
	 *
	 * @param child
	 */
	public static void measureView(View child) {
		ViewGroup.LayoutParams lp = child.getLayoutParams();
		if (lp == null) {
			lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		//headerView的宽度信息
		int childMeasureWidth = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
		int childMeasureHeight;
		if (lp.height > 0) {
			childMeasureHeight = View.MeasureSpec.makeMeasureSpec(lp.height, View.MeasureSpec.EXACTLY);
			//最后一个参数表示：适合、匹配
		} else {
			childMeasureHeight = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);//未指定
		}
		//将宽和高设置给child
		child.measure(childMeasureWidth, childMeasureHeight);
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}

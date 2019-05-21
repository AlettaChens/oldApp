package com.gizwits.energy.android.lib.pulltorefresh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PullableSlideListView extends PullableListView {

    private static final String TAG = "ListViewCompat";
    private int downx, upx;
    private SlideView mFocusedItemView;

    public PullableSlideListView(Context context) {
        super(context);
    }

    public PullableSlideListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableSlideListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void shrinkListItem(int position) {
        View item = getChildAt(position);

        if (item != null) {
            try {
                ((SlideView) item).shrink();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downx = (int) event.getX();
                int x = (int) event.getX();
                int y = (int) event.getY();
                //获取第一个可见的item位置
                int firstVisiblePosition = getFirstVisiblePosition();
                //获取当前item的position
                int position = pointToPosition(x, y);
                //计算当前item所在列表可见的位置
                position = position - firstVisiblePosition;
                Log.e(TAG, "postion=" + position);
                if (position != INVALID_POSITION && getChildAt(position) instanceof SlideView) {
                    //将当前item转为SlideView
                    mFocusedItemView = (SlideView) getChildAt(position);
                    Log.e(TAG, "FocusedItemView=" + mFocusedItemView);
                }
                break;
            case MotionEvent.ACTION_UP:
                upx = (int) event.getX();
                if (Math.abs(downx - upx) > 100) {
                    if (mFocusedItemView != null) {
                        // 响应滑动
                        mFocusedItemView.onRequireTouchEvent(event);
                    }
                    return false;
                }
                break;
            default:
                break;
        }

        if (mFocusedItemView != null) {
            //响应滑动
            mFocusedItemView.onRequireTouchEvent(event);
        }

        return super.onTouchEvent(event);
    }

}

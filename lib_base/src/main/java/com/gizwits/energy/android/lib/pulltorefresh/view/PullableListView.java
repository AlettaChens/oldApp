package com.gizwits.energy.android.lib.pulltorefresh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.gizwits.energy.android.lib.pulltorefresh.Pullable;
import com.gizwits.energy.android.lib.pulltorefresh.RefreshMode;


public class PullableListView extends ListView implements Pullable {

    private int refreshMode = 0;

    public PullableListView(Context context) {
        super(context);
    }

    public PullableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRefreshMode(int refreshMode) {
        this.refreshMode = refreshMode;
    }

    @Override
    public boolean canPullDown() {
        //只是头部刷新
        if (refreshMode == RefreshMode.BOTTOM || refreshMode == RefreshMode.NONE) {
            return false;
        }

        if (getCount() == 0) {
            // 没有item的时候也可以下拉刷新
            return true;
        } else if (getFirstVisiblePosition() == 0 && getChildAt(0) != null && getChildAt(0).getTop() >= 0) {
            // 滑到ListView的顶部了
            return true;
        } else
            return false;
    }

    @Override
    public boolean canPullUp() {
        //只是底部刷新
        if (refreshMode == RefreshMode.TOP || refreshMode == RefreshMode.NONE) {
            return false;
        }

        if (getCount() == 0) {
            // 没有item的时候也可以上拉加载
            return true;
        } else if (getLastVisiblePosition() == (getCount() - 1)) {
            // 滑到底部了
            if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
                    && getChildAt(
                    getLastVisiblePosition()
                            - getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
                return true;
        }
        return false;
    }
}

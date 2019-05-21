package com.gizwits.energy.android.lib.pulltorefresh.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.gizwits.energy.android.lib.pulltorefresh.Pullable;
import com.gizwits.energy.android.lib.pulltorefresh.RefreshMode;


public class PullableRecyclerView extends RecyclerView implements Pullable {

    private LinearLayoutManager linearLayoutManager;

    private int refreshMode = RefreshMode.BOTH;

    public PullableRecyclerView(Context context) {
        super(context);
    }

    public PullableRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRefreshMode(int refreshMode) {
        this.refreshMode = refreshMode;
    }

    @Override
    public boolean canPullDown() {
        getLinearLayoutManager();
        if (linearLayoutManager == null) {
            //非线性布局的不支持上下拉
            return false;
        }
        //只是头部刷新
        if (refreshMode == RefreshMode.BOTTOM || refreshMode == RefreshMode.NONE) {
            return false;
        }

        if (linearLayoutManager.getItemCount() == 0) {
            // 没有item的时候也可以下拉刷新
            return true;
        } else if (linearLayoutManager.findFirstVisibleItemPosition() == 0 && getChildAt(0) != null && getChildAt(0).getTop() >= 0) {
            // 滑到ListView的顶部了
            return true;
        } else
            return false;
    }

    @Override
    public boolean canPullUp() {
        getLinearLayoutManager();
        if (linearLayoutManager == null) {
            //非线性布局的不支持上下拉
            return false;
        }
        //只是底部部刷新
        if (refreshMode == RefreshMode.TOP || refreshMode == RefreshMode.NONE) {
            return false;
        }

        if (linearLayoutManager.getItemCount() == 0) {
            // 没有item的时候也可以上拉加载
            return true;
        } else if (linearLayoutManager.findLastVisibleItemPosition() == (linearLayoutManager.getItemCount() - 1)) {
            // 滑到底部了
            if (getChildAt(linearLayoutManager.findLastVisibleItemPosition() - linearLayoutManager.findFirstVisibleItemPosition()) != null
                    && getChildAt(linearLayoutManager.findLastVisibleItemPosition() - linearLayoutManager.findFirstVisibleItemPosition()).getBottom() <= getMeasuredHeight())
                return true;
        }
        return false;
    }

    public LinearLayoutManager getLinearLayoutManager() {
        if (linearLayoutManager == null) {
            try {
                linearLayoutManager = (LinearLayoutManager) getLayoutManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return linearLayoutManager;
    }
}

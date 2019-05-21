package com.gizwits.energy.android.lib.pulltorefresh.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.gizwits.energy.android.lib.pulltorefresh.Pullable;
import com.gizwits.energy.android.lib.pulltorefresh.RefreshMode;


public class PullableTextView extends TextView implements Pullable {

    private int refreshMode = 0;

    public PullableTextView(Context context) {
        super(context);
    }

    public PullableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableTextView(Context context, AttributeSet attrs, int defStyle) {
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
        return true;
    }

    @Override
    public boolean canPullUp() {
        //只是底部刷新
        if (refreshMode == RefreshMode.TOP || refreshMode == RefreshMode.NONE) {
            return false;
        }
        return true;
    }

}

package com.gizwits.energy.android.lib.pulltorefresh.view;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.gizwits.energy.android.lib.utils.ScreenUtil;


public class SlideView extends LinearLayout {

    private static final String TAG = "SlideView";

    private Context mContext;
    private LinearLayout mViewContent;
    private Scroller mScroller;
    private OnSlideListener mOnSlideListener;

    private int mHolderWidth;
    private boolean isCanSlide;
    private int mLastX = 0;
    private int mLastY = 0;
    private static final int TAN = 2;

    public interface OnSlideListener {
        public static final int SLIDE_STATUS_OFF = 0;
        public static final int SLIDE_STATUS_START_SCROLL = 1;
        public static final int SLIDE_STATUS_ON = 2;

        /**
         * @param view   current SlideView
         * @param status SLIDE_STATUS_ON or SLIDE_STATUS_OFF
         */
        public void onSlide(View view, int status);
    }

    public SlideView(Context context, View itemView, View slideview) {
        super(context);
        initView(itemView, slideview);
    }

    public SlideView(Context context, AttributeSet attrs, View itemView, View slideview) {
        super(context, attrs);
        initView(itemView, slideview);
    }

    private void initView(View itemView, View slideview) {
        mContext = getContext();
        mScroller = new Scroller(mContext);
        setOrientation(LinearLayout.HORIZONTAL);
        removeAllViews();
        // item 布局
        itemView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(itemView);
        // slide菜单布局
        ScreenUtil.measureView(slideview);
        mHolderWidth = slideview.getMeasuredWidth();
        slideview.setLayoutParams(new LayoutParams(slideview.getMeasuredWidth(), LayoutParams.MATCH_PARENT));
        addView(slideview);
    }

    public void setIsCanSlide(boolean value) {
        isCanSlide = value;
    }

    /**
     * 滑动监听
     *
     * @param onSlideListener
     */
    public void setOnSlideListener(OnSlideListener onSlideListener) {
        mOnSlideListener = onSlideListener;
    }

    public void shrink() {
        if (getScrollX() != 0) {
            this.smoothScrollTo(0, 0);
        }
    }

    public void onRequireTouchEvent(MotionEvent event) {
        if (!isCanSlide) {
            return;
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        int scrollX = getScrollX();
        Log.d(TAG, "x=" + x + "  y=" + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //如果屏幕的动画还没结束，就按下了，结束上一次动画，即开始这次新ACTION_DOWN的动画
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                if (mOnSlideListener != null) {
                    mOnSlideListener.onSlide(this,
                            OnSlideListener.SLIDE_STATUS_START_SCROLL);
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;//每次滑动屏幕，屏幕应该移动的距离
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) < Math.abs(deltaY) * TAN) {//上下
                    break;
                }
                //计算移动距离
                int newScrollX = scrollX - deltaX;
                if (deltaX != 0) {
                    if (newScrollX < 0) {
                        newScrollX = 0;
                    } else if (newScrollX > mHolderWidth) {
                        newScrollX = mHolderWidth;
                    }
                    //响应位置移动
                    this.scrollTo(newScrollX, 0);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                int newScrollX = 0;
                //移动位置超过控件宽度的75%则响应移动
                if (scrollX - mHolderWidth * 0.75 > 0) {
                    newScrollX = mHolderWidth;
                }
                this.smoothScrollTo(newScrollX, 0);
                if (mOnSlideListener != null) {
                    mOnSlideListener.onSlide(this,
                            newScrollX == 0 ? OnSlideListener.SLIDE_STATUS_OFF
                                    : OnSlideListener.SLIDE_STATUS_ON);
                }
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
    }

    private void smoothScrollTo(int destX, int destY) {
        // 缓慢滚动到指定位置
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        //Scroller动画移动,由(startX , 0)在Math.abs(delta) * 3时间内前进(dx,dy)个单位，即到达坐标为(startX+dx , startY+dy)出  
        mScroller.startScroll(scrollX, 0, delta, 0, Math.abs(delta) * 3);
        //重新绘制
        invalidate();
    }

    @Override
    public void computeScroll() {
        //根据当前已经消逝的时间计算当前的坐标点
        if (mScroller.computeScrollOffset()) {
            // 产生动画效果，根据当前值 每次滚动一点
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //刷新View ，否则效果可能有误差  
            postInvalidate();
        }
    }

}

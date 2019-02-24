package cool.chinalanguagegame.android.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {

    private boolean mProhibitScroll = false;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public void setProhibitScroll(boolean prohibitScroll) {
        this.mProhibitScroll = prohibitScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
//        if (mProhibitScroll) {
//            return false;
//        } else {
//            try {
//                return super.onTouchEvent(arg0);
//            } catch (Exception e) {
//
//            }
//        }
//        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
//        if (mProhibitScroll) {
//            return false;
//        } else {
//            return super.onInterceptTouchEvent(arg0);
//        }
    }
}

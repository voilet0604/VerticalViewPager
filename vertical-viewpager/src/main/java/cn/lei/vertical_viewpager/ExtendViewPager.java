package cn.lei.vertical_viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 支持水平和垂直
 * 原生的ViewPager扩展 不支持内嵌 ListView scrollView等滑动组件
 */
public class ExtendViewPager extends ViewPager {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({HORIZONTAL, VERTICAL})
    public @interface Orientation{}

    private @Orientation int mSwipeOrientation;

    public ExtendViewPager(@NonNull Context context) {
        super(context);
        mSwipeOrientation = VERTICAL;
    }

    public ExtendViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ExtendViewPager);
        mSwipeOrientation = ta.getInt(R.styleable.ExtendViewPager_swipe_orientation, VERTICAL);
        ta.recycle();
        initSwipeMethods();
    }

    public void setSwipeOrientation(@Orientation int swipeOrientation) {
        if (swipeOrientation == HORIZONTAL || swipeOrientation == VERTICAL)
            mSwipeOrientation = swipeOrientation;
        else
            throw new IllegalStateException("Swipe Orientation can be either CustomViewPager.HORIZONTAL" +
                    " or CustomViewPager.VERTICAL");
        initSwipeMethods();
    }


    private void initSwipeMethods() {
        if (mSwipeOrientation == VERTICAL) {
            // The majority of the work is done over here
            setPageTransformer(true, new VerticalPageTransformer());
            // The easiest way to get rid of the overscroll drawing that happens on the left and right
            setOverScrollMode(OVER_SCROLL_NEVER);
        }
    }

    private class VerticalPageTransformer implements PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            if (position < -1) {
                // This page is way off-screen to the left
                page.setAlpha(0);
            } else if (position <= 1) {
                page.setAlpha(1);

                // Counteract the default slide transition
                page.setTranslationX(page.getWidth() * -position);

                // set Y position to swipe in from top
                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);
            } else {
                // This page is way off screen to the right
                page.setAlpha(0);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(mSwipeOrientation == VERTICAL? swapXY(ev): ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mSwipeOrientation == VERTICAL) {
            boolean intercepted = super.onInterceptHoverEvent(swapXY(event));
            swapXY(event);
            return intercepted;
        }
        return super.onInterceptTouchEvent(event);
    }

    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);
        return ev;
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if(v instanceof ScrollView) {
            return true;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }
}

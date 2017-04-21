package li.yohan.parallax;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

import li.yohan.library.R;

/**
 * Created by yohan on 2017/4/18.
 */

public class ParallaxViewPager extends ViewPager {

    private static final String TAG = "ParallaxViewPager";

    // bitmap source rect
    private Rect src = new Rect();
    // bitmap destination rect
    private Rect dst = new Rect();
    // background bitmap
    private Bitmap mBitmap;
    // bitmap each section width / pager page width
    private float ratio;
    // background bitmap move speed / page move speed
    private float speedRatio = DEFAULT_SPEED_RATIO;

    private static final float DEFAULT_SPEED_RATIO = 0.5f;

    // bitmap scale
    private float bitmapScale = 0.0f;

    private int oldCount = 0;

    public ParallaxViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (getBackground() != null) {
            if (getBackground() instanceof BitmapDrawable) {
                mBitmap = ((BitmapDrawable) getBackground()).getBitmap();
            }
        }
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable
                    .ParallaxViewPager, 0, 0);
            try {
                speedRatio = array.getFloat(R.styleable.ParallaxViewPager_speedRatio,
                        DEFAULT_SPEED_RATIO);
            } finally {
                array.recycle();
            }
        }
        initSource();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        addOnPageChangeListener(mOnPageChangeListener);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeOnPageChangeListener(mOnPageChangeListener);
    }

    SimpleOnPageChangeListener mOnPageChangeListener = new SimpleOnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            if (mBitmap != null) {
                float offset = (position + positionOffset) * getMeasuredWidth();
                src.left = ((int) (offset * ratio * speedRatio));
                src.right = (int) (src.left + getMeasuredWidth() / bitmapScale);

                dst.left = (int) offset;
                dst.right = dst.left + getMeasuredWidth();
                invalidate();
            }
        }
    };

    @Override
    public void setBackgroundResource(@DrawableRes int resid) {
        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(getResources(), resid);
            initSource();
        }
    }

    @Override
    public void setBackground(Drawable background) {
        if (mBitmap == null) {
            mBitmap = ((BitmapDrawable) background).getBitmap();
            initSource();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (getAdapter() != null && getAdapter().getCount() > 0 && mBitmap != null) {
            int curCount = getAdapter().getCount();
            if (curCount != oldCount) {
                calculateRatio();
            }
            oldCount = curCount;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // when size changed reConfig destination rect
        dst.left = 0;
        dst.right = w;
        dst.top = 0;
        dst.bottom = h;
    }

    /**
     * calculate the size and ratio after bitmap stretching
     */
    private void calculateRatio() {
        if (mBitmap.getWidth() < getMeasuredWidth()) {
            Log.w(TAG, "Invalidate background bitmap, bitmap width must larger than ViewPager width");
        }

        bitmapScale = ((float) getMeasuredHeight()) / ((float) mBitmap.getHeight());
        src.right = (int) (getMeasuredWidth() / bitmapScale);

        int sectionWidth = mBitmap.getWidth() / getAdapter().getCount();
        ratio = ((float) sectionWidth) / ((float) getMeasuredWidth());

        // final source rect right edge
        int finalSourceRight = (int) (getMeasuredWidth() / bitmapScale + ((getAdapter().getCount
                () - 1) * getMeasuredWidth() * ratio * speedRatio));
        if (finalSourceRight > mBitmap.getWidth()) {
            // lager than bitmap width then reset speedRatio
            speedRatio = (mBitmap.getWidth() - getMeasuredWidth() / bitmapScale) / ((getAdapter()
                    .getCount() - 1) * getMeasuredWidth() * ratio);
            speedRatio = speedRatio <= 0 ? 1 : speedRatio;
        }
    }

    /**
     * init viewpager configuration
     */
    private void initSource() {
        if (mBitmap != null) {
            src = new Rect();
            src.top = 0;
            src.bottom = mBitmap.getHeight();
            src.left = 0;
        }
    }

    /**
     * get background move speed ratio
     *
     * @return speed ratio
     */
    public float getSpeedRatio() {
        return speedRatio;
    }

    /**
     * set background move speed ratio the lager the value the slower the speed vice versa, the
     * value must be between 0 and 1
     *
     * @param speedRatio
     */
    public void setSpeedRatio(float speedRatio) {
        if (speedRatio <= 0 || speedRatio >= 1)
            throw new IllegalArgumentException("Illegal argument: speedRatio must be between 0 " +
                    "and 1");
        this.speedRatio = speedRatio;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, src, dst, null);
        }
    }
}

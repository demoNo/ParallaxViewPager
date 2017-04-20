package li.yohan.parallaxviewpager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.salvage.RecyclingPagerAdapter;

import li.yohan.parallax.ParallaxViewPager;

public class MainActivity extends AppCompatActivity {

    private ParallaxViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = ((ParallaxViewPager) findViewById(R.id.pager));
        mPager.setAdapter(new Adapter());
    }

    class Adapter extends RecyclingPagerAdapter {

        private int[] data;

        public Adapter() {
            data = new int[]{0, 1, 2};
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new TextView(container.getContext());
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup
                        .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                ((TextView) convertView).setGravity(Gravity.CENTER);
                ((TextView) convertView).setTextSize(TypedValue.COMPLEX_UNIT_SP, 60);
                ((TextView) convertView).setTypeface(null, Typeface.BOLD);
                ((TextView) convertView).setTextColor(Color.parseColor("#3c3c3c"));
                convertView.setLayoutParams(layoutParams);
            }
            ((TextView) convertView).setText(position + "");
            return convertView;
        }

        @Override
        public int getCount() {
            return data.length;
        }
    }

}

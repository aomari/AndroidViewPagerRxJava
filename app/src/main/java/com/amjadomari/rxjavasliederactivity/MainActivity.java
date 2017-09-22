package com.amjadomari.rxjavasliederactivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.amjadomari.rxjavasliederactivity.customs.CustomScroller;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static com.amjadomari.rxjavasliederactivity.Constants.TRANSFORM_CLASSES;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.layoutBack)
    FrameLayout back;
    @BindView(R.id.layoutForeword)
    FrameLayout foreword;
    @BindView(R.id.viewPagerCountDots)
    LinearLayout pagerIndicator;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.SpeedRadioGroup)
    RadioGroup speed;

    private int dotsCount;
    private ImageView[] dots;

    private int[] images = {
            R.mipmap.pic_01, R.mipmap.pic_02, R.mipmap.pic_03, R.mipmap.pic_04,
            R.mipmap.pic_05, R.mipmap.pic_06, R.mipmap.pic_07, R.mipmap.pic_08};

    private ViewPagerAdapter pagerAdapter;
    private PublishSubject<Long> newInterval;
    private Long period = 5000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        pagerAdapter = new ViewPagerAdapter(this, images);
        viewPager.setAdapter(pagerAdapter);

        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);

        initSpeedListener();

        prepareSpinner();

        prepareObserver();

        setUiPageViewController();

        setCustomScrollerToViewPager();
    }

    private void initSpeedListener() {
        speed.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.slow:
                    period = 8000L;
                    newInterval.onNext(period);
                    break;
                case R.id.medium:
                    period = 5000L;
                    newInterval.onNext(period);
                    break;
                case R.id.fast:
                    period = 2000L;
                    newInterval.onNext(period);
                    break;
            }
        });
    }

    // reactive programming using RXJava2
    private void prepareObserver() {
        newInterval = PublishSubject.create();

        newInterval.switchMap(currentPeriod ->
                Observable.interval(currentPeriod, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.single())
        )
                .doOnNext(v -> runOnUiThread(this::pagerForeword))
                .subscribe();

        newInterval.onNext(period);
    }

    private void prepareSpinner() {
        final ArrayAdapter<TransformerItem> actionBarAdapter = new ArrayAdapter<>(

                getApplicationContext(), R.layout.spinner_dropdown, android.R.id.text1, TRANSFORM_CLASSES);

        Spinner navigationSpinner = new Spinner(getSupportActionBar().getThemedContext());

        navigationSpinner.setAdapter(actionBarAdapter);

        toolbar.addView(navigationSpinner, 0);

        // Handling Spinner item click listener
        // Set selected animation in viewpager
        // viewPager.setPageTransformer
        navigationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    viewPager.setPageTransformer(true, TRANSFORM_CLASSES.get(position).getClazz().newInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setUiPageViewController() {

        dotsCount = pagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pagerIndicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @OnClick(R.id.layoutForeword)
    void pagerForeword() {
        newInterval.onNext(period);
        if (viewPager.getCurrentItem() < images.length - 1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        } else {
            viewPager.setCurrentItem(0);
        }
    }

    @OnClick(R.id.layoutBack)
    void pagerBack() {
        newInterval.onNext(period);
        if (viewPager.getCurrentItem() == 0) {
            viewPager.setCurrentItem(images.length - 1);
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    // change the view pager scrolling time period
    private void setCustomScrollerToViewPager() {
        Interpolator interpolator = new LinearInterpolator();
        try {
            CustomScroller customScroller = new CustomScroller(this, interpolator, 500);
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mScroller.set(viewPager, customScroller);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

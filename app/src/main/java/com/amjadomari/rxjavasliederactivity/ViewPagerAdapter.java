package com.amjadomari.rxjavasliederactivity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by aomari on 12-09-2017.
 * ViewPagerAdapter
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int[] mResources;

    ViewPagerAdapter(Context context, int[] resources) {
        this.mContext = context;
        this.mResources = resources;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.view_pager_item, container, false);

        ImageView imageView = itemView.findViewById(R.id.image);
        Glide.with(mContext)
                .load(mResources[position])
                .into(imageView);

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
package com.paopao.newservlet.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.paopao.newservlet.R;
import com.paopao.newservlet.engine.AdapterCallback;

import java.util.ArrayList;

/**
 * 作者：paopao on 2018/12/28 19:56
 * <p>
 * 作用: 头条 轮播图的Viewpager 适配器
 */
public class RotationChartPagerAdapter extends PagerAdapter implements View.OnClickListener {

    //private ArrayList<ImageView> imageViews;
    //private int imageIds[]={R.drawable.rotation_chart_1,R.drawable.rotation_chart_2,R.drawable.rotation_chart_3};
   // private ArrayList<Drawable> images;
    private Drawable[] images;
    private LayoutInflater inflater;
    private Context context;
    private AdapterCallback adapterCallback;

    public RotationChartPagerAdapter(Drawable[] images, Context context) {
        //this.imageViews = imageViews;
        this.images=images;
        inflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // position是int最大值所以这里可能是几百甚至上千,因此取余避免数组越界

        int newPosition = position % images.length;
        View convertView = inflater.inflate(R.layout.rotation_chart, container, false);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
        imageView.setImageDrawable(images[newPosition]);

        imageView.setOnClickListener(this);
        imageView.setTag(newPosition);
        //ImageView v=images.get(newPosition);
        container.addView(convertView);

        return convertView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setAdapterCallback(AdapterCallback adapterCallback){
        this.adapterCallback=adapterCallback;
    }

    @Override
    public void onClick(View v) {
        adapterCallback.adapterOnClick(v);
    }
}

package com.paopao.newservlet.ui.childFragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.paopao.newservlet.R;
import com.paopao.newservlet.callback.OnResponseListener;
import com.paopao.newservlet.engine.AdapterCallback;
import com.paopao.newservlet.request.GetRotaionChartRequest;
import com.paopao.newservlet.request.GetRotationChartInfoRequest;
import com.paopao.newservlet.ui.activity.WebViewActivity;
import com.paopao.newservlet.ui.adapter.RotationChartPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：paopao on 2018/12/5 21:37
 * <p>
 * 作用:头条Fragment
 */
public class TouTiaoFragment extends BaseFragment implements ViewPager.OnPageChangeListener ,AdapterCallback {


    //private int imageIds[]={R.drawable.rotation_chart_1,R.drawable.rotation_chart_2,R.drawable.rotation_chart_3};
    private Drawable[] images=new Drawable[3];
    //private ArrayList<Drawable> images;
    private ArrayList<ImageView> points;
    //private ArrayList<ImageView> imageViews;

    private RelativeLayout rl_layout;
    private ViewPager rotation_chart_view_pager;
    private LinearLayout dots_layout;
    private View view;

    private Handler handler_url = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 3:
                    initData();
                    startRotate();
                    break;
            }

            return false;
        }
    });
    private Runnable rotateRunnable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view=super.onCreateView(inflater, container, savedInstanceState);


         getImageUrl();
        init();
        //initImageViews();



//        view_flipper.setVisibility(View.VISIBLE);
//        view_flipper.addView(addImageView(R.drawable.login_sina));
//        view_flipper.addView(addImageView(R.drawable.login_qq));
//        view_flipper.addView(addImageView(R.drawable.login_wechat));
        return view;
    }

    private void getImageUrl(){
        new GetRotaionChartRequest().sendRequest(new OnResponseListener() {
            @Override
            public void onResponse(Object result) {
                List<String> list_urls=(ArrayList<String>)result;
                for(int i=0;i<list_urls.size();i++){
                    initImageViews(list_urls.get(i),i);
                }



//                try {
//                    //Log.d("hsh","JSONArray.length"+result.toString());
//                    //JSONArray array=new JSONArray(result);
//                   // Log.d("hsh","JSONArray.length"+array.length());
//
//                } catch (JSONException e) {
//                    Log.d("hsh","JSONException="+e.getMessage());
//                    e.printStackTrace();
//                }
            }
        });
    }

    //网络请求获取url的图片，初始化imageViews
    private void initImageViews(final String image_url, final int position){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Drawable drawable=loadImageFromNetwork(image_url);
                    images[position]=drawable;
                    if(position==images.length-1){
                        Message message=new Message();
                        message.what=3;
                        handler_url.sendMessage(message);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Drawable loadImageFromNetwork(String url) throws MalformedURLException {
        Drawable drawable=null;
        try {
            drawable=Drawable.createFromStream(new URL(url).openStream(),"rotation_chart");
        } catch (IOException e) {
            Log.d("paopao","IOException="+e.getMessage());
            e.printStackTrace();
        }
        return drawable;
    }

    private void init(){
        rl_layout=view.findViewById(R.id.rl_picturePlay);
        rotation_chart_view_pager=view.findViewById(R.id.rotation_chart_view_pager);
        dots_layout=view.findViewById(R.id.ll_points);
    }

    private void initData(){
        rl_layout.setVisibility(View.VISIBLE);

        Log.d("hsh","*************************initData");
        //imageViews=new ArrayList<>();
        points=new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            //ImageView image = new ImageView(getContext());
            //image.setBackgroundResource(imageIds[i]);
            //imageViews.add(image);

            //创建点
            ImageView point = new ImageView(getContext());
            point.setBackgroundResource(R.drawable.shape_point_normal);
            if(i ==0){
                point.setBackgroundResource(R.drawable.shape_point_red);
            }
            points.add(point);

            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            point.setLayoutParams(params);
            if(i!=0){
                //不包括第0个点，所有点距离左边有10个像素
                params.leftMargin=20;
            }
            //添加到线性布局里面
            dots_layout.addView(point);
        }

        RotationChartPagerAdapter pagerAdapter=new RotationChartPagerAdapter(images,getContext());
        pagerAdapter.setAdapterCallback(this);
        rotation_chart_view_pager.setAdapter(pagerAdapter);
        // ViewPager的页数为int最大值,设置当前页多一些,可以上来就向前滑动
        // 为了保证第一页始终为数据的第0条 取余要为0,因此设置数据集合大小的倍数
        rotation_chart_view_pager.setCurrentItem(images.length * 100);
        rotation_chart_view_pager.addOnPageChangeListener(this);
    }

//    private View addImageView(int id){
//        ImageView imageView=new ImageView(getActivity());
//        imageView.setBackgroundResource(id);
//        return imageView;
//    }

    private void startRotate() {
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = rotation_chart_view_pager.getCurrentItem();
                rotation_chart_view_pager.setCurrentItem(++nowIndex);

                handler_url.postDelayed(rotateRunnable, 5000);
            }
        };
        handler_url.postDelayed(rotateRunnable, 5000);
    }


    @Override
    protected String getType() {
        return "toutiao";
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        for(int j=0;j<images.length; j++){
            if(j==i % images.length){
                points.get(j).setBackgroundResource(R.drawable.shape_point_red);
            }else {
                points.get(j).setBackgroundResource(R.drawable.shape_point_normal);
            }
        }
        //Toast.makeText(getContext(),"position"+(i % images.length),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void adapterOnClick(View v) {
        getRotationChartNew(String.valueOf(v.getTag()));
    }

    //获取轮播图新闻的url
    private void getRotationChartNew(String position){
        new GetRotationChartInfoRequest().setParams(new String[]{position+1}).sendRequest(new OnResponseListener() {
            @Override
            public void onResponse(Object result) {
                Intent intent=new Intent();
                intent.putExtra("URL",(String)result);
                intent.setClass(getContext(),WebViewActivity.class);
                startActivity(intent);
            }
        });
    }

}

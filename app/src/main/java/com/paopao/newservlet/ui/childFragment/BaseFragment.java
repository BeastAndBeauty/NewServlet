package com.paopao.newservlet.ui.childFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.paopao.newservlet.R;
import com.paopao.newservlet.domain.NewBean;
import com.paopao.newservlet.engine.NetWork;
import com.paopao.newservlet.ui.adapter.NewsListAdapter;
import com.paopao.newservlet.ui.activity.WebViewActivity;
import com.paopao.newservlet.utils.RefreshLayout;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

   private RefreshLayout refreshLayout;
   private ListView listView;
    private ImageView ivError;//加载失败显示的图片
    private ProgressBar progressBar;//第一次加载时显示的圆形进度条
    private NewsListAdapter adapter;//ListView适配器
    private List<NewBean.DataBean> datas;//加载的数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_base, container, false);
        refreshLayout=view.findViewById(R.id.refreshLayout);
        listView=view.findViewById(R.id.list_view);
        ivError=view.findViewById(R.id.iv_error);
        progressBar=view.findViewById(R.id.progress_bar);
        initView();
        initData();
        return view;
    }

    private void initData(){
        progressBar.setVisibility(View.VISIBLE);
        ivError.setVisibility(View.GONE);//隐藏错误消息
        refesh();//刷新数据

    }

    private void initView(){
        //给ListView设置适配器，从而显示到界面上
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //模拟网络交互
                refesh();
            }
        });

        //没有更多数据
        refreshLayout.setHasMore(false);
        //加载失败，点击重连
        ivError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });

        //ListView条目的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewBean.DataBean dataBean=datas.get(position);
                Intent intent=new Intent();
                intent.putExtra("URL",dataBean.getUrl());
                Log.d("URL****",dataBean.getUrl());
                intent.setClass(getContext(),WebViewActivity.class);
                startActivity(intent);

//                Intent intent= WebViewActivity.newIntent(getContext(),dataBean.getUrl());
//                getActivity().startActivity(intent);
            }
        });
//
    }

    private void refesh(){
        //top对应的是头条新闻
        NetWork.createApi().getNews(getType())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewBean>() {
                    @Override
                    public void call(NewBean newBean) {
                        refreshLayout.setEnabled(true);//允许下拉刷新
                        progressBar.setVisibility(View.GONE);//隐藏进度条
                        refreshLayout.setRefreshing(false);//停止下拉刷新
                        datas=newBean.getData();
                        adapter=new NewsListAdapter(datas,getContext());
                        listView.setAdapter(adapter);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ivError.setVisibility(View.VISIBLE);//加载失败显示的错误图片
                        refreshLayout.setEnabled(false);//禁止下拉刷新
                        progressBar.setVisibility(View.GONE);//隐藏进度条
                        refreshLayout.setRefreshing(false);//停止下拉刷新
                        Toast.makeText(getContext(),"请求失败",Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();
                    }
                });
    }

    //返回新闻类型，改成抽象方法
    protected abstract String getType();

}

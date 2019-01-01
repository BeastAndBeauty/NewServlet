package com.paopao.newservlet.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.paopao.newservlet.R;

public class WebViewActivity extends AppCompatActivity {
    //传输的网址
    //public static final String URL="url";
    private WebView mWedView;
    private NumberProgressBar progressBar;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        init();
        initView();
        initData();
    }



    //初始化传递的信息
    private void init() {
        Intent intent = getIntent();
        url = intent.getStringExtra("URL");
    }

    //初始化界面
    private void initView(){
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);//设置返回按钮
        toolbar.setTitle("");
        //设置返回按钮的点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWedView= (WebView) findViewById(R.id.web_view);
        progressBar= (NumberProgressBar) findViewById(R.id.progressbar);
    }

    protected void initData(){
        mWedView.getSettings().setBuiltInZoomControls(false);//放大缩小按钮
        //mWedView.getSettings().setJavaScriptEnabled(true);//JS允许
        mWedView.setWebChromeClient(new WebChromeClient());//Chrome内核
        mWedView.setInitialScale(100);//设置缩放比例
        WebSettings settings=mWedView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 是否支持viewport属性，默认值 false
        // 页面通过`<meta name="viewport" ... />`自适应手机屏幕
        settings.setUseWideViewPort(true);
        // 是否使用overview mode加载页面，默认值 false
        // 当页面宽度大于WebView宽度时，缩小使页面宽度等于WebView宽度
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadWithOverviewMode(false);

        settings.setAppCacheEnabled(true);//设置缓存
        mWedView.setWebChromeClient(new ChromeClient());//ChromeClient 继承WedChromeClient
        mWedView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loadUrl(url);
                return true;
            }
        });

        if (!TextUtils.isEmpty(url))
            loadUrl(url);

        //碰到需要下载的连接，调到浏览器器进行下载
        mWedView.setDownloadListener(new MyWedViewDownLoadListener());


    }

    private class MyWedViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Uri uri=Uri.parse(url);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
    }

    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KetEvent exent)方法
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if ((keyCode==KeyEvent.KEYCODE_BACK)&&mWedView.canGoBack()){
            mWedView.goBack();//goBack()表示返回WedView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    private class ChromeClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            //设置进度
            progressBar.setProgress(newProgress);
            if (newProgress==100){
                progressBar.setVisibility(View.GONE);
            }else {
                progressBar.setVisibility(View.VISIBLE);
            }

        }
    }

    /**
     * 加载网页
     * @param url 要显示的网页
     */
    public void loadUrl(String url){
        if (mWedView!=null)
            mWedView.loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWedView!=null)
            mWedView.destroy();
    }

    @Override
    protected void onPause() {
        if (mWedView!=null)
            mWedView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWedView!=null)
            mWedView.onResume();
    }

//    public static Intent newIntent(Context context, String url){
//        Intent intent=new Intent(context,WebViewActivity.class);
//        intent.putExtra(URL,url);
//        return intent;
//    }

}

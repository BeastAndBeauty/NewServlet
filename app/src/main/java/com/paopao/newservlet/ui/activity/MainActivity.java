package com.paopao.newservlet.ui.activity;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.paopao.newservlet.R;
import com.paopao.newservlet.ui.adapter.MainPagerAdapter;
import com.paopao.newservlet.ui.fragment.MainFragment;
import com.paopao.newservlet.ui.fragment.UserFragment;
import com.paopao.newservlet.ui.fragment.VideoFragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout fl_fragment;
    private MainFragment mainFragment;
    private VideoFragment videoFragment;
    private UserFragment userFragment;
    private Fragment fragment_now = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

//        toolbar= (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        viewPager= (ViewPager) findViewById(R.id.view_pager);
//        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
//
//        pagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(pagerAdapter);
//        viewPager.setOffscreenPageLimit(2);
//
//        //TabLayout绑定ViewPager
//        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView(){
        fl_fragment=findViewById(R.id.fl_fragment);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);//改变 BottomNavigationView 默认的效果
        bottomNavigationView.setSelectedItemId(R.id.item_1);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        changePageFragment(menuItem.getItemId());
        return true;
    }

    /**
     * 当点击导航栏时改变fragment
     *
     * @param id
     */
    private void changePageFragment(int id){
        switch (id) {
            case R.id.item_1:
                if (mainFragment == null) { //减少new fragmnet,避免不必要的内存消耗
                    mainFragment = MainFragment.newInstance();
                }
                switchFragment(fragment_now, mainFragment);
                break;
            case R.id.item_2:
                if (videoFragment == null) {
                    videoFragment = VideoFragment.newInstance();
                }
                switchFragment(fragment_now, videoFragment);
                break;
            case R.id.item_3:
                if (userFragment == null) {
                    userFragment = UserFragment.newInstance();
                }
                switchFragment(fragment_now, userFragment);
                break;
        }
    }

    /**
     * 隐藏显示fragment
     *
     * @param from 需要隐藏的fragment
     * @param to   需要显示的fragment
     */
    private void switchFragment(Fragment from, Fragment to) {
        if (to == null)
            return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            if (from == null) {
                transaction.add(R.id.fl_fragment, to).show(to).commit();
            } else {
                // 隐藏当前的fragment，add下一个fragment到Activity中
                transaction.hide(from).add(R.id.fl_fragment, to).commitAllowingStateLoss();
            }
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(from).show(to).commit();
        }
        fragment_now = to;
    }


//    private TextView text;
//    Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Bundle b=msg.getData();
//            String data=b.getString("api");
//            try {
//                JSONObject jsonObject=new JSONObject(data);
//                String reason=jsonObject.getString("reason");
//                Log.d("TAG","reason"+reason);
//                JSONArray result=jsonObject.getJSONArray("data");
//                String str="";
//                for(int i=0;i<result.length();i++){
//                    JSONObject value=result.getJSONObject(i);
//                    String url=value.getString("title");
//                    str+=url+"******";
//                }
//
//
//                text.setText(str);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//    };

//    text=findViewById(R.id.text);
//    //String url = "http://localhost:8080/New/TouTiao";
//    String url="http://192.168.0.5:8080/News/NewApi?type=junshi";
//    OkHttpClient okHttpClient = new OkHttpClient();
//    final Request request = new Request.Builder()
//            .url(url)
//            .get()//默认就是GET请求，可以不写
//            .build();
//    Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//            Log.d("TAG", "onFailure: "+e.toString());
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//            //Log.d("TAG", "onResponse: " + response.body().string());
//            Bundle bundle=new Bundle();
//            bundle.putString("api",response.body().string());
//            Message message=new Message();
//            message.what=1;
//            message.setData(bundle);
//            handler.sendMessage(message);
//        }
//    });
//    //sendRequestWithOkHttp();



}

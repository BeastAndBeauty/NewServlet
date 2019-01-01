package com.paopao.newservlet.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.paopao.newservlet.R;
import com.paopao.newservlet.ui.activity.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 *
 * 我的Fragment
 */
public class UserFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ListView list_view;
    private RelativeLayout rl_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_user, container, false);
        initView();
        setAdapterData();
        return view;
    }

    private void initView(){
        list_view=view.findViewById(R.id.list_view);
        rl_layout=view.findViewById(R.id.rl_layout);
        rl_layout.setOnClickListener(this);
    }

    private void setAdapterData(){
        int [] item_icons=new int[]{R.drawable.user_fragment_history,R.drawable.user_fragment_collect,R.drawable.user_fragment_love,
        R.drawable.user_fragment_wallet,R.drawable.user_fragment_bank_card,R.drawable.user_fragment_setting};
        String[] item_texts=new String[]{"历史","收藏","喜欢","钱包","银行卡","设置"};

        List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < item_icons.length; i++) {
            Map<String, Object> showItem = new HashMap<String, Object>();
            showItem.put("item_icon", item_icons[i]);
            showItem.put("item_text", item_texts[i]);
            listItem.add(showItem);
        }

        //创建一个simpleAdapter
        SimpleAdapter simpleAdapter= new SimpleAdapter(getActivity(), listItem, R.layout.user_fragment_list_view, new String[]{"item_icon", "item_text"}, new int[]{R.id.item_icon, R.id.item_text});
        list_view.setAdapter(simpleAdapter);
    }

    public static UserFragment newInstance(){
        UserFragment userFragment=new UserFragment();
        return userFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //RelativeLayout
            case R.id.rl_layout:
                startActivity(new Intent(getActivity(),LoginActivity.class));
                break;
        }
    }
}

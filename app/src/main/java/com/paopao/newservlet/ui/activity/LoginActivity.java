package com.paopao.newservlet.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.paopao.newservlet.R;
import com.paopao.newservlet.callback.OnResponseListener;
import com.paopao.newservlet.request.BusStationRequest;
import com.paopao.newservlet.request.LoginRequest;
import com.paopao.newservlet.request.RegisterRequest;
import com.paopao.newservlet.utils.MD5Util;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;
    private EditText edt_use_account;
    private EditText edt_use_pw;
    private Button btn_login;
    private TextView tv_register_account;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
//        text=findViewById(R.id.text);
//        BusStationRequest.getInstance().setParams(new String[]{"1"}).sendRequest(new OnResponseListener() {
//            @Override
//            public void onResponse(Object result) {
//                if(result!=null){
//                    ArrayList<Integer> distance= (ArrayList<Integer>) result;
//                    Log.d("result","distance.get(0)"+distance.get(0)+"");
//                    text.setText(distance.get(0).toString());
//                }
//            }
//        });
    }

    private void initView(){
        edt_use_account=findViewById(R.id.edt_use_account);
        edt_use_pw=findViewById(R.id.edt_use_pw);
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        tv_register_account=findViewById(R.id.tv_register_account);
        tv_register_account.setOnClickListener(this);
        iv_back=findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回箭头
            case R.id.iv_back:
                finish();
                break;
                //点击登录
            case R.id.btn_login:
                checkLogin();
                break;
                //点击注册
            case R.id.tv_register_account:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }

    //检查是否可以登录
    private void checkLogin(){
        String str_account=edt_use_account.getText().toString().trim();
        String str_pw=edt_use_pw.getText().toString().trim();
        if(TextUtils.isEmpty(str_account)||TextUtils.isEmpty(str_pw)){
            Toast.makeText(getApplicationContext(),"账号或密码不能为空",Toast.LENGTH_SHORT).show();
        }else {
            try {
                LoginRequest.getInstance().setParams(new String[]{str_account,MD5Util.encode(str_pw)}).sendRequest(new OnResponseListener() {
                    @Override
                    public void onResponse(Object result) {
                        if(result.equals("success")){
                            Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                            //finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"账号或密码有误",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

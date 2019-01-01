package com.paopao.newservlet.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.paopao.newservlet.R;
import com.paopao.newservlet.callback.OnResponseListener;
import com.paopao.newservlet.request.RegisterRequest;
import com.paopao.newservlet.utils.MD5Util;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_account;
    private EditText edt_pw;
    private EditText edt_re_pw;
    private Button btn_register;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView(){
        edt_account=findViewById(R.id.edt_account);
        edt_pw=findViewById(R.id.edt_pw);
        edt_re_pw=findViewById(R.id.edt_re_pw);
        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        iv_back=findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //回退
            case R.id.iv_back:
                finish();
                break;
                //注册
            case R.id.btn_register:
                checkRegister();
                break;
        }
    }

    //检查是否可以注册
    private void checkRegister(){
        String str_account=edt_account.getText().toString().trim();
        String str_pw=edt_pw.getText().toString().trim();
        String str_re_pw=edt_re_pw.getText().toString().trim();
        if(TextUtils.isEmpty(str_account)||TextUtils.isEmpty(str_pw)||TextUtils.isEmpty(str_re_pw)){
            Toast.makeText(getApplicationContext(),"账号或密码不能为空",Toast.LENGTH_SHORT).show();
        }else if(!str_pw.equals(str_re_pw)){
            Toast.makeText(getApplicationContext(),"密码不一致",Toast.LENGTH_SHORT).show();
        }else{
            try {
                RegisterRequest.getInstance().setParams(new String[]{str_account,MD5Util.encode(str_pw)}).sendRequest(new OnResponseListener() {
                    @Override
                    public void onResponse(Object result) {
                        if(result.equals("exit")){
                            Toast.makeText(getApplicationContext(),"用户已存在",Toast.LENGTH_SHORT).show();
                        }else if (result.equals("failed")){//服务端问题
                            Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

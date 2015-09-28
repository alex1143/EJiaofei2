package com.dxkj.ejiaofei.ejiaofei.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dxkj.ejiaofei.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity {

    @ViewInject(R.id.btn_login)
    private Button btn_login;
    @ViewInject(R.id.btn_regist)
    private Button btn_regist;
    @ViewInject(R.id.edt_code)
    private TextInputLayout textInputCode;
    @ViewInject(R.id.edt_phone)
    private TextInputLayout textInputPhone;
    private EditText edt_code;
    private EditText edt_phone;

    //存入登录信息
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        edt_code = textInputCode.getEditText();
        edt_phone = textInputPhone.getEditText();
        textInputCode.setHint("请输入密码");
        textInputPhone.setHint("请输入账号");
        edt_code.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }


    public void onClick() {

        //登录
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //注册
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

   /* *//**
     * 保存用户登录信息
     *//*
    public void saveUserInfo(String userPhone, String userPassWord) {
        preferences = getSharedPreferences("loginNew", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //设置参数
        editor.putString("userphone", userPhone);
        editor.putString("password", userPassWord);
        //提交
        editor.commit();
    }*/

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}

       /*   edt_phone.setError("HAHA");*/





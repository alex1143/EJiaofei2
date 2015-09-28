package com.dxkj.ejiaofei.ejiaofei.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dxkj.ejiaofei.R;
import com.lidroid.xutils.view.annotation.ViewInject;

public class RegistActivity extends AppCompatActivity {

    @ViewInject(R.id.btn_regist)
    private Button btn_regist;
    @ViewInject(R.id.btn_cancel)
    private Button btn_cancel;
    @ViewInject(R.id.edt_code)
    private TextInputLayout textInputCode;
    @ViewInject(R.id.edt_sure_code)
    private TextInputLayout textInputSureCode;
    @ViewInject(R.id.edt_phone)
    private TextInputLayout textInputPhone;
    @ViewInject(R.id.edt_SMS)
    private TextInputLayout textInputSMS;
    @ViewInject(R.id.getSMS)
    Button btn_getSMS;
    private EditText edt_code;
    private EditText edt_sure_code;
    private EditText edt_phone;
    private EditText edt_SMS;

    public String phone;                            //用户手机号码
    public String InSMS;                            //输入的验证码
    public String InCode;                           //输入的密码
    public String InSureCode;                       //再次输入的密码

    //存入登录信息
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        init();
        setOnClick();
        ValidationNews(phone, InSMS, null, InCode, InSureCode);         //验证信息
        saveUserInfo(phone, InCode);
    }

    public void init() {
        edt_code = textInputCode.getEditText();
        edt_phone = textInputPhone.getEditText();
        edt_SMS = textInputPhone.getEditText();
        edt_sure_code = textInputPhone.getEditText();
        textInputCode.setHint("请输入密码");
        textInputPhone.setHint("请输入账号");
        textInputSMS.setHint("请输入用户名");
        textInputSureCode.setHint("确认密码");
        edt_phone.setInputType(InputType.TYPE_CLASS_NUMBER);
        edt_code.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        edt_sure_code.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        getInPutNews();
    }

    public void setOnClick() {

        //注册
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //取消
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //获取短信验证码
        btn_getSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 验证短信验证码及密码确认信息
     *
     * @param SMS      输入的短信验证码
     * @param getSMS   获取到的短信验证码
     * @param code     输入的密码
     * @param sureCode 确认密码
     */
    public void ValidationNews(String phone, String SMS, String getSMS, String code, String sureCode) {

        //判断手机号码是否已注册
        if (phone.equals(preferences.getString("userphone", ""))) {
            Toast.makeText(RegistActivity.this, "手机号码已注册", Toast.LENGTH_SHORT).show();
        }
        //密码确认
        if (code.equals(sureCode) && SMS.equals(getSMS)) {
            Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        } else if (!code.equals(sureCode) && SMS.equals(getSMS)) {
            Toast.makeText(RegistActivity.this, "两次密码不匹配，请检查重新输入", Toast.LENGTH_SHORT).show();
        } else if (code.equals(sureCode) && !SMS.equals(getSMS)) {
            Toast.makeText(RegistActivity.this, "短信验证码错误", Toast.LENGTH_SHORT).show();
        }
    }

    //获取用户的输入的信息
    public void getInPutNews() {
        phone = edt_phone.getText().toString();
        InSMS = edt_SMS.getText().toString();
        InCode = edt_code.getText().toString();
        InSureCode = edt_sure_code.getText().toString();
    }

    /**
     * 保存用户登录信息
     */
    public void saveUserInfo(String userPhone, String userPassWord) {
        preferences = getSharedPreferences("loginNew", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //设置参数
        editor.putString("userphone", userPhone);
        editor.putString("password", userPassWord);
        //提交
        editor.commit();
    }
}

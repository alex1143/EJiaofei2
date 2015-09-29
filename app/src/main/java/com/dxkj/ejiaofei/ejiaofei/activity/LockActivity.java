package com.dxkj.ejiaofei.ejiaofei.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dxkj.ejiaofei.R;
import com.dxkj.ejiaofei.ejiaofei.custom.GestureContentView;
import com.dxkj.ejiaofei.ejiaofei.custom.GestureDrawline;
import com.dxkj.ejiaofei.ejiaofei.lock.LockIndicator;

public class LockActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 手机号码
     */
    public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
    /**
     * 意图
     */
    public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
    /**
     * 首次提示绘制手势密码，可以选择跳过
     */
    public static final String PARAM_IS_FIRST_ADVICE = "PARAM_IS_FIRST_ADVICE";
    private TextView mTextTitle;
    private TextView mTextCancel;
    private LockIndicator mLockIndicator;   //
    private TextView mTextTip;
    private FrameLayout mGestureContainer;
    private GestureContentView mGestureContentView;
    private TextView mTextReset;
    private String mParamSetUpcode = null;
    private String mParamPhoneNumber;
    private boolean mIsFirstInput = true;
    private String mFirstPassword = null;
    private String mConfirmPassword = null;
    private int mParamIntentCode;

    //存入密码信息
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        initView();
        setUpListeners();
    }

    public void initView() {
        mTextTip = (TextView) findViewById(R.id.text_tip);
        mTextCancel = (TextView) findViewById(R.id.text_cancel);
        mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
        mTextReset = (TextView) findViewById(R.id.text_reset);
        mLockIndicator = (LockIndicator) findViewById(R.id.lock_indicator);
        mTextReset.setClickable(false);
        //初始化一个显示各个点的视图
        mGestureContentView = new GestureContentView(this, false, "", new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {
                if (!isInputPassValidate(inputCode)) {
                    mTextTip.setText(Html.fromHtml("<font color='#c70c1e'>最少链接4个点, 请重新输入</font>"));
                    mGestureContentView.clearDrawlineState(0L);
                    return;
                }
                if (mIsFirstInput) {
                    mFirstPassword = inputCode;
                    updateCodeList(inputCode);
                    mGestureContentView.clearDrawlineState(0L);
                    mTextReset.setClickable(true);
                    mTextReset.setText(getString(R.string.reset_gesture_code));
                } else {
                    if (inputCode.equals(mFirstPassword)) {
                        preferences = getSharedPreferences("ShouShiPassWord", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        //设置参数
                        editor.putString("sPassWord", mFirstPassword);
                        //提交
                        editor.commit();
                        Log.i("123","mFirstPassword-->"+mFirstPassword);
                        Toast.makeText(LockActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        mGestureContentView.clearDrawlineState(0L);
                        LockActivity.this.finish();
                    } else {
                        mTextTip.setText(Html.fromHtml("<font color='#c70c1e'>与上一次绘制不一致，请重新绘制</font>"));
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(LockActivity.this, R.anim.shake);
                        mTextTip.startAnimation(shakeAnimation);
                        // 保持绘制的线，1.5秒后清除
                        mGestureContentView.clearDrawlineState(1300L);
                    }
                }
                mIsFirstInput = false;
            }

            @Override
            public void checkedSuccess() {

            }

            @Override
            public void checkedFail() {

            }
        });

        //设置手势解锁显示在那个布局中
        mGestureContentView.setParentView(mGestureContainer);
        updateCodeList("");
    }

    private void setUpListeners() {
        mTextCancel.setOnClickListener(this);
        mTextReset.setOnClickListener(this);
    }

    private void updateCodeList(String inputCode) {
        // 更新选择的图案
        mLockIndicator.setPath(inputCode);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_cancel:
                this.finish();
                break;
            case R.id.text_reset:
                mIsFirstInput = true;
                updateCodeList("");
                mTextTip.setText(getString(R.string.set_gesture_pattern));
                break;
            default:
                break;
        }
    }

    private boolean isInputPassValidate(String inputPassword) {
        if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
            return false;
        }
        return true;
    }
}

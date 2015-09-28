package com.dxkj.ejiaofei.ejiaofei.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dxkj.ejiaofei.R;


public class ChargeActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);
        mViewPager = (ViewPager) findViewById(R.id.chargeViewPager);
    }

}

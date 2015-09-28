package com.dxkj.ejiaofei.ejiaofei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dxkj.ejiaofei.R;
import com.dxkj.ejiaofei.ejiaofei.adapter.WalletListAdapter;

/**
 * 钱包页面
 */
public class WalletActivity extends AppCompatActivity {

    private TextView tv_balance;
    private TextView tv_userName;
    private ListView lv;
    private WalletListAdapter listAdapter;
    public String[] listItems = {"个人信息", "银行转账", "淘宝加款", "拍拍加款"};
    public int[] icon = {R.drawable.geren, R.drawable.yinghang2, R.drawable.taobao, R.drawable.bags};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        init();
        setListclick();
    }

    private void init() {
        tv_balance = (TextView) findViewById(R.id.tv_balance);
        tv_userName = (TextView) findViewById(R.id.userName);
        lv = (ListView) findViewById(R.id.wallet_listview);
        listAdapter = new WalletListAdapter(WalletActivity.this, listItems, icon);
        tv_userName.setText("萧炎");
        tv_balance.setText("100.0000.00");
        lv.setAdapter(listAdapter);
    }

    private void setListclick() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent1 = new Intent();
                        intent1.setClass(WalletActivity.this, UserNewsActivity.class);
                        startActivity(intent1);
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                }
            }
        });
    }
}

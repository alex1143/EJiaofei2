package com.dxkj.ejiaofei.ejiaofei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dxkj.ejiaofei.R;
import com.dxkj.ejiaofei.ejiaofei.adapter.FragmentAdapter;
import com.dxkj.ejiaofei.ejiaofei.fragment.HomeFragment;
import com.dxkj.ejiaofei.ejiaofei.fragment.MeFragment;
import com.dxkj.ejiaofei.ejiaofei.fragment.OrderFragment;
import com.dxkj.ejiaofei.ejiaofei.fragment.ServierFragment;
import com.tencent.connect.auth.QQAuth;
import com.tencent.open.wpa.WPA;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 程序入口首页
 * Created by dxkj on 2015/9/20.
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mFtViewPager;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;                    //标签栏
    private NavigationView mNavigationView;          //左侧菜单控件
    private DrawerLayout mDrawerLayout;              //DrawerLayout控件
    public List<String> titles;                      //标题栏文字

    private static boolean mBackKeyPressed = false;  //记录是否有首次按键

    public List<Fragment> mListFragment;
    public HomeFragment mHomeFragment;
    public OrderFragment mOrderFragment;
    public ServierFragment mServierFragment;
    public MeFragment mMeFragment;
    public FragmentAdapter mFtdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mFtViewPager = (ViewPager) findViewById(R.id.fragment_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        titles = new ArrayList<>();
        setToolbar();                                                               //设置toolbar和mTabLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setmNavDraw();

    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //初始化标题栏
        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("订单");
        titles.add("客服");
        titles.add("我");

        //初始化tablayout的title
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(3)));
    }

    private void setmNavDraw() {
        //对NavigationView添加item的监听事件
        mNavigationView.setNavigationItemSelectedListener(naviListener);
        //开启应用默认打开DrawerLayout
//        mDrawerLayout.openDrawer(mNavigationView);
    }

    private NavigationView.OnNavigationItemSelectedListener naviListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            //点击NavigationView中定义的menu item时触发反应
            switch (menuItem.getItemId()) {
                case R.id.menu_publicnews:                             //暂时为qq登录
                    QQAuth mqqAuth = QQAuth.createInstance("1104809073",MainActivity.this); // 10000000为你申请的APP_ID,mContext是上下文
                    WPA mWPA = new WPA(MainActivity.this, mqqAuth.getQQToken());
                    String ESQ = "2850314360";  //512821255为客服QQ号
                    String text = "aaaaa";
                    int ret = mWPA.startWPAConversation(MainActivity.this,ESQ, "你好，我正在乐宠看这个商品~\n"+text);
                    if (ret != 0) { //如果ret不为0，就说明调用SDK出现了错误
                        Toast.makeText(MainActivity.this,
                                "抱歉，联系客服出现了错误~. error:" + ret,
                                Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.menu_goods:                                 //暂时为设置手势密码
                    Intent intent1 = new Intent();
                    intent1.setClass(MainActivity.this, LockActivity.class);
                    startActivity(intent1);
//                    mFtViewPager.setCurrentItem(1);
                    break;
                case R.id.menu_order:
                    Intent intent2 = new Intent();
                    intent2.setClass(MainActivity.this,CheakLockActivity.class);
                    startActivity(intent2);
//                    mFtViewPager.setCurrentItem(2);
                    break;
                case R.id.menu_me:
                    mFtViewPager.setCurrentItem(3);
                    break;
            }
            //关闭DrawerLayout回到主界面选中的tab的fragment页
            mDrawerLayout.closeDrawer(mNavigationView);
            return false;
        }
    };

    private void initFragment() {
        mListFragment = new ArrayList<>();
        mHomeFragment = HomeFragment.newInstance();
        mOrderFragment = OrderFragment.newInstance();
        mServierFragment = ServierFragment.newInstance();
        mMeFragment = MeFragment.newInstance();
        mListFragment.add(mHomeFragment);
        mListFragment.add(mOrderFragment);
        mListFragment.add(mServierFragment);
        mListFragment.add(mMeFragment);
        mFtdapter = new FragmentAdapter(getSupportFragmentManager(), mListFragment, titles);
        mFtViewPager.setAdapter(mFtdapter);
        mTabLayout.setupWithViewPager(mFtViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFtdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦除第一次按键记录
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {//退出程序
            this.finish();
            System.exit(0);
        }
    }
}

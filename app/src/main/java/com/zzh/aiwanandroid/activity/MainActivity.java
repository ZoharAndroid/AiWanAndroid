package com.zzh.aiwanandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.fragment.home.HomeFragment;
import com.zzh.aiwanandroid.fragment.project.ProjectFragment;
import com.zzh.aiwanandroid.fragment.square.SquareFragment;
import com.zzh.aiwanandroid.fragment.structure.StructureFragment;
import com.zzh.aiwanandroid.fragment.wechat.WechatFragment;
import com.zzh.aiwanandroid.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private TextView mTitleTextView;
    private Toolbar toolbar;
    private FloatingActionButton mFloatingActionButton;
    private NavigationView mNavigationView;
    private BottomNavigationView mBottomView;
    private TabLayout mTabLayout;

    private int mLastFragmentIndex;
    private Fragment mHomeFragment;
    private List<Fragment> mFragments;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomView = findViewById(R.id.bottom_navigation);
        FrameLayout mContentGroupFrameLayout = findViewById(R.id.content_group);
        toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout_main);
        mTitleTextView = findViewById(R.id.toolbar_textview);
        mNavigationView = findViewById(R.id.navigation_view);
        mFloatingActionButton = findViewById(R.id.floating_action_button);
        mTabLayout = findViewById(R.id.tool_bar_tab_layout);

        // 将toolbar设置Actionbar
        setSupportActionBar(toolbar);

        // 给标题栏添加新的图标
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // 隐藏系统自带的图标
            actionBar.setDisplayHomeAsUpEnabled(true);
            // 隐藏系统应用名
            actionBar.setDisplayShowTitleEnabled(false);
            // 设置新的图标
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_menu_nav);
        }

        // 设置标题栏
        setToolbarTitle(getResources().getString(R.string.bottom_home));


        initEventAndData();
        if (savedInstanceState == null) {
            initPager(false, Constants.TYPE_MAIN);
        }

    }

    private void initEventAndData() {
        mFragments = new ArrayList<>();

        // 给FloatingActionButton设置点击事件
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  回到顶部
                RecyclerView homeRecyclerView = mHomeFragment.getView().findViewById(R.id.home_pager_recycler_view);
                homeRecyclerView.smoothScrollToPosition(0);
            }
        });

        // 给NavigationView的menu创建菜单点击事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_menu_my_score:
                        CommonUtils.ToastShow("我的积分");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_my_collection:
                        CommonUtils.ToastShow("我的收藏");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_my_share:
                        CommonUtils.ToastShow("我的分享");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_todo:
                        CommonUtils.ToastShow("todo");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_night:
                        CommonUtils.ToastShow("夜间模式");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_system_setting:
                        CommonUtils.ToastShow("系统设置");
                        mDrawerLayout.closeDrawers();
                        return true;
                    default:
                        mDrawerLayout.closeDrawers();
                }

                return false;
            }
        });

        // 给底部导航栏添加点击事件
        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        // 选中的是home
                        showTabLayout(false);
                        switchFragment(Constants.TYPE_MAIN, getResources().getString(R.string.bottom_home));
                        setToolbarTitle(getResources().getString(R.string.bottom_home));
                        return true;
                    case R.id.bottom_question:
                        showTabLayout(false);
                        switchFragment(Constants.TYPE_QUESTION, getResources().getString(R.string.bottom_question));
                        setToolbarTitle(getResources().getString(R.string.bottom_question));
                        return true;
                    case R.id.bottom_wechat:
                        showTabLayout(false);
                        switchFragment(Constants.TYPE_WEHCHAT, getResources().getString(R.string.bottom_wechat));
                        setToolbarTitle(getResources().getString(R.string.bottom_wechat));
                        return true;
                    case R.id.bottom_system:
                        showTabLayout(true);
                        switchFragment(Constants.TYPE_SYSTEM, getResources().getString(R.string.bottom_system));
                        //setToolbarTitle(getResources().getString(R.string.bottom_system));
                        return true;
                    case R.id.bottom_project:
                        showTabLayout(false);
                        switchFragment(Constants.TYPE_PROJECT, getResources().getString(R.string.bottom_project));
                        setToolbarTitle(getResources().getString(R.string.bottom_project));
                        return true;
                }
                return false;
            }
        });



    }


    /**
     * 获取TabLayout
     *
     * @return
     */
    public TabLayout getTabLayout() {
        return mTabLayout;
    }


    /**
     * 初始化界面
     */
    private void initPager(boolean isCreated, int position) {
        mHomeFragment = HomeFragment.getInstance(null, null);
        mFragments.add(mHomeFragment);
        initFragments();
        switchFragment(Constants.TYPE_MAIN, getResources().getString(R.string.bottom_home));
    }

    private void initFragments() {
        Fragment mQuestionFragment = SquareFragment.getInstance(null, null);
        Fragment mWechatFragment = WechatFragment.getInstance(null, null);
        Fragment mSystemFragment = StructureFragment.getInstance(null, null);
        Fragment mProjectFragment = ProjectFragment.getInstance(null, null);

        mFragments.add(mQuestionFragment);
        mFragments.add(mWechatFragment);
        mFragments.add(mSystemFragment);
        mFragments.add(mProjectFragment);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 创建toolbar 右侧菜单
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        // 只显示搜索按钮，其余的隐藏
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        MenuItem collectItem = menu.findItem(R.id.menu_collection);
        MenuItem shareItem = menu.findItem(R.id.menu_share);
        MenuItem explorerItem = menu.findItem(R.id.menu_explorer);
        searchItem.setVisible(true);
        collectItem.setVisible(false);
        shareItem.setVisible(false);
        explorerItem.setVisible(false);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // 点击toolbar的home按钮
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.menu_search:
                // 搜索
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_share:
                // 分享

                break;
            case R.id.menu_collection:
                // 收藏
                break;
            case R.id.menu_explorer:
                // 浏览器打开
                break;
        }
        return true;
    }

    /**
     * 设置标题栏
     *
     * @param title
     */
    private void setToolbarTitle(String title) {
        mTitleTextView.setText(title);
        toolbar.setSelected(true);
    }

    /**
     * 显示 tab layout
     *
     * @param isShow
     */
    private void showTabLayout(boolean isShow) {
        if (isShow) {
            mTabLayout.setVisibility(View.VISIBLE);
            mTitleTextView.setVisibility(View.GONE);
        } else {
            mTabLayout.setVisibility(View.GONE);
            mTitleTextView.setVisibility(View.VISIBLE);

        }
    }

    /**
     * 切换Fragment
     *
     * @param position
     */
    private void switchFragment(int position, String tag) {
        if (position > mFragments.size()) {
            return;
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment targetFragment = mFragments.get(position);
        Fragment lastFragment = mFragments.get(mLastFragmentIndex);
        mLastFragmentIndex = position;
        fragmentTransaction.hide(lastFragment);
        if (!targetFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFragment).commit();
            fragmentTransaction.add(R.id.content_group, targetFragment, tag);
        }
        fragmentTransaction.show(targetFragment);
        fragmentTransaction.commit();
    }

}
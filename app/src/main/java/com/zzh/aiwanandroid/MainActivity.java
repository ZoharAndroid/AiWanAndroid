package com.zzh.aiwanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.zzh.aiwanandroid.fragment.HomeFragment;
import com.zzh.aiwanandroid.fragment.ProjectFragment;
import com.zzh.aiwanandroid.fragment.QuestionFragment;
import com.zzh.aiwanandroid.fragment.SystemFragment;
import com.zzh.aiwanandroid.fragment.WechatFragment;
import com.zzh.aiwanandroid.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView mBottomView = findViewById(R.id.bottom_navigation);
        FrameLayout mContentGroupFrameLayout = findViewById(R.id.content_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout_main);
        NavigationView mNavigationView = findViewById(R.id.navigation_view);
        FloatingActionButton mFloatingActionButton = findViewById(R.id.floating_action_button);

        // 将toolbar设置Actionbar
        setSupportActionBar(toolbar);

        // 给标题栏添加新的图标
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // 隐藏系统自带的图标
            actionBar.setDisplayHomeAsUpEnabled(true);
            // 设置新的图标
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_menu_nav);
        }

        // 默认开启Home页面
        Fragment mHomeFragment = HomeFragment.getInstance(null, null);
        getSupportFragmentManager().beginTransaction().add(R.id.content_group, mHomeFragment).commit();

        // 给FloatingActionButton设置点击事件
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.ToastShow("FloatingActionButton");
            }
        });

        // 给NavigationView的menu创建菜单点击事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_menu_my_score:
                        Utils.ToastShow("我的积分");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_my_collection:
                        Utils.ToastShow("我的收藏");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_my_share:
                        Utils.ToastShow("我的分享");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_todo:
                        Utils.ToastShow("todo");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_night:
                        Utils.ToastShow("夜间模式");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_system_setting:
                        Utils.ToastShow("系统设置");
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
                        switchFragment(HomeFragment.getInstance(null, null));
                        return true;
                    case R.id.bottom_question:
                        switchFragment(QuestionFragment.getInstance(null, null));
                        return true;
                    case R.id.bottom_wechat:
                        switchFragment(WechatFragment.getInstance(null, null));
                        return true;
                    case R.id.bottom_system:
                        switchFragment(SystemFragment.getInstance(null, null));
                        return true;
                    case R.id.bottom_project:
                        switchFragment(ProjectFragment.getInstance(null, null));
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 创建toolbar 右侧菜单
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
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
     * 切换Fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_group, fragment).commit();
    }
}
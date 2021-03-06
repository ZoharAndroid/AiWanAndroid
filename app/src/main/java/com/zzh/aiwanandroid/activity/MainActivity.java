package com.zzh.aiwanandroid.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.zzh.aiwanandroid.bean.LoginBean;
import com.zzh.aiwanandroid.fragment.home.HomeFragment;
import com.zzh.aiwanandroid.fragment.project.ProjectFragment;
import com.zzh.aiwanandroid.fragment.square.SquareFragment;
import com.zzh.aiwanandroid.fragment.structure.StructureFragment;
import com.zzh.aiwanandroid.fragment.wechat.WechatFragment;
import com.zzh.aiwanandroid.utils.CommonUtils;
import com.zzh.aiwanandroid.utils.LogUtils;

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

    private TextView mLoginTextView;

    private int mLastFragmentIndex;
    private Fragment mHomeFragment;
    private List<Fragment> mFragments;
    private TextView mLoginUserNameView;
    private ActivityResultLauncher<Intent> launcher;
    private LoginBean.User user;


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

        View headerView = mNavigationView.inflateHeaderView(R.layout.header_navigation_layout);
        mLoginTextView = headerView.findViewById(R.id.nav_header_login_text_view);
        mLoginUserNameView = headerView.findViewById(R.id.nav_header_login_text_view);



        // ???toolbar??????Actionbar
        setSupportActionBar(toolbar);

        // ??????????????????????????????
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // ???????????????????????????
            actionBar.setDisplayHomeAsUpEnabled(true);
            // ?????????????????????
            actionBar.setDisplayShowTitleEnabled(false);
            // ??????????????????
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_menu_nav);
        }

        // ???????????????
        setToolbarTitle(getResources().getString(R.string.bottom_home));

        initEventAndData();
        if (savedInstanceState == null) {
            initPager(false, Constants.TYPE_MAIN);
        }
    }

    private void initEventAndData() {
        mFragments = new ArrayList<>();

        // ???FloatingActionButton??????????????????
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  ????????????
                RecyclerView homeRecyclerView = mHomeFragment.getView().findViewById(R.id.home_pager_recycler_view);
                homeRecyclerView.smoothScrollToPosition(0);
            }
        });

        // ???NavigationView???menu????????????????????????
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_menu_my_score:
                        CommonUtils.ToastShow("????????????");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_my_collection:
                        CommonUtils.ToastShow("????????????");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_my_share:
                        CommonUtils.ToastShow("????????????");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_todo:
                        CommonUtils.ToastShow("todo");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_night:
                        CommonUtils.ToastShow("????????????");
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_menu_system_setting:
                        CommonUtils.ToastShow("????????????");
                        mDrawerLayout.closeDrawers();
                        return true;
                    default:
                        mDrawerLayout.closeDrawers();
                }

                return false;
            }
        });

        // ????????????????????????????????????
        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        // ????????????home
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


        // ??????????????????
        mLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // ?????????????????????????????????SP?????????????????????
        user = CommonUtils.getObjectFromSP(LoginBean.User.class);
        if (user != null){
            mLoginTextView.setText(user.getUsername());
            // ???????????????????????????
            mNavigationView.getMenu().findItem(R.id.nav_menu_login_out).setVisible(true);
            // ????????????????????????
            mLoginTextView.setClickable(false);
        }
    }



    /**
     * ??????TabLayout
     *
     * @return
     */
    public TabLayout getTabLayout() {
        return mTabLayout;
    }


    /**
     * ???????????????
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
        // ??????toolbar ????????????
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        // ???????????????????????????????????????
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
            // ??????toolbar???home??????
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.menu_search:
                // ??????
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_share:
                // ??????

                break;
            case R.id.menu_collection:
                // ??????
                break;
            case R.id.menu_explorer:
                // ???????????????
                break;
            case R.id.nav_menu_login_out:
                if (user!=null){

                }
                // ????????????
                // todo:

                break;
        }
        return true;
    }

    /**
     * ???????????????
     *
     * @param title
     */
    private void setToolbarTitle(String title) {
        mTitleTextView.setText(title);
        toolbar.setSelected(true);
    }

    /**
     * ?????? tab layout
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
     * ??????Fragment
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
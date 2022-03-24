package com.zzh.aiwanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zzh.aiwanandroid.fragment.HomeFragment;
import com.zzh.aiwanandroid.fragment.ProjectFragment;
import com.zzh.aiwanandroid.fragment.QuestionFragment;
import com.zzh.aiwanandroid.fragment.SystemFragment;
import com.zzh.aiwanandroid.fragment.WechatFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomView;
    private FrameLayout mContentGroupFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomView = findViewById(R.id.bottom_navigation);
        mContentGroupFrameLayout = findViewById(R.id.content_group);

        Fragment mHomeFragment = HomeFragment.getInstance(null, null);
        getSupportFragmentManager().beginTransaction().add(R.id.content_group, mHomeFragment).commit();

        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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


    /**
     * 切换Fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_group, fragment).commit();
    }
}
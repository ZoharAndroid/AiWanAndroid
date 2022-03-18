package com.zzh.aiwanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomView;
    private FrameLayout mContentGroupFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomView = findViewById(R.id.bottom_navigation);
        mContentGroupFrameLayout = findViewById(R.id.content_group);

        mBottomView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:

                        break;
                    case R.id.bottom_question:
                        break;
                    case R.id.bottom_wechat:
                        break;
                    case R.id.bottom_system:
                        break;
                    case R.id.bottom_project:
                        break;
                    default:
                        break;

                }
            }
        });
    }
}
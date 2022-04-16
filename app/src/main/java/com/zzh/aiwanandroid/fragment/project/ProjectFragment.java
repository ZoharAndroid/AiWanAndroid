package com.zzh.aiwanandroid.fragment.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.TreeBean;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 项目
 */
public class ProjectFragment extends BaseFragment {

    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;

    private List<ProjectDetailFragment> mFragments = new ArrayList<>();


    private ProjectFragment(){}


    public static Fragment getInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.param1, param1);
        bundle.putString(Constants.param2, param2);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        mViewPager = view.findViewById(R.id.view_pager_project);
        mTabLayout = view.findViewById(R.id.tab_layout_project);
    }

    @Override
    protected void initEventAndData() {
        loadProjectTreeData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    /**
     * 加载数据
     *
     */
    private void loadProjectTreeData(){
        HttpUtils.sendHttpRequest(HttpConfig.PROJECT_LIST_URL(), new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                TreeBean treeBean = HttpUtils.parseJson(response, TreeBean.class);
                List<TreeBean.Tree> treeList = treeBean.getData();
                List<String> projectTitleList = new ArrayList<>();
                for (TreeBean.Tree tree : treeList){
                    projectTitleList.add(tree.getName());
                    mFragments.add(ProjectDetailFragment.getInstance(tree.getId(),null));
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // viewpager
                        mViewPager.setAdapter(new FragmentStateAdapter(getChildFragmentManager(),getLifecycle()) {
                            @NonNull
                            @Override
                            public Fragment createFragment(int position) {
                                return mFragments.get(position);
                            }

                            @Override
                            public int getItemCount() {
                                return mFragments.size();
                            }
                        });

                        // tablayout数据加载
                        new TabLayoutMediator(mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                            @Override
                            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                                tab.setText(projectTitleList.get(position));
                            }
                        }).attach();


                    }
                });
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }


}

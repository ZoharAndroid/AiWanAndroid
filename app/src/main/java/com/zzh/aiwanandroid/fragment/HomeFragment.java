package com.zzh.aiwanandroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;

public class HomeFragment extends Fragment {


    /**
     * 获取HomeFragment实例
     *
     * @param param1
     * @param param2
     *
     * @return
     */
    public static Fragment getInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.param1, param1);
        bundle.putString(Constants.param2, param2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }
}

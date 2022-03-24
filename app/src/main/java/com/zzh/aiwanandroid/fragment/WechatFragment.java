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

public class WechatFragment extends Fragment {

    public static WechatFragment getInstance(String param1, String param2){
        Bundle args = new Bundle();
        WechatFragment fragment = new WechatFragment();
        args.putString(Constants.param1,param1);
        args.putString(Constants.param2,param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat,container,false);

        return view;
    }
}

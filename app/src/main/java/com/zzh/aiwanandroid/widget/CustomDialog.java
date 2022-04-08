package com.zzh.aiwanandroid.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zzh.aiwanandroid.R;

public class CustomDialog extends Dialog {

    private TextView mLoadingTextView;
    private ImageView mLoadingImageView;

    public CustomDialog(@NonNull Context context) {
        this(context, R.style.loading_dialog,"正在加载...");
    }

    public CustomDialog(Context context,String string){
        this(context,R.style.loading_dialog,string);
    }
    public CustomDialog(@NonNull Context context, int themeResId,String string) {
        super(context, themeResId);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.loading_dialog_layout);
        mLoadingTextView = findViewById(R.id.tv_loading_tx);
        mLoadingImageView = findViewById(R.id.iv_loading);

        mLoadingTextView.setText(string);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_anim);
        mLoadingImageView.startAnimation(animation);

        getWindow().getAttributes().gravity = Gravity.CENTER; // 居中显示
        getWindow().getAttributes().dimAmount = 0.5f; // 设置背景透明度
    }

}

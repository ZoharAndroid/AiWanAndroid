package com.zzh.aiwanandroid.fragment.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.LoginBean;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.utils.CommonUtils;
import com.zzh.aiwanandroid.utils.HttpUtils;
import com.zzh.aiwanandroid.utils.LogUtils;

import okhttp3.Call;

public class LoginFragment extends BaseFragment {

    private EditText mUsernameEdit;
    private EditText mPasswordEdit;
    private Button mLoginButton;
    private ImageView mShowPasswordView;
    private LinearLayout mGoRegisterView;

    private boolean isClickSeePassword = false; // 默认为不显示

    @Override
    protected void initView(View view) {
        super.initView(view);
        mUsernameEdit = view.findViewById(R.id.login_user_name);
        mPasswordEdit = view.findViewById(R.id.login_password);
        mLoginButton = view.findViewById(R.id.login_button);
        mShowPasswordView = view.findViewById(R.id.password_show_image_view);
        mGoRegisterView = view.findViewById(R.id.go_register_view);

        showPasswordView(false);
    }

    private void showPasswordView(boolean isShow) {
        if (isShow) {
            mShowPasswordView.setVisibility(View.VISIBLE);
        } else
            mShowPasswordView.setVisibility(View.INVISIBLE);
    }


    private LoginFragment() {
    }

    public static BaseFragment getInstance(String param1, String param2) {
        BaseFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initEventAndData() {
        /**
         * 密码输入
         */
        mPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    showPasswordView(false);
                } else {
                    showPasswordView(true);
                }
            }
        });



        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 获取用户名
                String username = mUsernameEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    CommonUtils.ToastShow("请输入用户名");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    CommonUtils.ToastShow("请输入密码");
                    return;
                }
                // 登录请求
                sendLoginRequest(username,password);
            }
        });


        mShowPasswordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClickSeePassword = !isClickSeePassword;

                // 显示密码
                if (isClickSeePassword) {
                    mPasswordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mPasswordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                // 设置光标移动到最后
                mPasswordEdit.setSelection(mPasswordEdit.getText().length());
            }
        });

        mGoRegisterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginOrRegisterFragment)getParentFragment()).goNextFragment();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    private void sendLoginRequest(String username,String password){
        HttpUtils.sendLoginPostRequest(username, password, new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                LoginBean loginBean = HttpUtils.parseJson(response, LoginBean.class);
                if (!TextUtils.isEmpty(loginBean.getErrorMsg())) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CommonUtils.ToastShow(loginBean.getErrorMsg());
                        }
                    });
                    return;
                }
                if (loginBean.getData() != null) {
                    LoginBean.User user = loginBean.getData();
                    // 将用户信息保存到sp中
                    CommonUtils.saveObjectToSP(user);

                    // 登陆成功就关闭该页面
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CommonUtils.ToastShow("登录成功！");
                            // 将数据传递给
                            getActivity().finish();
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }
}

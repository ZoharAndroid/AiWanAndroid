package com.zzh.aiwanandroid.fragment.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.LoginBean;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.utils.CommonUtils;
import com.zzh.aiwanandroid.utils.HttpUtils;

import okhttp3.Call;

public class RegisterFragment extends BaseFragment {

    private EditText mUsernameEdit;
    private EditText mEmailEdit;
    private EditText mPasswordEdit;
    private EditText mPasswordAgainEdit;
    private ImageView mSeeImageView;
    private ImageView mSeeAgainImageView;
    private Button mRegisterButton;
    private LinearLayout mGoLoginView;

    private boolean isClickPassword = false;
    private boolean isClickAgainPassword = false;

    private RegisterFragment() {
    }

    public static BaseFragment getInstance(String param1, String param2) {
        BaseFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mUsernameEdit = view.findViewById(R.id.register_user_name);
        mEmailEdit = view.findViewById(R.id.register_email);
        mPasswordEdit = view.findViewById(R.id.register_password);
        mPasswordAgainEdit = view.findViewById(R.id.register_password_again);
        mSeeImageView = view.findViewById(R.id.see_register_image);
        mSeeAgainImageView = view.findViewById(R.id.see_register_again_image);
        mRegisterButton = view.findViewById(R.id.register_button);
        mGoLoginView = view.findViewById(R.id.go_login_view);
    }

    @Override
    protected void initEventAndData() {

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

        mPasswordAgainEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    showPasswordAgainView(false);
                } else {
                    showPasswordAgainView(true);
                }
            }
        });

        mSeeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClickPassword = !isClickPassword;
                // ????????????
                if (isClickPassword) {
                    mPasswordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mPasswordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                // ???????????????????????????
                mPasswordEdit.setSelection(mPasswordEdit.getText().length());
            }
        });

        mSeeAgainImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClickAgainPassword = !isClickAgainPassword;
                // ????????????
                if (isClickAgainPassword) {
                    mPasswordAgainEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mPasswordAgainEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                // ???????????????????????????
                mPasswordAgainEdit.setSelection(mPasswordAgainEdit.getText().length());
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mUsernameEdit.getText().toString())) {
                    CommonUtils.ToastShow("?????????????????????");
                    return;
                }

//                if (TextUtils.isEmpty(mEmailEdit.getText().toString())){
//                    CommonUtils.ToastShow("??????????????????");
//                    return;
//                }

                if (TextUtils.isEmpty(mPasswordEdit.getText().toString())) {
                    CommonUtils.ToastShow("??????????????????");
                    return;
                }

                if (TextUtils.isEmpty(mPasswordAgainEdit.getText().toString()) || !mPasswordEdit.getText().toString().equals(mPasswordAgainEdit.getText().toString())) {
                    CommonUtils.ToastShow("?????????????????????");
                    return;
                }

                // ????????????
                String username = mUsernameEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                String repassword = mPasswordAgainEdit.getText().toString();
                sendRegisterRequest(username, password, repassword);
            }
        });

        mGoLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginOrRegisterFragment) getParentFragment()).goNextFragment();
            }
        });
    }

    private void sendRegisterRequest(String username, String password, String repassword) {
        HttpUtils.sendRegisterPostRequest(username, password, repassword, new CallbackListener() {
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

                    // ??????????????????
                    LoginBean.User user = loginBean.getData();
                    // ??????Cookies
                    // todo:


                    // ??????????????????????????????
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CommonUtils.ToastShow("???????????????");
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


    /**
     * ????????????
     *
     * @param isShow
     */
    private void showPasswordView(boolean isShow) {
        if (isShow) {
            mSeeImageView.setVisibility(View.VISIBLE);
        } else
            mSeeImageView.setVisibility(View.INVISIBLE);
    }


    private void showPasswordAgainView(boolean isShow) {
        if (isShow) {
            mSeeAgainImageView.setVisibility(View.VISIBLE);
        } else
            mSeeAgainImageView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }
}

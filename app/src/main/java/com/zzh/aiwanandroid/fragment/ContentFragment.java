package com.zzh.aiwanandroid.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.View;

import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import androidx.fragment.app.Fragment;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;


public class ContentFragment extends BaseFragment {

    private WebView mWebView;
    private ProgressBar mProgressBar;
    private String mLink;

    public static Fragment getInstance(String param1, String param2) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        mWebView = view.findViewById(R.id.fragment_content_web_view);
        mProgressBar = view.findViewById(R.id.content_loading_progressbar);
    }


    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void initEventAndData() {
        mLink = (String) getArguments().get(Constants.param1);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // 拦截url

                // 用当前浏览器继续浏览
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
            }
        });
        mWebView.loadUrl(mLink);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.content_fragment_layout;
    }
}

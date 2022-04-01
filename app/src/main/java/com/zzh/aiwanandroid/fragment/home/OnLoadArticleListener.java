package com.zzh.aiwanandroid.fragment.home;

import com.zzh.aiwanandroid.bean.Article;

import java.util.List;

/**
 * adapter 获取文章接口
 */
public interface OnLoadArticleListener {
    void getArticle(List<Article> datas);
}

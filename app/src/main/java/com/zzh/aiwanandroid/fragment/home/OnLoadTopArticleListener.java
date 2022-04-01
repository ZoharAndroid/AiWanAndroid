package com.zzh.aiwanandroid.fragment.home;

import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.bean.TopArticle;

import java.util.List;

/**
 * Adapter 获取置顶文章数据接口
 */
public interface OnLoadTopArticleListener {

    void getTopArticle(List<Article> articles);
}

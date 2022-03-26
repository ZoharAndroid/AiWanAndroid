package com.zzh.aiwanandroid.bean;

import java.util.List;

/**
 * 文章列表某页数据
 */
public class ArticlePagesData {

    /**
     * 当前页面
     */
    private int curPage;

    /**
     * 文章详细数据
     */
    private List<Article> datas;

    /**
     * 文章偏移量
     */
    private int offset;

    /**
     * 当前页面加载文章是否加载完成
     */
    private boolean over;

    /**
     * 页面总数
     */
    private int pageCount;

    /**
     * 当前页面加载的文章数目
     */
    private int size;

    /**
     * 文章总数
     */
    private int total;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<Article> getDatas() {
        return datas;
    }

    public void setDatas(List<Article> datas) {
        this.datas = datas;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

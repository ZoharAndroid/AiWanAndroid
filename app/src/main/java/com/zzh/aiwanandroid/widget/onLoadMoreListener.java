package com.zzh.aiwanandroid.widget;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class onLoadMoreListener extends RecyclerView.OnScrollListener {

    private boolean isScroll = false; // 判断是否正在滑动状态
    //private boolean isAllScreen = false;// 数据是否充满全屏

    /**
     * 加载更多接口
     *
     * @param countItem 总数量
     * @param lastItem  最后显示的位置
     */
    protected abstract void onLoading(int countItem, int lastItem);

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
            isScroll = true;
           // isAllScreen = true;
        } else {
            isScroll = false;
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int countItem = layoutManager.getItemCount();
            int lastItem = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
            if (isScroll && countItem != lastItem && lastItem == countItem - 1) {
                onLoading(countItem, lastItem);
            }
        }
    }

//    public boolean isAllScreen() {
//        return isAllScreen;
//    }

}

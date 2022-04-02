package com.zzh.aiwanandroid.fragment.home;

import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.Indicator;
import com.youth.banner.util.BannerUtils;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.bean.ArticlePages;
import com.zzh.aiwanandroid.bean.ArticlePagesData;
import com.zzh.aiwanandroid.bean.BannerRoot;
import com.zzh.aiwanandroid.bean.DataBean;
import com.zzh.aiwanandroid.bean.TopArticle;
import com.zzh.aiwanandroid.utils.CommonUtils;
import com.zzh.aiwanandroid.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章首页RecyclerView的Adapter
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnLoadBannerListener, OnLoadTopArticleListener, OnLoadArticleListener {

    private static final int ITEM_CONTENT = 1;
    private static final int ITEM_FOOT = 2;
    private static final int ITEM_HEADER = 0;

    private boolean isLastData; // 判断是不是所有的最后一条数据

    private List<Article> mArticleDetail; // 文章数据

    private BannerRoot mBannerRoot; // 轮播图数据


    public HomeRecyclerViewAdapter() {

    }

    public HomeRecyclerViewAdapter(List<Article> articles) {
        this.mArticleDetail = articles;
    }

    @Override
    public int getItemViewType(int position) {
        LogUtils.d("position:" + position + "; size:" + mArticleDetail.size());
        if (position == 0) {
            return ITEM_HEADER;
        } else if (position == mArticleDetail.size()) {
            return ITEM_FOOT;
        } else {
            return ITEM_CONTENT;
        }
    }

    protected void setIsLastData(boolean lastData) {
        this.isLastData = lastData;
    }

    protected boolean getIsLastData() {
        return this.isLastData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_banner_layout, parent, false);
            return new HeaderViewHolder(headerView);
        } else if (viewType == ITEM_FOOT) {
            if (getIsLastData()) {
                return new LastFootViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer_lastitem_layout, parent, false));
            } else {
                View footView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer_layout, parent, false);
                return new FootViewHolder(footView);
            }
        } else {
            // 如果是正常内容
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_article, parent, false);
            ContentViewHolder holder = new ContentViewHolder(view);
            // item点击事件
            holder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = holder.getAdapterPosition();
                            Article article = mArticleDetail.get(position-1);
                            // todo:item页面点击事件
                            CommonUtils.ToastShow(article.getTitle());
                        }
                    });

            // item中的收藏按钮点击事件
            holder.mCollectImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Article article = mArticleDetail.get(position-1);
                    // todo: 发送收藏请求
                    CommonUtils.ToastShow("收藏了");
                }
            });
            return holder;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            LogUtils.d("onCreateView:" + position);
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            Banner banner = headerViewHolder.banner;
            // 获取banner加载的数据
            if (mBannerRoot != null) {
                banner.setAdapter(new ImageNetAdapter(mBannerRoot.getData()))
                        // 设置指示器
                        .setIndicator(new CircleIndicator(((HeaderViewHolder) holder).banner.getContext()))
                        // 设置指示器选中的颜色
                        .setIndicatorSelectedColor(banner.getContext().getResources().getColor(R.color.background_color))
                        .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                        .setIndicatorMargins(new IndicatorConfig.Margins(0, 0, BannerConfig.INDICATOR_MARGIN, BannerUtils.dp2px(5)));
            }
        } else if (holder instanceof ContentViewHolder) {
            ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            Article article = mArticleDetail.get(position-1);
            if (article.isFresh()) {
                // 判断是否是新上线的
                contentViewHolder.mNewTextView.setVisibility(View.VISIBLE);
            }else{
                contentViewHolder.mNewTextView.setVisibility(View.GONE);
            }
            // 如果是置顶文章就显示置顶标识
            if (article.getType() == 1){
                contentViewHolder.mTopTextView.setVisibility(View.VISIBLE);
            }else{
                contentViewHolder.mTopTextView.setVisibility(View.GONE);
            }
            // 文章标题
            contentViewHolder.mTitleTextView.setText(article.getTitle());
            // 日期
            contentViewHolder.mDataTextView.setText(article.getNiceDate());
            // 设置作者名，如果网站上的文章可能是某位作者author的，也可能是某位分享人shareUser分享的。
            // 如果是分享人分享的，author 为 null。
            LogUtils.d(article.getAuthor() + " ---" + article.getShareUser());
            if (!TextUtils.isEmpty(article.getAuthor())) {
                contentViewHolder.mAuthorTextView.setText(article.getAuthor());
            }else {
                contentViewHolder.mAuthorTextView.setText(article.getShareUser());
            }
            // 设置标签
            if (!article.getTags().isEmpty()) {
                contentViewHolder.mTagsTextView.setVisibility(View.VISIBLE);
                contentViewHolder.mTagsTextView.setText(article.getTags().get(0).getName());
            }else{
                contentViewHolder.mTagsTextView.setVisibility(View.GONE);
            }
            // 显示项目图片
            if (!TextUtils.isEmpty(article.getEnvelopePic())){
                contentViewHolder.mProjectImage.setVisibility(View.VISIBLE);
                Glide.with(contentViewHolder.mProjectImage).load(article.getEnvelopePic()).into(contentViewHolder.mProjectImage);
            }else{
                contentViewHolder.mProjectImage.setVisibility(View.GONE);
            }
            // 大章节
            contentViewHolder.mSuperChapterTextView.setText(article.getSuperChapterName());
            // 小章节
            contentViewHolder.mChapterTextView.setText(article.getChapterName());

            // 收藏按钮
            if (article.isCollect()) {
                // 判断是否收藏了
                contentViewHolder.mCollectImageView.setImageResource(R.mipmap.icon_collect_y);
            } else {
                contentViewHolder.mCollectImageView.setImageResource(R.mipmap.icon_nav_collect);
            }
        }
    }


    @Override
    public int getItemCount() {
        LogUtils.d("getItemCount:" + mArticleDetail.size());
        return mArticleDetail.size() == 0 ? 0 : mArticleDetail.size() + 1;
    }

    @Override
    public void onLoadBannerData(BannerRoot bannerRoot) {
        mBannerRoot = bannerRoot;
    }

    @Override
    public void getTopArticle(List<Article> topArticles) {
        LogUtils.d("top--" + topArticles.size());
        // 获取到topArticle之后
        mArticleDetail.addAll(topArticles);
    }

    @Override
    public void getArticle(List<Article> articles) {

        mArticleDetail.addAll(articles);
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        Banner banner;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.item_home_banner);
        }
    }


    /**
     * 最后一项数据显示的ITEM
     */
    static class LastFootViewHolder extends RecyclerView.ViewHolder {

        public LastFootViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     * 底部加载
     */
    static class FootViewHolder extends RecyclerView.ViewHolder {
        LinearLayout footView;

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
            footView = itemView.findViewById(R.id.item_footer_container);
        }
    }

    /**
     * 正常内容的ViewHolder
     */
    static class ContentViewHolder extends RecyclerView.ViewHolder {

        TextView mTopTextView; // 置顶
        TextView mNewTextView; // 新
        TextView mAuthorTextView; //作者
        TextView mDataTextView; // 日期
        TextView mTitleTextView; // 标题
        TextView mSuperChapterTextView; // 大章节
        TextView mChapterTextView; // 小章节
        ImageView mCollectImageView; // 收藏图标
        TextView mTagsTextView; // tags
        ImageView mProjectImage; // 项目图片

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            mTopTextView = itemView.findViewById(R.id.item_article_top);
            mNewTextView = itemView.findViewById(R.id.item_article_new);
            mAuthorTextView = itemView.findViewById(R.id.item_article_author);
            mDataTextView = itemView.findViewById(R.id.item_article_time);
            mTitleTextView = itemView.findViewById(R.id.item_article_title);
            mSuperChapterTextView = itemView.findViewById(R.id.item_article_super_chapter);
            mChapterTextView = itemView.findViewById(R.id.item_article_chapter);
            mCollectImageView = itemView.findViewById(R.id.item_article_collect);
            mTagsTextView = itemView.findViewById(R.id.item_article_tags);
            mProjectImage = itemView.findViewById(R.id.item_article_project_image);
        }
    }

}

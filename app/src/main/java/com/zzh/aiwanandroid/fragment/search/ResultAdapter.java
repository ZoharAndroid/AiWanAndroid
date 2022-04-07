package com.zzh.aiwanandroid.fragment.search;

import android.content.Intent;
import android.text.Html;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.activity.ContentActivity;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.fragment.home.HomeRecyclerViewAdapter;
import com.zzh.aiwanandroid.utils.CommonUtils;
import com.zzh.aiwanandroid.utils.LogUtils;

import org.w3c.dom.Text;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int FOOT_TYPE = 1;
    private static final int NORMAL_TYPE = 2;


    private List<Article> mArticles;

    public ResultAdapter(List<Article> articles) {
        this.mArticles = articles;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mArticles.size()) {
            return FOOT_TYPE;
        } else {
            return NORMAL_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == FOOT_TYPE) {
            View footView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer_layout, parent, false);
            FootViewHolder footViewHolder = new FootViewHolder(footView);
            return footViewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_article, parent, false);
            ContentViewHolder holder = new ContentViewHolder(view);
            // item点击事件
            holder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = holder.getAdapterPosition();
                            Article article = mArticles.get(position);
                            // 跳转到新的页面
                            Intent intent = new Intent(view.getContext(), ContentActivity.class);
                            intent.putExtra(Constants.intent_extra_title, article.getTitle());
                            intent.putExtra(Constants.intent_extra_url, article.getLink());
                            view.getContext().startActivity(intent);
                        }
                    });

            // item中的收藏按钮点击事件
            holder.mCollectImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Article article = mArticles.get(position);
                    // todo: 发送收藏请求
                    CommonUtils.ToastShow("收藏了");
                }
            });
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentViewHolder) {
            ResultAdapter.ContentViewHolder contentViewHolder = (ResultAdapter.ContentViewHolder) holder;
            Article article = mArticles.get(position);
            if (article.isFresh()) {
                // 判断是否是新上线的
                contentViewHolder.mNewTextView.setVisibility(View.VISIBLE);
            } else {
                contentViewHolder.mNewTextView.setVisibility(View.GONE);
            }
            // 如果是置顶文章就显示置顶标识
            if (article.getType() == 1) {
                contentViewHolder.mTopTextView.setVisibility(View.VISIBLE);
            } else {
                contentViewHolder.mTopTextView.setVisibility(View.GONE);
            }
            // 文章标题
            contentViewHolder.mTitleTextView.setText(Html.fromHtml(article.getTitle()));
            // 日期
            contentViewHolder.mDataTextView.setText(article.getNiceDate());
            // 设置作者名，如果网站上的文章可能是某位作者author的，也可能是某位分享人shareUser分享的。
            // 如果是分享人分享的，author 为 null。
            if (!TextUtils.isEmpty(article.getAuthor())) {
                contentViewHolder.mAuthorTextView.setText(article.getAuthor());
            } else {
                contentViewHolder.mAuthorTextView.setText(article.getShareUser());
            }
            // 设置标签
            if (article.getTags() != null && !article.getTags().isEmpty()) {
                contentViewHolder.mTagsTextView.setVisibility(View.VISIBLE);
                contentViewHolder.mTagsTextView.setText(article.getTags().get(0).getName());
            } else {
                contentViewHolder.mTagsTextView.setVisibility(View.GONE);
            }
            // 显示项目图片
            if (!TextUtils.isEmpty(article.getEnvelopePic())) {
                contentViewHolder.mProjectImage.setVisibility(View.VISIBLE);
                Glide.with(contentViewHolder.mProjectImage).load(article.getEnvelopePic()).into(contentViewHolder.mProjectImage);
            } else {
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
        if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            if (ResultFragment.getLoadIsOver()) {
                footViewHolder.noDataText.setVisibility(View.VISIBLE);
                footViewHolder.footView.setVisibility(View.GONE);
            } else {
                footViewHolder.noDataText.setVisibility(View.GONE);
                footViewHolder.footView.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return mArticles.size() == 0 ? 0 : mArticles.size() + 1;
    }


    public void getMoreArticle(List<Article> datas) {
        mArticles.addAll(datas);
    }

    /**
     * 底部加载
     */
    static class FootViewHolder extends RecyclerView.ViewHolder {
        LinearLayout footView;
        TextView noDataText;

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
            footView = itemView.findViewById(R.id.item_footer_container);
            noDataText = itemView.findViewById(R.id.item_footer_no_data);
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

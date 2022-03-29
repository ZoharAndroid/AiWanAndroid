package com.zzh.aiwanandroid.fragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.bean.ArticlePages;
import com.zzh.aiwanandroid.bean.ArticlePagesData;
import com.zzh.aiwanandroid.utils.CommonUtils;
import com.zzh.aiwanandroid.utils.LogUtils;

import java.util.List;

/**
 * 文章首页RecyclerView的Adapter
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {


    private List<Article> mArticleDetail;

    public HomeRecyclerViewAdapter(List<Article> articles) {
        this.mArticleDetail = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_article, parent, false);
        ViewHolder holder = new ViewHolder(view);

        // item点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Article article = mArticleDetail.get(position);
                // todo:item页面点击事件
                CommonUtils.ToastShow(article.getTitle());
            }
        });

        // item中的收藏按钮点击事件
        holder.mCollectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Article article = mArticleDetail.get(position);
                // todo: 发送收藏请求
                CommonUtils.ToastShow("收藏了");
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = mArticleDetail.get(position);
        if (article.isFresh()) {
            // 判断是否是新上线的
            holder.mNewTextView.setVisibility(View.VISIBLE);
        }
        holder.mTitleTextView.setText(article.getTitle());
        holder.mDataTextView.setText(article.getNiceDate());
        if (article.getTags().isEmpty()) {
            // 如果tag标签为null
            holder.mAuthorTextView.setText(article.getShareUser());
        } else {
            holder.mTagsTextView.setText(article.getTags().get(0).getName());
        }
        holder.mSuperChapterTextView.setText(article.getSuperChapterName());
        holder.mChapterTextView.setText(article.getChapterName());

        // 收藏按钮
        if (article.isCollect()) {
            // 判断是否收藏了
            holder.mCollectImageView.setImageResource(R.mipmap.icon_collect_y);
        } else {
            holder.mCollectImageView.setImageResource(R.mipmap.icon_nav_collect);
        }
    }


    @Override
    public int getItemCount() {
        return mArticleDetail.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTopTextView; // 置顶
        TextView mNewTextView; // 新
        TextView mAuthorTextView; //作者
        TextView mDataTextView; // 日期
        TextView mTitleTextView; // 标题
        TextView mSuperChapterTextView; // 大章节
        TextView mChapterTextView; // 小章节
        ImageView mCollectImageView; // 收藏图标
        TextView mTagsTextView; // tags

        public ViewHolder(@NonNull View itemView) {
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
        }
    }

}

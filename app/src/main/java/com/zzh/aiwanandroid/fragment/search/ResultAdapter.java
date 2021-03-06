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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.activity.ContentActivity;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.fragment.home.HomeRecyclerViewAdapter;
import com.zzh.aiwanandroid.fragment.structure.TreeDetailFragment;
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
            // item????????????
            holder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = holder.getAdapterPosition();
                            Article article = mArticles.get(position);
                            // ?????????????????????
                            Intent intent = new Intent(view.getContext(), ContentActivity.class);
                            intent.putExtra(Constants.intent_extra_title, article.getTitle());
                            intent.putExtra(Constants.intent_extra_url, article.getLink());
                            view.getContext().startActivity(intent);
                        }
                    });

            // item??????????????????????????????
            holder.mCollectImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Article article = mArticles.get(position);
                    // todo: ??????????????????
                    CommonUtils.ToastShow("?????????");
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
                // ???????????????????????????
                contentViewHolder.mNewTextView.setVisibility(View.VISIBLE);
            } else {
                contentViewHolder.mNewTextView.setVisibility(View.GONE);
            }
            // ??????????????????????????????????????????
            if (article.getType() == 1) {
                contentViewHolder.mTopTextView.setVisibility(View.VISIBLE);
            } else {
                contentViewHolder.mTopTextView.setVisibility(View.GONE);
            }
            // ????????????
            contentViewHolder.mTitleTextView.setText(Html.fromHtml(article.getTitle()));
            // ??????
            contentViewHolder.mDataTextView.setText(article.getNiceDate());
            // ???????????????????????????????????????????????????????????????author?????????????????????????????????shareUser????????????
            // ??????????????????????????????author ??? null???
            if (!TextUtils.isEmpty(article.getAuthor())) {
                contentViewHolder.mAuthorTextView.setText(article.getAuthor());
            } else {
                contentViewHolder.mAuthorTextView.setText(article.getShareUser());
            }
            // ????????????
            if (article.getTags() != null && !article.getTags().isEmpty()) {
                contentViewHolder.mTagsTextView.setVisibility(View.VISIBLE);
                contentViewHolder.mTagsTextView.setText(article.getTags().get(0).getName());
            } else {
                contentViewHolder.mTagsTextView.setVisibility(View.GONE);
            }
            // ??????????????????
            if (!TextUtils.isEmpty(article.getEnvelopePic())) {
                contentViewHolder.mProjectImage.setVisibility(View.VISIBLE);
                Glide.with(contentViewHolder.mProjectImage).load(article.getEnvelopePic()).into(contentViewHolder.mProjectImage);
            } else {
                contentViewHolder.mProjectImage.setVisibility(View.GONE);
            }
            // ?????????
            contentViewHolder.mSuperChapterTextView.setText(article.getSuperChapterName());
            // ?????????
            contentViewHolder.mChapterTextView.setText(article.getChapterName());

            // ????????????
            if (article.isCollect()) {
                // ?????????????????????
                contentViewHolder.mCollectImageView.setImageResource(R.mipmap.icon_collect_y);
            } else {
                contentViewHolder.mCollectImageView.setImageResource(R.mipmap.icon_nav_collect);
            }
        }
        if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            if (ResultFragment.getLoadIsOver() || TreeDetailFragment.getLoadIsOver()) {
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
     * ????????????
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
     * ???????????????ViewHolder
     */
    static class ContentViewHolder extends RecyclerView.ViewHolder {

        TextView mTopTextView; // ??????
        TextView mNewTextView; // ???
        TextView mAuthorTextView; //??????
        TextView mDataTextView; // ??????
        TextView mTitleTextView; // ??????
        TextView mSuperChapterTextView; // ?????????
        TextView mChapterTextView; // ?????????
        ImageView mCollectImageView; // ????????????
        TextView mTagsTextView; // tags
        ImageView mProjectImage; // ????????????

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

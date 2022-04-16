package com.zzh.aiwanandroid.fragment.project;

import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.activity.ContentActivity;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.utils.LogUtils;

import java.util.List;

public class ProjectDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_NORMAL = 0; // 正常内容
    private static int TYPE_FOOT = 1; // 尾部

    private List<Article> mArticles;
    private Activity mActivity;

    private boolean isOver;

    public ProjectDetailAdapter(Activity activity, List<Article> mArticles) {
        this.mActivity = activity;
        this.mArticles = mArticles;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mArticles.size()) {
            // 滑动到底部了
            return TYPE_FOOT; // 返回底部
        }
        return TYPE_NORMAL; // 返回正常
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOT) {
            // 底部布局
            View footView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer_layout, parent, false);

            FootViewHolder footHolder = new FootViewHolder(footView);

            return footHolder;

        } else {
            // 正常布局
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_detail, parent, false);
            ProjectViewHolder holder = new ProjectViewHolder(view);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转到Activity
                    Article article = mArticles.get(holder.getAdapterPosition());
                    startActivity(article.getTitle(), article.getLink());
                }
            });

            holder.collectView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // todo:收藏

                }
            });
            return holder;
        }
    }

    /**
     * 获取数据加载完毕标志
     *
     * @return
     */
    private  boolean getIsOver(){
        return isOver;
    }

    /**
     * 所有的数据是否加载完毕
     *
     * @param isOver
     */
    public void setIsOver(boolean isOver){
        this.isOver = isOver;
    }


    /**
     * 开启详情界面
     *
     * @param title
     * @param url
     */
    private void startActivity(String title, String url) {
        Intent intent = new Intent(mActivity, ContentActivity.class);
        intent.putExtra(Constants.intent_extra_title, title);
        intent.putExtra(Constants.intent_extra_url, url);
        mActivity.startActivity(intent);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProjectViewHolder) {
            Article article = mArticles.get(position);
            ((ProjectViewHolder) holder).titleView.setText(article.getTitle());
            ((ProjectViewHolder) holder).dateView.setText(article.getNiceDate());
            ((ProjectViewHolder) holder).descView.setText(article.getDesc());
            ((ProjectViewHolder) holder).authorView.setText(article.getAuthor());
            Glide.with(((ProjectViewHolder) holder).projectView).load(article.getEnvelopePic()).into(((ProjectViewHolder) holder).projectView);
        }

        if (holder instanceof FootViewHolder){
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            LogUtils.d(getIsOver()+"====");
            if (getIsOver()) {
                footViewHolder.footView.setVisibility(View.GONE);
                footViewHolder.noDataView.setVisibility(View.VISIBLE);
            } else {
                footViewHolder.footView.setVisibility(View.VISIBLE);
                footViewHolder.noDataView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mArticles.size() == 0 ? 0 : mArticles.size() + 1;
    }


    /**
     * 底部
     */
    static class FootViewHolder extends RecyclerView.ViewHolder {

        LinearLayout footView;
        TextView noDataView;

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);

            footView = itemView.findViewById(R.id.item_footer_container);
            noDataView = itemView.findViewById(R.id.item_footer_no_data);
        }
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        ImageView projectView;
        TextView titleView;
        TextView authorView;
        TextView descView;
        TextView dateView;
        ImageView collectView;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            projectView = itemView.findViewById(R.id.image_view_item_project_image);
            titleView = itemView.findViewById(R.id.text_view_item_project_title);
            authorView = itemView.findViewById(R.id.text_view_item_project_author);
            descView = itemView.findViewById(R.id.text_view_item_project_desc);
            dateView = itemView.findViewById(R.id.text_view_item_project_date);
            collectView = itemView.findViewById(R.id.image_view_item_project_collect);

        }
    }
}

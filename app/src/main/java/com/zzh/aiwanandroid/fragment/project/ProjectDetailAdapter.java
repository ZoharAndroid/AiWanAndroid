package com.zzh.aiwanandroid.fragment.project;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.activity.ContentActivity;
import com.zzh.aiwanandroid.bean.Article;

import java.util.List;

public class ProjectDetailAdapter extends RecyclerView.Adapter<ProjectDetailAdapter.ProjectViewHolder> {

    private List<Article> mArticles;
    private Activity mActivity;

    public ProjectDetailAdapter(Activity activity,List<Article> mArticles) {
        this.mActivity = activity;
        this.mArticles = mArticles;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_detail, parent, false);
        ProjectViewHolder holder = new ProjectViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到Activity
                Article article = mArticles.get(holder.getAdapterPosition());
                startActivity(article.getTitle(),article.getLink());
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

    /**
     * 开启详情界面
     *
     * @param title
     * @param url
     */
    private void startActivity(String title,String url){
        Intent intent = new Intent(mActivity,ContentActivity.class);
        intent.putExtra(Constants.intent_extra_title,title);
        intent.putExtra(Constants.intent_extra_url,url);
        mActivity.startActivity(intent);
    }


    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Article article = mArticles.get(position);
        holder.titleView.setText(article.getTitle());
        holder.dateView.setText(article.getNiceDate());
        holder.descView.setText(article.getDesc());
        holder.authorView.setText(article.getAuthor());
        Glide.with(holder.projectView).load(article.getEnvelopePic()).into(holder.projectView);
    }

    @Override
    public int getItemCount() {
        return mArticles.size() == 0 ? 0 : mArticles.size();
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

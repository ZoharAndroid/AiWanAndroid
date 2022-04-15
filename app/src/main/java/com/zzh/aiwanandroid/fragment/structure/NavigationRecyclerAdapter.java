package com.zzh.aiwanandroid.fragment.structure;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.bean.NavigationBean;
import com.zzh.aiwanandroid.utils.CommonUtils;

import java.util.List;
import java.util.Random;

public class NavigationRecyclerAdapter extends RecyclerView.Adapter<NavigationRecyclerAdapter.NavigationViewHolder> {

    private Activity mContext;

    private List<NavigationBean.Navigation> mNavigationList;

    public NavigationRecyclerAdapter(Activity context, List<NavigationBean.Navigation> navigationList) {
        this.mContext = context;
        this.mNavigationList = navigationList;
    }

    @NonNull
    @Override
    public NavigationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_system, parent, false);
        NavigationViewHolder holder = new NavigationViewHolder(view);
        // 标签
        holder.mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                return true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationViewHolder holder, int position) {
        holder.mTopTreeTextView.setText(mNavigationList.get(position).getName());
        List<Article> mArticles = mNavigationList.get(position).getArticles();
        holder.mFlowLayout.setAdapter(new TagAdapter(mArticles) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
                tv.setText(mArticles.get(position).getTitle());
                tv.setTextColor(CommonUtils.getColors(mContext)[new Random().nextInt(CommonUtils.getColors(mContext).length)]);
                return tv;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNavigationList.size() == 0 ? 0 : mNavigationList.size();
    }

    static class NavigationViewHolder extends RecyclerView.ViewHolder {
        TextView mTopTreeTextView;
        TagFlowLayout mFlowLayout;
        View itemView;
        ImageView imageArrow;

        public NavigationViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            mTopTreeTextView = itemView.findViewById(R.id.item_top_tree_text_view);
            mFlowLayout = itemView.findViewById(R.id.item_tree_layout);
            imageArrow = itemView.findViewById(R.id.item_tree_arrow);
        }
    }
}

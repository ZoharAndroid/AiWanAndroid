package com.zzh.aiwanandroid.fragment.structure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.activity.TreeActivity;
import com.zzh.aiwanandroid.bean.TreeBean;
import com.zzh.aiwanandroid.utils.CommonUtils;
import com.zzh.aiwanandroid.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SystemRecyclerAdapter extends RecyclerView.Adapter<SystemRecyclerAdapter.SystemViewHolder> {

    // 头部标题
    private List<TreeBean.Tree> mTopTrees;
    private Activity mContext;


    public SystemRecyclerAdapter(Activity context, List<TreeBean.Tree> mTopTress) {
        this.mContext = context;
        this.mTopTrees = mTopTress;
    }


    public void getMoreData(List<TreeBean.Tree> trees) {
        int startPosition = mTopTrees.size();
        mTopTrees.addAll(trees);


        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyItemRangeChanged(startPosition, trees.size());
            }
        });

    }

    @NonNull
    @Override
    public SystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_system, parent, false);
        SystemViewHolder holder = new SystemViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTreeActivity(v.getContext(), mTopTrees.get(holder.getAdapterPosition()).getName(), mTopTrees.get(holder.getAdapterPosition()).getChildren(), 0);
            }
        });

        holder.mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                // tag标签点击事件
                startTreeActivity(view.getContext(), mTopTrees.get(holder.getAdapterPosition()).getName(), mTopTrees.get(holder.getAdapterPosition()).getChildren(), position);
                return true;
            }
        });

        holder.imageArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTreeActivity(v.getContext(), mTopTrees.get(holder.getAdapterPosition()).getName(), mTopTrees.get(holder.getAdapterPosition()).getChildren(), 0);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SystemViewHolder holder, int position) {
        TreeBean.Tree topTress = mTopTrees.get(position);
        holder.mTopTreeTextView.setText(topTress.getName());
        List<TreeBean.Tree> treeList = mTopTrees.get(position).getChildren();
        holder.mFlowLayout.setAdapter(new TagAdapter(treeList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, holder.mFlowLayout, false);
                tv.setText(treeList.get(position).getName());
                int index = new Random().nextInt(CommonUtils.getColors(mContext).length);
                tv.setTextColor(CommonUtils.getColors(mContext)[index]);
                return tv;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mTopTrees.size() == 0 ? 0 : mTopTrees.size();
    }

    /**
     * 开启Tree详细页面
     *
     * @param context
     * @param trees
     * @param position
     */
    private void startTreeActivity(Context context, String topTreeTitle, List<TreeBean.Tree> trees, int position) {
        Intent intent = new Intent(context, TreeActivity.class);
        intent.putExtra(Constants.intent_extra_title, topTreeTitle);
        intent.putExtra(Constants.intent_extra_position, position);
        intent.putParcelableArrayListExtra(Constants.intent_extra_trees, (ArrayList<? extends Parcelable>) trees);
        context.startActivity(intent);
    }


    static class SystemViewHolder extends RecyclerView.ViewHolder {
        TextView mTopTreeTextView;
        TagFlowLayout mFlowLayout;
        View itemView;
        ImageView imageArrow;

        public SystemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            mTopTreeTextView = itemView.findViewById(R.id.item_top_tree_text_view);
            mFlowLayout = itemView.findViewById(R.id.item_tree_layout);
            imageArrow = itemView.findViewById(R.id.item_tree_arrow);
        }
    }
}

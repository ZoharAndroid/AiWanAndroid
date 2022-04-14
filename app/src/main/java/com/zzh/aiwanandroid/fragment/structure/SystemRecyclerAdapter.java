package com.zzh.aiwanandroid.fragment.structure;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.bean.TreeBean;
import com.zzh.aiwanandroid.utils.LogUtils;

import java.util.List;
import java.util.Random;

public class SystemRecyclerAdapter extends RecyclerView.Adapter<SystemRecyclerAdapter.SystemViewHolder> {

    private List<TreeBean.Tree> mTopTrees;
    private Activity mContext;


    public SystemRecyclerAdapter(Activity context, List<TreeBean.Tree> mTopTress) {
        this.mContext = context;
        this.mTopTrees = mTopTress;
        LogUtils.d(this.mTopTrees.size() + "SystemRecyclerAdapter");
    }


    public void getMoreData(List<TreeBean.Tree> trees) {
        int startPosition = mTopTrees.size();
        mTopTrees.addAll(trees);


        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyItemRangeChanged(startPosition,trees.size());
            }
        });

    }

    @NonNull
    @Override
    public SystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtils.d("onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_system, parent, false);
        SystemViewHolder holder = new SystemViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                // int index = new Random().nextInt(colors.length);
                //tv.setTextColor(colors[index]);
                return tv;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mTopTrees.size() == 0 ? 0 : mTopTrees.size();
    }

    static class SystemViewHolder extends RecyclerView.ViewHolder {
        TextView mTopTreeTextView;
        TagFlowLayout mFlowLayout;
        View itemView;


        public SystemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            mTopTreeTextView = itemView.findViewById(R.id.item_top_tree_text_view);
            mFlowLayout = itemView.findViewById(R.id.item_tree_layout);
        }
    }
}

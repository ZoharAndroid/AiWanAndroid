package com.zzh.aiwanandroid.fragment.home;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.util.BannerUtils;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.activity.ContentActivity;
import com.zzh.aiwanandroid.bean.BannerRoot;
import com.zzh.aiwanandroid.bean.DataBean;

import java.util.List;

public class ImageNetAdapter extends BannerAdapter<BannerRoot.Banner, ImageTitleHolder> {

    public ImageNetAdapter(List<BannerRoot.Banner> mDatas) {
        super(mDatas);
    }


    @Override
    public ImageTitleHolder onCreateHolder(ViewGroup parent, int viewType) {

        ImageTitleHolder holder =
                new ImageTitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_image, parent, false));

        return holder;

    }

    @Override
    public void onBindView(ImageTitleHolder holder, BannerRoot.Banner data, int position, int size) {
//通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        Glide.with(holder.itemView)
                .load(data.getImagePath())
                //.thumbnail(Glide.with(holder.itemView).load(R.drawable.loading))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.imageView);
        holder.title.setText(data.getTitle());

        // Banner的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ContentActivity.class);
                intent.putExtra(Constants.intent_extra_url,data.getUrl());
                intent.putExtra(Constants.intent_extra_title,data.getTitle());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }
}

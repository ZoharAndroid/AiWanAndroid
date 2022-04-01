package com.zzh.aiwanandroid.fragment.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zzh.aiwanandroid.R;

public class ImageTitleHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView title;

    public ImageTitleHolder(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.banner_image);
        this.title = itemView.findViewById(R.id.banner_title);
    }
}

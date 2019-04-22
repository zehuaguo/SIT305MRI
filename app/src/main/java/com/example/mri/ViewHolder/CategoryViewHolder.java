package com.example.mri.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mri.ClickItem;
import com.example.mri.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtResName;
    public ImageView imageView;

    private ClickItem clickItem;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        txtResName = (TextView)itemView.findViewById(R.id.category_name);
        imageView = (ImageView)itemView.findViewById(R.id.food_category);

        itemView.setOnClickListener(this);
    }

    public void setClickItem(ClickItem clickItem) {
        this.clickItem = clickItem;
    }

    @Override
    public void onClick(View v) {
        clickItem.onClick(v,getAdapterPosition(),false);
    }
}

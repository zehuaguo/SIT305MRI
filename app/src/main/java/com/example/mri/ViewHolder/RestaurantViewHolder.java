package com.example.mri.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mri.ClickItem;
import com.example.mri.R;

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView restaurant_name;
    public ImageView restaurant_image;

    private ClickItem clickItem;

    public void setClickItem(ClickItem clickItem) {
        this.clickItem = clickItem;
    }

    public RestaurantViewHolder(View itemView) {
        super(itemView);

        restaurant_name = (TextView)itemView.findViewById(R.id.restaurant_name);
        restaurant_image = (ImageView)itemView.findViewById(R.id.restaurant_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clickItem.onClick(v,getAdapterPosition(),false);
    }
}

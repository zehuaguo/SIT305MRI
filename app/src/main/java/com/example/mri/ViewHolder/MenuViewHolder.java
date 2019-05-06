package com.example.mri.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mri.R;
import com.squareup.picasso.Picasso;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    View view;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;
    }

    public void setPhoto(String image){
        ImageView imageView = view.findViewById(R.id.menuImage);
        Picasso.get().load(image).into(imageView);
    }
}

package com.example.mri.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mri.R;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    public TextView txtUserId, txtComment;
    public RatingBar ratingBar;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        txtComment = (TextView)itemView.findViewById(R.id.txtComment);
        txtUserId = (TextView)itemView.findViewById(R.id.txtUserId);
        ratingBar = (RatingBar)itemView.findViewById(R.id.ratingBar);
    }
}

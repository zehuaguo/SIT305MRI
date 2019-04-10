package com.example.mri.Western;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mri.R;

import java.util.ArrayList;

public class WestAdapter extends RecyclerView.Adapter<WestAdapter.ViewHolder> {

    Context context;
    ArrayList<Western> westerns;

    public WestAdapter(Context c, ArrayList<Western> w)
    {
        context = c;
        westerns = w;
    }

    @NonNull
    @Override
    public WestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.customlayout,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WestAdapter.ViewHolder holder, int position) {
        holder.name.setText(westerns.get(position).getName());
        holder.location.setText(westerns.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return westerns.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,location;
        ImageView westernPic;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_name);
            location = (TextView) itemView.findViewById(R.id.textView_location);
            westernPic = (ImageView) itemView.findViewById(R.id.resPic);
        }
    }
}


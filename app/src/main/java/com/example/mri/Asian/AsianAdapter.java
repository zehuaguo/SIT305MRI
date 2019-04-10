package com.example.mri.Asian;

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

public class AsianAdapter extends RecyclerView.Adapter<AsianAdapter.ViewHolder> {

    Context context;
    ArrayList<Asian> asians;

    public AsianAdapter(Context c, ArrayList<Asian> a)
    {
        context = c;
        asians = a;
    }

    @NonNull
    @Override
    public AsianAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.customlayout,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AsianAdapter.ViewHolder holder, int position) {
        holder.name.setText(asians.get(position).getName());
        holder.location.setText(asians.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return asians.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,location;
        ImageView asianPic;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_name);
            location = (TextView) itemView.findViewById(R.id.textView_location);
            asianPic = (ImageView) itemView.findViewById(R.id.resPic);
        }
    }
}


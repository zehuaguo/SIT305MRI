package com.example.mri.Dessert;

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

public class DesAdapter extends RecyclerView.Adapter<DesAdapter.ViewHolder> {

    Context context;
    ArrayList<Dessert> desserts;

    public DesAdapter(Context c, ArrayList<Dessert> d)
    {
        context = c;
        desserts = d;
    }

    @NonNull
    @Override
    public DesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.customlayout,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DesAdapter.ViewHolder holder, int position) {
        holder.name.setText(desserts.get(position).getName());
        holder.location.setText(desserts.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return desserts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,location;
        ImageView dessertPic;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_name);
            location = (TextView) itemView.findViewById(R.id.textView_location);
            dessertPic = (ImageView) itemView.findViewById(R.id.resPic);
        }
    }
}


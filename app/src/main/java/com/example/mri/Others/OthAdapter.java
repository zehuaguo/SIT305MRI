package com.example.mri.Others;

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

class OthersAdapter extends RecyclerView.Adapter<OthersAdapter.ViewHolder> {

    Context context;
    ArrayList<Others> others;

    public OthersAdapter(Context c, ArrayList<Others> o)
    {
        context = c;
        others = o;
    }

    @NonNull
    @Override
    public OthersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.customlayout,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OthersAdapter.ViewHolder holder, int position) {
        holder.name.setText(others.get(position).getName());
        holder.location.setText(others.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return others.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,location;
        ImageView othersPic;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_name);
            location = (TextView) itemView.findViewById(R.id.textView_location);
            othersPic = (ImageView) itemView.findViewById(R.id.resPic);
        }
    }
}



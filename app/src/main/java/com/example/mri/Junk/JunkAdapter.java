package com.example.mri.Junk;

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

public class JunkAdapter extends RecyclerView.Adapter<JunkAdapter.ViewHolder> {

    Context context;
    ArrayList<Junk> junks;

    public JunkAdapter(Context c, ArrayList<Junk> j)
    {
        context = c;
        junks = j;
    }

    @NonNull
    @Override
    public JunkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.customlayout,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JunkAdapter.ViewHolder holder, int position) {
        holder.name.setText(junks.get(position).getName());
        holder.location.setText(junks.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return junks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,location;
        ImageView junkPic;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_name);
            location = (TextView) itemView.findViewById(R.id.textView_location);
            junkPic = (ImageView) itemView.findViewById(R.id.resPic);
        }
    }
}


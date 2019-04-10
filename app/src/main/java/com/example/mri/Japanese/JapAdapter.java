package com.example.mri.Japanese;

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

public class JapAdapter extends RecyclerView.Adapter<JapAdapter.ViewHolder> {

    Context context;
    ArrayList<Japanese> japanese;

    public JapAdapter(Context c, ArrayList<Japanese> j)
    {
        context = c;
        japanese = j;
    }

    @NonNull
    @Override
    public JapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.customlayout,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JapAdapter.ViewHolder holder, int position) {
        holder.name.setText(japanese.get(position).getName());
        holder.location.setText(japanese.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return japanese.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,location;
        ImageView japanesePic;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_name);
            location = (TextView) itemView.findViewById(R.id.textView_location);
            japanesePic = (ImageView) itemView.findViewById(R.id.resPic);
        }
    }
}


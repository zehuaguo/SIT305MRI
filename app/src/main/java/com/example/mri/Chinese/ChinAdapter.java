package com.example.mri.Chinese;

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

public class ChinAdapter extends RecyclerView.Adapter<ChinAdapter.ViewHolder> {

    Context context;
    ArrayList<Chinese> chinese;

    public ChinAdapter(Context c, ArrayList<Chinese> ch)
    {
        context = c;
        chinese = ch;
    }

    @NonNull
    @Override
    public ChinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.customlayout,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChinAdapter.ViewHolder holder, int position) {
        holder.name.setText(chinese.get(position).getName());
        holder.location.setText(chinese.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return chinese.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,location;
        ImageView chinesePic;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_name);
            location = (TextView) itemView.findViewById(R.id.textView_location);
            chinesePic = (ImageView) itemView.findViewById(R.id.resPic);
        }
    }
}


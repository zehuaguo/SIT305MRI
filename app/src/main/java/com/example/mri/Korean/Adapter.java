package com.example.mri.Korean;

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

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Korean> koreans;

    public Adapter(Context c, ArrayList<Korean> k)
    {
        context = c;
        koreans = k;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.customlayout,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.name.setText(koreans.get(position).getName());
        holder.location.setText(koreans.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return koreans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,location;
        ImageView koreanPic;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_name);
            location = (TextView) itemView.findViewById(R.id.textView_location);
            koreanPic = (ImageView) itemView.findViewById(R.id.resPic);
        }
    }
}

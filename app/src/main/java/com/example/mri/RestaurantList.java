package com.example.mri;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mri.DataModel.Restaurants;
import com.example.mri.ViewHolder.RestaurantViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class RestaurantList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference restaurantList;

    String categoryId = "";

    FirebaseRecyclerAdapter<Restaurants, RestaurantViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        database = FirebaseDatabase.getInstance();
        restaurantList = database.getReference("Restaurants");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_restaurant);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && categoryId != null)
        {
            loadRestaurantList(categoryId);
        }

    }

    private void loadRestaurantList(String categoryId){

        FirebaseRecyclerOptions<Restaurants> options =
                new FirebaseRecyclerOptions.Builder<Restaurants>()
                        .setQuery(restaurantList.orderByChild("RestaurantId").equalTo(categoryId), Restaurants.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Restaurants, RestaurantViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position, @NonNull Restaurants model) {
                holder.restaurant_name.setText(model.getRestaurantName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(holder.restaurant_image);

                final Restaurants local = model;
                holder.setClickItem(new ClickItem() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent restaurantDetail = new Intent(RestaurantList.this, RestaurantDetail.class);

                        restaurantDetail.putExtra("DetailId",adapter.getRef(position).getKey());
                        startActivity(restaurantDetail);
                    }
                });
            }

            @NonNull
            @Override
            public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_restaurant_list, parent, false);
                RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
                return viewHolder;
            }
        };

        recyclerView.setAdapter(adapter);
    }
}

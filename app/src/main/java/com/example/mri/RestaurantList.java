package com.example.mri;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mri.DataModel.Restaurants;
import com.example.mri.ViewHolder.RestaurantViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference restaurantList;

    String categoryId = "";

    FirebaseRecyclerAdapter<Restaurants, RestaurantViewHolder> adapter;

    FirebaseRecyclerAdapter<Restaurants, RestaurantViewHolder> searchAdapter;
    List<String> searchList = new ArrayList<>();
    MaterialSearchBar materialSearchBar;

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

        materialSearchBar = (MaterialSearchBar)findViewById(R.id.searchBar);
        materialSearchBar.setHint("Enter the restaurant");

        loadSearch();
        materialSearchBar.setLastSuggestions(searchList);
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<String>();
                for (String search:searchList)
                {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled)
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

    }

    private void startSearch(CharSequence text) {

        FirebaseRecyclerOptions<Restaurants> options =
                new FirebaseRecyclerOptions.Builder<Restaurants>()
                .setQuery(restaurantList.orderByChild("RestaurantName").equalTo(text.toString()), Restaurants.class)
                .build();

        searchAdapter = new FirebaseRecyclerAdapter<Restaurants, RestaurantViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position, @NonNull Restaurants model) {
                holder.restaurant_name.setText(model.getRestaurantName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(holder.restaurant_image);

                final Restaurants local = model;
                holder.setClickItem(new ClickItem() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent restaurantDetail = new Intent(RestaurantList.this, RestaurantDetail.class);

                        restaurantDetail.putExtra("DetailId",searchAdapter.getRef(position).getKey());
                        startActivity(restaurantDetail);
                    }
                });
            }

            @NonNull
            @Override
            public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.restaurants_item, viewGroup, false);
                RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
                return null;
            }
        };
        recyclerView.setAdapter(searchAdapter);
    }


    private void loadSearch(){
        restaurantList.orderByChild("RestaurantId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                        {
                            Restaurants item = postSnapshot.getValue(Restaurants.class);
                            searchList.add(item.getRestaurantName());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_item, parent, false);
                RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
                return viewHolder;
            }
        };

        recyclerView.setAdapter(adapter);
    }
}

package com.example.mri;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mri.DataModel.Menu;
import com.example.mri.DataModel.Restaurants;
import com.example.mri.ViewHolder.MenuViewHolder;
import com.example.mri.ViewHolder.RestaurantViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference menuRef;

    String menuId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        menuRef = database.getReference("Menu");
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent() != null)
            menuId = getIntent().getStringExtra(Restaurants.INTENT_MENU_ID);

        Query query = menuRef.orderByChild("menuId").equalTo(menuId);

        FirebaseRecyclerOptions<Menu> options = new FirebaseRecyclerOptions.Builder<Menu>()
                        .setQuery(query, Menu.class)
                        .build();

        FirebaseRecyclerAdapter<Menu, MenuViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Menu, MenuViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull Menu model) {
                        holder.setPhoto(model.getMenuImage());
                    }

                    @NonNull
                    @Override
                    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_card, parent, false);
                        MenuViewHolder viewHolder = new MenuViewHolder(view);
                        return viewHolder;
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}

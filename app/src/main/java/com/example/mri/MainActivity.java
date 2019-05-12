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
import android.widget.Button;

import com.example.mri.DataModel.Category;
import com.example.mri.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference category;

    RecyclerView recyler_category;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;

    Button BtnSignUp;
    Button BtnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        category = database.getReference("category");

        recyler_category = (RecyclerView)findViewById(R.id.foodList);
        recyler_category.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyler_category.setLayoutManager(layoutManager);

        loadCategory();
/*
        BtnSignUp = findViewById(R.id.btnSignUp);
        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
*/
        BtnSignIn = findViewById(R.id.btnLogin);
        BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });

    }

    private void loadCategory(){

        FirebaseRecyclerOptions<Category> options =
                new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(category, Category.class)
                .build();

        adapter
                = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull Category model) {
                holder.txtResName.setText(model.getResName());
                Picasso.get().load(model.getImage()).into(holder.imageView);

                final Category clickItem = model;
                holder.setClickItem(new ClickItem() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent restaurantList = new Intent(MainActivity.this,RestaurantList.class);

                        restaurantList.putExtra("CategoryId", adapter.getRef(position).getKey());
                        startActivity(restaurantList);
                    }
                });
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
                CategoryViewHolder viewHolder = new CategoryViewHolder(view);
                return viewHolder;
            }
        };
        recyler_category.setAdapter(adapter);
    }

    public void SignUp(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void SignIn(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}

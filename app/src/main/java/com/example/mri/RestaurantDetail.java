package com.example.mri;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mri.DataModel.Restaurants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RestaurantDetail extends AppCompatActivity {

    TextView restaurant_name, location;
    ImageView img_restaurant;
    CollapsingToolbarLayout collapsingToolbarLayout;

    String detailId = "";

    FirebaseDatabase database;
    DatabaseReference restaurantDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        database = FirebaseDatabase.getInstance();
        restaurantDetail = database.getReference("Detail");

        restaurant_name = (TextView)findViewById(R.id.txtRes_name);
        location = (TextView)findViewById(R.id.txtLocation);
        img_restaurant = (ImageView)findViewById(R.id.img_restaurant);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);

        if (getIntent() != null)
            detailId = getIntent().getStringExtra("DetailId");
        if (!detailId.isEmpty())
        {

        }
    }

    private void getDetailRestaurant(String detailId){
        restaurantDetail.child(detailId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Restaurants restaurants = dataSnapshot.getValue(Restaurants.class);

                Picasso.with(getBaseContext()).load(restaurants.getImage()).into(img_restaurant);

                restaurant_name.setText(restaurants.getRestaurantName());

                location.setText(restaurants.getLocation());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

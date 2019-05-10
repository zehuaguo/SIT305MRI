package com.example.mri;

import android.app.Dialog;
import android.content.Intent;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mri.DataModel.Rate;
import com.example.mri.DataModel.Restaurants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class RestaurantDetail extends AppCompatActivity implements RatingDialogListener {

    Button menu, comment, rating, map;
    TextView restaurant_name, location;
    ImageView img_restaurant;
    CollapsingToolbarLayout collapsingToolbarLayout;
    RatingBar ratingBar;

    String detailId = "";

    FirebaseDatabase database;
    DatabaseReference restaurantDetail;
    DatabaseReference ratingTbl;

    private static final String TAG = "Restaurant";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        database = FirebaseDatabase.getInstance();
        restaurantDetail = database.getReference("Detail");
        ratingTbl = database.getReference("Rating");

        restaurant_name = (TextView)findViewById(R.id.txtRes_name);
        location = (TextView)findViewById(R.id.txtLocation);
        img_restaurant = (ImageView)findViewById(R.id.img_restaurant);
        menu = (Button)findViewById(R.id.btnMenu);
        comment = (Button)findViewById(R.id.btnComment);
        rating = (Button)findViewById(R.id.btnRating);
        map = (Button)findViewById(R.id.btnMap);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuPage();
            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantDetail.this,CommentActivity.class);
                intent.putExtra(Restaurants.INTENT_RESTURANT_ID,detailId);
                startActivity(intent);
            }
        });


        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingDialog();
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);

        if (getIntent() != null)
            detailId = getIntent().getStringExtra("DetailId");
        if (!detailId.isEmpty())
        {
            getDetailRestaurant(detailId);
            getRatingRestaurant(detailId);
        }

        // Map function
        if (isServicesOk()){
            MapScreen();
        }

    }

    public void MenuPage(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void MapScreen(){
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantDetail.this, GoogleActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDetailRestaurant(String detailId){
        restaurantDetail.child(detailId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Restaurants restaurants = dataSnapshot.getValue(Restaurants.class);

                Picasso.get().load(restaurants.getImage()).into(img_restaurant);

                restaurant_name.setText(restaurants.getRestaurantName());

                location.setText(restaurants.getLocation());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getRatingRestaurant(String detailId){
        Query restaurantRating = ratingTbl.orderByChild("DetailId").equalTo(detailId);

        restaurantRating.addValueEventListener(new ValueEventListener() {
            int count = 0, sum = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Rate item = postSnapshot.getValue(Rate.class);
                    sum += Integer.parseInt(item.getRateValue());
                }
                if (count != 0)
                {
                    float average = sum/count;
                    ratingBar.setRating(average);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showRatingDialog(){
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNoteDescriptions(Arrays.asList("Bad", "Unsatisfied", "Normal", "Satisfied", "Good"))
                .setDefaultRating(1)
                .setTitle("Rate this restaurant")
                .setDescription("Please select some stars and leave your comment")
                .setTitleTextColor(R.color.colorPrimary)
                .setDescriptionTextColor(R.color.colorPrimary)
                .setHint("Please write your review here....")
                .setHintTextColor(R.color.colorAccent)
                .setCommentTextColor(R.color.white)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .create(RestaurantDetail.this)
                .show();
    }

    public boolean isServicesOk(){
        Log.d(TAG,"isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(RestaurantDetail.this);

        if (available == ConnectionResult.SUCCESS){
            Log.d(TAG, "isServicesOK: google play services is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG,"isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(RestaurantDetail.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else {
            Toast.makeText(this,"you can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onPositiveButtonClicked(int value, @NotNull String comments) {
        final Rate rating = new Rate(Restaurants.currentUser.getId(),
                detailId,
                String.valueOf(value),
                comments);

        ratingTbl.push()
                .setValue(rating)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(RestaurantDetail.this,"Thank you for your feedback", Toast.LENGTH_SHORT).show();
                    }
                });
        /*
        ratingTbl.child(Restaurants.currentUser.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(Restaurants.currentUser.getId()).exists())
                {
                    ratingTbl.child(Restaurants.currentUser.getId()).setValue(rating);  //Update new value
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */
    }
}

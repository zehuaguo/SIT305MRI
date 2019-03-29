package com.example.mri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

     ImageButton androidImagebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidImagebutton = findViewById(R.id.imageButton);
        androidImagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Restaurantlist();
            }
        });
    }

    public void Restaurantlist(){
        Intent intent = new Intent(this,RestaurantList.class);
        startActivity(intent);
    }
}

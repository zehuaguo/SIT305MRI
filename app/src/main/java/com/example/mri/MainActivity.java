package com.example.mri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

     ImageButton androidImagebutton;
     ImageButton androidImagebutton2;
     ImageButton androidImagebutton3;
     ImageButton androidImagebutton4;
     ImageButton androidImagebutton5;
     ImageButton androidImagebutton6;
     ImageButton androidImagebutton7;
     ImageButton androidImagebutton8;
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

        androidImagebutton2 = findViewById(R.id.imageButton2);
        androidImagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurant_list2Activity();
            }
        });

        androidImagebutton3 = findViewById(R.id.imageButton3);
        androidImagebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurant_list2Activity();
            }
        });

        androidImagebutton4 = findViewById(R.id.imageButton4);
        androidImagebutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurant_list2Activity();
            }
        });

        androidImagebutton5 = findViewById(R.id.imageButton5);
        androidImagebutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurant_list2Activity();
            }
        });

        androidImagebutton6 = findViewById(R.id.imageButton6);
        androidImagebutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurant_list2Activity();
            }
        });

        androidImagebutton7 = findViewById(R.id.imageButton7);
        androidImagebutton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurant_list2Activity();
            }
        });

        androidImagebutton8 = findViewById(R.id.imageButton8);
        androidImagebutton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurant_list2Activity();
            }
        });


    }

    public void Restaurantlist(){
        Intent intent = new Intent(this,RestaurantList.class);
        startActivity(intent);
    }

    public void restaurant_list2Activity(){
        Intent intent = new Intent(this,restaurant_list2Activity.class);
        startActivity(intent);
    }

    public void restaurant_list3Activity(){
        Intent intent = new Intent(this,restaurant_list3Activity.class);
        startActivity(intent);
    }

    public void restaurant_list4Activity(){
        Intent intent = new Intent(this,restaurant_list4Activity.class);
        startActivity(intent);
    }

    public void restaurant_list5Activity(){
        Intent intent = new Intent(this,restaurant_list5Activity.class);
        startActivity(intent);
    }

    public void restaurant_list6Activity(){
        Intent intent = new Intent(this,restaurant_list6Activity.class);
        startActivity(intent);
    }

    public void restaurant_list7Activity(){
        Intent intent = new Intent(this,restaurant_list7Activity.class);
        startActivity(intent);
    }

    public void restaurant_list8Activity(){
        Intent intent = new Intent(this,restaurant_list8Activity.class);
        startActivity(intent);
    }


}

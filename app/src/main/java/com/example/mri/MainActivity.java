package com.example.mri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mri.Asian.AsianActivity;
import com.example.mri.Chinese.ChineseActivity;
import com.example.mri.Dessert.DessertActivity;
import com.example.mri.Japanese.JapaneseActivity;
import com.example.mri.Junk.JunkActivity;
import com.example.mri.Korean.KoreanActivity;
import com.example.mri.Others.OthersActivity;
import com.example.mri.Western.WesternActivity;

public class MainActivity extends AppCompatActivity {

     ImageButton BtnKorean;
     ImageButton BtnChinese;
     ImageButton BtnJapanese;
     ImageButton BtnWestern;
     ImageButton BtnAsian;
     ImageButton BtnJunk;
     ImageButton BtnDessert;
     ImageButton BtnOthers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnKorean = findViewById(R.id.KoreanBtn);
        BtnKorean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KoreanList();
            }
        });

        BtnChinese = findViewById(R.id.ChineseBtn);
        BtnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChineseList();
            }
        });

        BtnJapanese = findViewById(R.id.JapaneseBtn);
        BtnJapanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JapaneseList();
            }
        });

        BtnWestern = findViewById(R.id.WesternBtn);
        BtnWestern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WesternList();
            }
        });

        BtnAsian = findViewById(R.id.AsianBtn);
        BtnAsian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsianList();
            }
        });

        BtnJunk = findViewById(R.id.JunkBtn);
        BtnJunk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JunkList();
            }
        });

        BtnDessert = findViewById(R.id.DessertBtn);
        BtnDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DessertList();
            }
        });

        BtnOthers = findViewById(R.id.OthersBtn);
        BtnOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OthersList();
            }
        });


    }

    public void KoreanList(){
        Intent intent = new Intent(this, KoreanActivity.class);
        startActivity(intent);
    }

    public void ChineseList(){
        Intent intent = new Intent(this, ChineseActivity.class);
        startActivity(intent);
    }

    public void JapaneseList(){
        Intent intent = new Intent(this, JapaneseActivity.class);
        startActivity(intent);
    }

    public void WesternList(){
        Intent intent = new Intent(this, WesternActivity.class);
        startActivity(intent);
    }

    public void AsianList(){
        Intent intent = new Intent(this, AsianActivity.class);
        startActivity(intent);
    }

    public void JunkList(){
        Intent intent = new Intent(this, JunkActivity.class);
        startActivity(intent);
    }

    public void DessertList(){
        Intent intent = new Intent(this, DessertActivity.class);
        startActivity(intent);
    }

    public void OthersList(){
        Intent intent = new Intent(this, OthersActivity.class);
        startActivity(intent);
    }


}

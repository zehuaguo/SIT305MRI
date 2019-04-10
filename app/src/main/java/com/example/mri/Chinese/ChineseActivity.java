package com.example.mri.Chinese;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mri.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChineseActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference dataRef;
    RecyclerView recyclerView;
    ArrayList<Chinese> list;
    ChinAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_list);

        list = new ArrayList<Chinese>();
        recyclerView = (RecyclerView) findViewById(R.id.ResRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataRef = FirebaseDatabase.getInstance().getReference().child("chinese");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Chinese c = ds.getValue(Chinese.class);
                    list.add(c);
                }
                adapter = new ChinAdapter(ChineseActivity.this,list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ChineseActivity.this, "Data error", Toast.LENGTH_SHORT).show();
            }
        });
    }



}

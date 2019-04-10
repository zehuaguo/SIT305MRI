package com.example.mri.Asian;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mri.Asian.AsianAdapter;
import com.example.mri.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AsianActivity extends AppCompatActivity {

    DatabaseReference dataRef;
    AsianAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Asian> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asian_list);

        list = new ArrayList<Asian>();
        recyclerView = (RecyclerView) findViewById(R.id.ResRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataRef = FirebaseDatabase.getInstance().getReference().child("asian");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Asian a = ds.getValue(Asian.class);
                    list.add(a);
                }
                adapter = new AsianAdapter(AsianActivity.this,list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AsianActivity.this, "Data error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

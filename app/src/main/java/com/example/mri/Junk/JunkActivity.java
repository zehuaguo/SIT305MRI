package com.example.mri.Junk;

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

public class JunkActivity extends AppCompatActivity {

    JunkAdapter adapter;
    ArrayList<Junk> list;
    DatabaseReference dataRef;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junk_list);

        list = new ArrayList<Junk>();
        recyclerView = (RecyclerView) findViewById(R.id.ResRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataRef = FirebaseDatabase.getInstance().getReference().child("junk");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Junk j = ds.getValue(Junk.class);
                    list.add(j);
                }
                adapter = new JunkAdapter(JunkActivity.this,list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(JunkActivity.this, "Data error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

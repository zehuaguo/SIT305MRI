package com.example.mri.Korean;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mri.Asian.Asian;
import com.example.mri.Chinese.Chinese;
import com.example.mri.Dessert.Dessert;
import com.example.mri.Japanese.Japanese;
import com.example.mri.Junk.Junk;
import com.example.mri.Others.Others;
import com.example.mri.R;
import com.example.mri.Western.Western;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KoreanActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference dataRef;

    ArrayList<Korean> list;
    /*
    ArrayList<Chinese> chineseList;
    ArrayList<Japanese> japaneseList;
    ArrayList<Western> westernList;
    ArrayList<Asian> asianList;
    ArrayList<Junk> junkList;
    ArrayList<Dessert> dessertList;
    ArrayList<Others> othersList;
    */
    Adapter adapter;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean_list);

        list = new ArrayList<Korean>();
        recyclerView = (RecyclerView) findViewById(R.id.ResRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataRef = FirebaseDatabase.getInstance().getReference().child("korean");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Korean k = ds.getValue(Korean.class);
                    list.add(k);
                }
                adapter = new Adapter(KoreanActivity.this,list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(KoreanActivity.this, "Data error", Toast.LENGTH_SHORT).show();
            }
        });

/*
        korean = new Korean();
        final ListView listView = (ListView)findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        dataRef = database.getReference("Korean");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.customlayout,R.id.textView_name, list);
        dataRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    korean = ds.getValue(Korean.class);
                    list.add(korean.getName().toString() + " " + korean.getLocation().toString());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }
*/
    }
}

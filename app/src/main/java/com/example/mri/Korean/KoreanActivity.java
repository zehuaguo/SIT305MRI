package com.example.mri.Korean;

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

public class KoreanActivity extends AppCompatActivity {

/*
    int[] IMAGE = {R.drawable.korean, R.drawable.chinese};

    String[] NAMES = {"Korean", "Chinese"};

    String[] LOCATION = {"City", "BoxHill"};

    ImageButton Imagebutton9;
*/
    FirebaseDatabase database;
    DatabaseReference dataRef;
    ArrayList<Korean> list;
    Adapter adapter;
    //Korean korean;
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


        Imagebutton9 = (ImageButton) findViewById(R.id.imageButton9);
        Imagebutton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_restaurant_list();
            }
        });

    }

    public void openActivity_restaurant_list() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    class CustomAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return IMAGE.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textView_name = (TextView)view.findViewById(R.id.textView_name);
            TextView textView_location = (TextView)view.findViewById(R.id.textView_location);

            imageView.setImageResource(IMAGE[i]);
            textView_name.setText(NAMES[i]);
            textView_location.setText(LOCATION[i]);

            return view;
        }*/
    }
}

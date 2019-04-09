package com.example.mri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class restaurant_list3Activity extends AppCompatActivity {

    int[] IMAGE = {R.drawable.korean, R.drawable.chinese};

    String[] NAMES = {"Korean", "Chinese"};

    String[] LOCATION = {"City", "BoxHill"};

    ImageButton Imagebutton11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list3);

        ListView listView = (ListView)findViewById(R.id.listView3);

        restaurant_list3Activity.CustomAdapter customAdapter = new restaurant_list3Activity.CustomAdapter();
        listView.setAdapter(customAdapter);

        Imagebutton11 = (ImageButton) findViewById(R.id.imageButton11);
        Imagebutton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_restaurant_list3();
            }
        });
    }

    public void openActivity_restaurant_list3() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    class CustomAdapter extends BaseAdapter {


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
        }
    }
}

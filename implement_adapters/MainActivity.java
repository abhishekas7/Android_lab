package com.example.implement_adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView;
        String[] a={ "A","B","C"};
        listView=findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,a));
        listView.setOnItemClickListener((parent,view,position,id)->{
            Toast.makeText(this, "selected Item is "+ a[position ], Toast.LENGTH_SHORT).show();

        });


    }
}
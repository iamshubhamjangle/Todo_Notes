package com.octa.todo_notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    TextView no_data;

    MyDatabaseHelper myDB;
    ArrayList<String> todo_id, todo_title, todo_desc, todo_day;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        todo_id = new ArrayList<>();
        todo_title = new ArrayList<>();
        todo_desc = new ArrayList<>();
        todo_day = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, todo_id, todo_title, todo_desc, todo_day);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            //empty_imageview.setVisibility(View.VISIBLE);
            //no_data.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No data..", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                todo_id.add(cursor.getString(0));
                todo_title.add(cursor.getString(1));
                todo_desc.add(cursor.getString(2));
                todo_day.add(cursor.getString(3));
            }
            //empty_imageview.setVisibility(View.GONE);
            //no_data.setVisibility(View.GONE);
        }
    }
}
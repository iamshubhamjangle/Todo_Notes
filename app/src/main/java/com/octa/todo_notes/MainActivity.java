package com.octa.todo_notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper myDB;
    ArrayList<String> todo_id, todo_title, todo_desc, todo_day;
    CustomAdapter customAdapter;
    public static MainActivity fa;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //used to finish this activity when moving to other activity,
        //this help when user does not add any task and press back the activity remains in same state in background
        fa = this;

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

    @Override
    protected void onResume() {
        super.onResume();
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data..", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                todo_id.add(cursor.getString(0));
                todo_title.add(cursor.getString(1));
                todo_desc.add(cursor.getString(2));
                todo_day.add(cursor.getString(3));
            }
        }
    }

    @Override
    public void onBackPressed() {
        //Exit only when back button is pressed twice
        if (doubleBackToExitPressedOnce) {   //if true exit the activity
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
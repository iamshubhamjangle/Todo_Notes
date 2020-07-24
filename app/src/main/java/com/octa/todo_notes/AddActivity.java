package com.octa.todo_notes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText title_input, desc_input;
    TextView tv_date_picker;
    ImageView date_picker;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        desc_input = findViewById(R.id.author_input);
        tv_date_picker = findViewById(R.id.tv_date_picker);
        add_button = findViewById(R.id.add_button);
        date_picker = findViewById(R.id.imageViewDatePicker);
        final Calendar myCalendar = Calendar.getInstance();

        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddButtonClick();
            }
        });
    }

    private void onAddButtonClick() {
        MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
        myDB.addtodo(title_input.getText().toString().trim(),
                desc_input.getText().toString().trim(),
                tv_date_picker.getText().toString().trim());
        MainActivity.fa.finish();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
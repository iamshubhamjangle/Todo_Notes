package com.octa.todo_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;

import static java.lang.String.format;

public class AddActivity extends AppCompatActivity {

    private EditText title_input, desc_input;
    private TextView tv_date_picker;
    private ImageView date_picker;
    private Button add_button;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        desc_input = findViewById(R.id.author_input);
        tv_date_picker = findViewById(R.id.tv_date_picker);
        add_button = findViewById(R.id.add_button);
        date_picker = findViewById(R.id.imageViewDatePicker);

        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDatePickerClick();
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddButtonClick();
            }
        });

    }


    private void onDatePickerClick() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv_date_picker.setText(format("%d-%d-%d", dayOfMonth, monthOfYear + 1, year));
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
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
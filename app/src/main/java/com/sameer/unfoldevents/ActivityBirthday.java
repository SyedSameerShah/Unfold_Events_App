package com.sameer.unfoldevents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ActivityBirthday extends AppCompatActivity {

    TextInputEditText selectEventDate, noofguests, venue, foodtype, budget;
    DBHelper DB;
    Button confirmBooking;

    int year;
    int month;
    int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        selectEventDate = findViewById(R.id.selectEventDate);

        noofguests = findViewById(R.id.noofguests);
        venue = findViewById(R.id.venue);
        foodtype = findViewById(R.id.foodtype);
        budget = findViewById(R.id.budget);
        DB = new DBHelper(this);
        confirmBooking = findViewById(R.id.confirmBooking);
        Bundle extras = getIntent().getExtras();

        Calendar calendar = Calendar.getInstance();

        selectEventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityBirthday.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        selectEventDate.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(selectEventDate.getText().toString()) || TextUtils.isEmpty(noofguests.getText().toString()) || TextUtils.isEmpty(venue.getText().toString()) || TextUtils.isEmpty(foodtype.getText().toString()) || TextUtils.isEmpty(budget.getText().toString()))
                    Toast.makeText(ActivityBirthday.this, "All Fields Are Required!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert = DB.addEvent(extras.getString("email"), "birthday", selectEventDate.getText().toString(), Integer.parseInt(noofguests.getText().toString()), venue.getText().toString(), foodtype.getText().toString(), Integer.parseInt(budget.getText().toString()));
                    if(insert == true) {
                        Toast.makeText(ActivityBirthday.this, "Birthday Event Added Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                        intent.putExtra("email", extras.getString("email"));
                        startActivity(intent);
                    } else {
                        Toast.makeText(ActivityBirthday.this, "Failed To Add An Event!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}

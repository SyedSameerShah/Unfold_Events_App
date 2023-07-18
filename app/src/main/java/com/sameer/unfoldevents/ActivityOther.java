package com.sameer.unfoldevents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class ActivityOther extends AppCompatActivity {

    TextInputEditText selectEventDate3, noofguests3, venue3, foodtype3, budget3, eventName;
    DBHelper DB;
    Button confirmBooking3;

    int year;
    int month;
    int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        eventName = findViewById(R.id.eventName);
        selectEventDate3 = findViewById(R.id.selectEventDate3);
        noofguests3 = findViewById(R.id.noofguests3);
        venue3 = findViewById(R.id.venue3);
        foodtype3 = findViewById(R.id.foodtype3);
        budget3 = findViewById(R.id.budget3);
        DB = new DBHelper(this);
        confirmBooking3 = findViewById(R.id.confirmBooking3);
        Bundle extras = getIntent().getExtras();

        Calendar calendar = Calendar.getInstance();

        selectEventDate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityOther.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        selectEventDate3.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        confirmBooking3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(eventName.getText().toString()) || TextUtils.isEmpty(selectEventDate3.getText().toString()) || TextUtils.isEmpty(noofguests3.getText().toString()) || TextUtils.isEmpty(venue3.getText().toString()) || TextUtils.isEmpty(foodtype3.getText().toString()) || TextUtils.isEmpty(budget3.getText().toString()))
                    Toast.makeText(ActivityOther.this, "All Fields Are Required!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert = DB.addEvent(extras.getString("email"), "OTHER:  " + eventName.getText().toString(), selectEventDate3.getText().toString(), Integer.parseInt(noofguests3.getText().toString()), venue3.getText().toString(), foodtype3.getText().toString(), Integer.parseInt(budget3.getText().toString()));
                    if(insert == true) {
                        Toast.makeText(ActivityOther.this, "Other Event Added Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                        intent.putExtra("email", extras.getString("email"));
                        startActivity(intent);
                    } else {
                        Toast.makeText(ActivityOther.this, "Failed To Add An Event!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
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

public class ActivityWedding extends AppCompatActivity {

    TextInputEditText selectEventDate1, noofguests1, venue1, foodtype1, budget1;
    DBHelper DB;
    Button confirmBooking1;

    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding);

        selectEventDate1 = findViewById(R.id.selectEventDate1);
        noofguests1 = findViewById(R.id.noofguests1);
        venue1 = findViewById(R.id.venue1);
        foodtype1 = findViewById(R.id.foodtype1);
        budget1 = findViewById(R.id.budget1);
        DB = new DBHelper(this);
        confirmBooking1 = findViewById(R.id.confirmBooking1);
        Bundle extras = getIntent().getExtras();

        Calendar calendar = Calendar.getInstance();

        selectEventDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityWedding.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        selectEventDate1.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        confirmBooking1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(selectEventDate1.getText().toString()) || TextUtils.isEmpty(noofguests1.getText().toString()) || TextUtils.isEmpty(venue1.getText().toString()) || TextUtils.isEmpty(foodtype1.getText().toString()) || TextUtils.isEmpty(budget1.getText().toString()))
                    Toast.makeText(ActivityWedding.this, "All Fields Are Required!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert = DB.addEvent(extras.getString("email"), "wedding", selectEventDate1.getText().toString(), Integer.parseInt(noofguests1.getText().toString()), venue1.getText().toString(), foodtype1.getText().toString(), Integer.parseInt(budget1.getText().toString()));
                    if(insert == true) {
                        Toast.makeText(ActivityWedding.this, "Wedding Event Added Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                        intent.putExtra("email", extras.getString("email"));
                        startActivity(intent);
                    } else {
                        Toast.makeText(ActivityWedding.this, "Failed To Add An Event!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
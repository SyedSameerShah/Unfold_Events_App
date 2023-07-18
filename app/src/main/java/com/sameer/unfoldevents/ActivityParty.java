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

public class ActivityParty extends AppCompatActivity {

    TextInputEditText selectEventDate2, noofguests2, venue2, foodtype2, budget2;
    DBHelper DB;
    Button confirmBooking2;

    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        selectEventDate2 = findViewById(R.id.selectEventDate2);
        noofguests2 = findViewById(R.id.noofguests2);
        venue2 = findViewById(R.id.venue2);
        foodtype2 = findViewById(R.id.foodtype2);
        budget2 = findViewById(R.id.budget2);
        DB = new DBHelper(this);
        confirmBooking2 = findViewById(R.id.confirmBooking2);
        Bundle extras = getIntent().getExtras();

        Calendar calendar = Calendar.getInstance();

        selectEventDate2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityParty.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        selectEventDate2.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        confirmBooking2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(selectEventDate2.getText().toString()) || TextUtils.isEmpty(noofguests2.getText().toString()) || TextUtils.isEmpty(venue2.getText().toString()) || TextUtils.isEmpty(foodtype2.getText().toString()) || TextUtils.isEmpty(budget2.getText().toString()))
                    Toast.makeText(ActivityParty.this, "All Fields Are Required!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert = DB.addEvent(extras.getString("email"), "party", selectEventDate2.getText().toString(), Integer.parseInt(noofguests2.getText().toString()), venue2.getText().toString(), foodtype2.getText().toString(), Integer.parseInt(budget2.getText().toString()));
                    if(insert == true) {
                        Toast.makeText(ActivityParty.this, "Party Event Added Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                        intent.putExtra("email", extras.getString("email"));
                        startActivity(intent);
                    } else {
                        Toast.makeText(ActivityParty.this, "Failed To Add An Event!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
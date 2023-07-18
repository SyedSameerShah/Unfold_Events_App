package com.sameer.unfoldevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class EventCoordinationActivity extends AppCompatActivity {

    Button myBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_coordination);

        myBookings = findViewById(R.id.myBookings);
        Bundle extras = getIntent().getExtras();


        myBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyBookingActivity.class);
                intent.putExtra("email", extras.getString("email"));
                startActivity(intent);
            }
        });
    }
}
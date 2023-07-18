package com.sameer.unfoldevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class SecondActivity extends AppCompatActivity {

    Button signOutBtn, bookEvent, myBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();

        signOutBtn = findViewById(R.id.signout);
        bookEvent = findViewById(R.id.bookEvent);
        myBookings = findViewById(R.id.myBookings);

        bookEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.putExtra("email", extras.getString("email"));
                startActivity(intent);
            }
        });

        myBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyBookingActivity.class);
                intent.putExtra("email", extras.getString("email"));
                startActivity(intent);
            }
        });


        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}

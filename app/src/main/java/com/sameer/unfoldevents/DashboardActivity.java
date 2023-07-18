package com.sameer.unfoldevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;


public class DashboardActivity extends AppCompatActivity {

    MaterialButton birthdayBtn, weddingBtn, partyBtn, otherBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        birthdayBtn = findViewById(R.id.birthdayBtn);
        weddingBtn = findViewById(R.id.weddingBtn);
        partyBtn = findViewById(R.id.partyBtn);
        otherBtn = findViewById(R.id.otherBtn);

        Bundle extras = getIntent().getExtras();

        birthdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ActivityBirthday.class);
                intent.putExtra("email", extras.getString("email"));
                startActivity(intent);
            }
        });

        weddingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ActivityWedding.class);
                intent.putExtra("email", extras.getString("email"));
                startActivity(intent);
            }
        });

        partyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ActivityParty.class);
                intent.putExtra("email", extras.getString("email"));
                startActivity(intent);
            }
        });

        otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ActivityOther.class);
                intent.putExtra("email", extras.getString("email"));
                startActivity(intent);
            }
        });



    }
}
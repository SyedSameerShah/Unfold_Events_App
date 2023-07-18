package com.sameer.unfoldevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class PaymentCompleteActivity extends AppCompatActivity {

    Button nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_complete);

        nextPage = findViewById(R.id.nextPage);
        Bundle extras = getIntent().getExtras();


        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventCoordinationActivity.class);
                intent.putExtra("email", extras.getString("email"));
                startActivity(intent);
            }
        });

    }
}
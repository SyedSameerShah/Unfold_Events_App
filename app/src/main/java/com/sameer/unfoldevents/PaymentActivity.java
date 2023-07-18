package com.sameer.unfoldevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    private EditText cardNumberEditText, expiryDateEditText, cvvEditText;
    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentactivity);

        payButton = findViewById(R.id.payButton);
        Bundle extras = getIntent().getExtras();


        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentCompleteActivity.class);
                intent.putExtra("email", extras.getString("email"));
                startActivity(intent);
            }
        });
    }
}

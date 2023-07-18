package com.sameer.unfoldevents;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button signupbtn, loginbtn;
    TextView email, password;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        signupbtn = findViewById(R.id.signupbtn);
        DB = new DBHelper(this);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "All Fields Are Required!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserPass = DB.loginUser(mail, pass);
                    if (checkUserPass == true) {
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                        intent.putExtra("email", mail);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect Username/Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
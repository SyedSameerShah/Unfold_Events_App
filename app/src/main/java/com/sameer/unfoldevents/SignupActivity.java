package com.sameer.unfoldevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends Activity {
    Button btnSignUp;
    LinearLayout alreadyHaveAnAccount;
    TextInputEditText fullname, email, monumber, signupPassword, confirmPassword1;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignUp = findViewById(R.id.btnSignUp);
        alreadyHaveAnAccount = findViewById(R.id.alreadyHaveAnAccount);

        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        monumber = findViewById(R.id.monumber);
        signupPassword = findViewById(R.id.signupPassword);
        confirmPassword1 = findViewById(R.id.confirmPassword1);
        DB = new DBHelper(this);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = fullname.getText().toString();
                String emailAddress = email.getText().toString();
                String moNumber = monumber.getText().toString();
                String passwordFirst = signupPassword.getText().toString();
                String passwordSecond = confirmPassword1.getText().toString();

                if(TextUtils.isEmpty(fullName) || TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(moNumber) || TextUtils.isEmpty(passwordFirst) || TextUtils.isEmpty(passwordSecond))
                    Toast.makeText(SignupActivity.this, "All Fields Are Required!", Toast.LENGTH_SHORT).show();
                else{
                    if(passwordFirst.equals(passwordSecond)){
                        Boolean checkUser = DB.checkUser(emailAddress);
                        if(checkUser == false) {
                            Boolean insert = DB.registerUser(emailAddress, passwordFirst, fullName, moNumber);
                            if(insert == true) {
                                Toast.makeText(SignupActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                                intent.putExtra("email", emailAddress);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "User Already Exists!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Password Does Not Match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }}
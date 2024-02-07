package com.example.foodplanner.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.foodplanner.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

    }
}
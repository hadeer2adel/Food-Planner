package com.example.foodplanner.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.R;
import com.example.foodplanner.HelperClasses.PreferenceManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        PreferenceManager preferenceManager = new PreferenceManager(this);
        if(currentUser != null){
            if(preferenceManager.isLoggedIn()) {
                UserDTO.setUserFromPreference(preferenceManager);
            }
            Intent intent = new Intent(StartActivity.this, SplashScreenActivity.class);
            startActivity(intent);
            finish();
        }
        else if(preferenceManager.isLoggedIn()){
            UserDTO.setUserFromPreference(preferenceManager);
            Intent intent = new Intent(StartActivity.this, SplashScreenActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide();
    }
}
package com.example.foodplanner.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;

public class SplashScreenActivity extends AppCompatActivity {
    private TextView appName1, appName2, appName3;
    private LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        appName3 = findViewById(R.id.appName3);
        appName2 = findViewById(R.id.appName2);
        appName1 = findViewById(R.id.appName1);
        lottie = findViewById(R.id.lottie);

        appName3.animate().translationY(-1400).setDuration(2700).setStartDelay(100);
        appName2.animate().translationY(-1400).setDuration(2700).setStartDelay(1000);
        appName1.animate().translationY(-1400).setDuration(2700).setStartDelay(1500);
        lottie.animate().translationY(2000).setDuration(2000).setStartDelay(5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 6500);
    }
}
package com.muti.ereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    String nik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initialize();
        new Handler().postDelayed(() -> {
            if(nik.equals("default_value1")){
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
            }
            else{
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            }
            finish();
        }, 1500L);
    }

    void initialize(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        nik = sharedPreferences.getString("nik", "default_value1");
    }
}
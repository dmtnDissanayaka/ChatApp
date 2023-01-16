package com.example.chatme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Start1Activity extends AppCompatActivity {

    private  static  int SPLASH_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Start1Activity.this, StartActivity.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME_OUT);
    }
}
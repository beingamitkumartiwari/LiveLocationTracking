package com.travel.livelocationtracking.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.travel.livelocationtracking.R;
import com.travel.livelocationtracking.db.SessionPrefs;
import com.travel.livelocationtracking.services.TrackerActivity;

public class WelcomeActivity extends AppCompatActivity {

    SessionPrefs sessionPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        sessionPrefs = new SessionPrefs(this);
        if (sessionPrefs.isLogin()){
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(WelcomeActivity.this,
                        TrackerActivity.class);
                startActivity(intent);
                finish();
            }, 1500);
        } else {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(WelcomeActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }, 1500);
        }
    }
}

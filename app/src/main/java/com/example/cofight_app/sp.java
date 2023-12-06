package com.example.cofight_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class sp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        getSupportActionBar().hide();
    }
}
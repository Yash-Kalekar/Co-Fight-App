package com.example.cofight_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Covid3_Activity extends AppCompatActivity {

    private Button yes;
    private Button no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_covid3);

        yes = findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTested();
            }
        });

        no = findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCovid4();
            }
        });
    }

    public void openTested(){
        Intent intent = new Intent(this, Tested_Activity.class);
        startActivity(intent);
    };

    public void openCovid4(){
        Intent intent = new Intent(this, com.example.cofight_app.Covid4_Activity.class);
        startActivity(intent);
    };
}
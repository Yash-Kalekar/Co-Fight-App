package com.example.cofight_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Covid5_Activity extends AppCompatActivity {


    private Button yes;
    private Button no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_covid5);

        yes = findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelfIsolate();
            }
        });

        no = findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStayHealthy();
            }
        });
    }

    public void openSelfIsolate(){
        Intent intent = new Intent(this, com.example.cofight_app.SelfIsolate_Activity.class);
        startActivity(intent);
    };

    public void openStayHealthy(){
        Intent intent = new Intent(this, com.example.cofight_app.StayHealthy_Activity.class);
        startActivity(intent);
    };
}
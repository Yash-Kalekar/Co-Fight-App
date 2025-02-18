package com.example.cofight_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Covid_Activity extends AppCompatActivity {

    private Button proceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_covid);

        proceed = (Button) findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCovid1();
            }
        });
    }

    public void openCovid1(){
        Intent intent = new Intent(this, com.example.cofight_app.Covid1_Activity.class);
        startActivity(intent);
    };
}

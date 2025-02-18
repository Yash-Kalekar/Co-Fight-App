package com.example.cofight_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Terminated_Activity extends AppCompatActivity {

    private Button again,homereturn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminated);

        homereturn1=findViewById(R.id.homereturn1);
        homereturn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Terminated_Activity.this,MainActivity.class);
                startActivity(i);
            }
        });

        again = (Button)  findViewById(R.id.again);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCovid();
            }
        });
    }

    public void openCovid(){
        Intent intent = new Intent(this, com.example.cofight_app.Covid_Activity.class);
        startActivity(intent);
    };

}
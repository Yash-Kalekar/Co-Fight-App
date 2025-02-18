package com.example.cofight_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class  Covid2_Activity extends AppCompatActivity {

    private Button yes;
    private Button no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_covid2);

        yes = findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTerminated();
            }
        });

        no = findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCovid3();
            }
        });
    }

    public void openTerminated(){
        Intent intent = new Intent(this, Terminated_Activity.class);
        startActivity(intent);
    };

    public void openCovid3(){
        Intent intent = new Intent(this, Covid3_Activity.class);
        startActivity(intent);
    };

}

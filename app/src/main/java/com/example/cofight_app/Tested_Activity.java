package com.example.cofight_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Tested_Activity extends AppCompatActivity {

    private Button tested,homereturn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tested);

        homereturn1=findViewById(R.id.homereturn1);
        homereturn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Tested_Activity.this,MainActivity.class);
                startActivity(i);
            }
        });


        tested = findViewById(R.id.tested);
        tested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink();
            }
        });
    }

    public void openLink(){
        Uri uri = Uri.parse("https://www.acko.com/health-insurance/coronavirus-testing-india-govt-covid-19-labs/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    };


}
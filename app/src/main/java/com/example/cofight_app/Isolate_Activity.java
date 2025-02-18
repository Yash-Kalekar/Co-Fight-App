package com.example.cofight_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Isolate_Activity extends AppCompatActivity {

    private Button isolate,homereturn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_isolate);

        homereturn1=findViewById(R.id.homereturn1);
        homereturn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Isolate_Activity.this,MainActivity.class);
                startActivity(i);
            }
        });


        isolate = findViewById(R.id.isolate);
        isolate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink();
            }
        });
    }

    public void openLink(){
        Uri uri = Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/symptoms-testing/testing.html");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    };
}
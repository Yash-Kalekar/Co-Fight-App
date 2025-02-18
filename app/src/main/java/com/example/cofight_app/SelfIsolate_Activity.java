package com.example.cofight_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelfIsolate_Activity extends AppCompatActivity {

    private Button isolate,homereturn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.isolate_self_solate);

        homereturn1=findViewById(R.id.homereturn1);
        homereturn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelfIsolate_Activity.this,MainActivity.class);
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
        Uri uri = Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/your-health/quarantine-isolation.html");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    };
}
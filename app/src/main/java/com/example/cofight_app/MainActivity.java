package com.example.cofight_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.cofight_app.view.Vaccine;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;
    private ImageView more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        more = findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,info.class);
                startActivity(i);
            }
        });

        imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_tracker();
            }
        });

        imageButton2 = (ImageButton)findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_assesment();
            }
        });

        imageButton3 = (ImageButton)findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_sp();
            }
        });
        imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_loginsignup();
            }
        });

    }

    public void open_tracker()
    {
        Intent intent = new Intent(MainActivity.this, tracker.class);
        startActivity(intent);
    };

    public void open_assesment()
    {
        Intent intent = new Intent(MainActivity.this, Covid_Activity.class);
        startActivity(intent);
    };

    public void open_sp()
    {
        Intent intent = new Intent(MainActivity.this, sp.class);
        startActivity(intent);
    };

    public void open_loginsignup()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(LoginSignup.PREFS_NAME , MODE_PRIVATE);
        boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn" ,false);

        Intent intent;
        if(hasLoggedIn)
        {
            intent = new Intent(MainActivity.this, Vaccine.class);
        }else
        {
            intent = new Intent(MainActivity.this, LoginSignup.class);
        }
        startActivity(intent);
        finish();
    };

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this );
        builder.setTitle("EXIT !!");
        builder.setMessage("Are you sure want to Exit ??").setCancelable(false).
                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.super.onBackPressed();
                        finishAffinity();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
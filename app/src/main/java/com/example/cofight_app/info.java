package com.example.cofight_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import static com.example.cofight_app.LoginSignup.PREFS_NAME;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class info extends AppCompatActivity {

    LinearLayout l1,l2,l3,l4,l5,l6;
    TextView textView,user;
    static String USER_NAME = "file";
    static String KEY_NAME = "name";
    SharedPreferences sd ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_info);
        l3=findViewById(R.id.about);
        l4=findViewById(R.id.contact);
        l1=findViewById(R.id.rateus);
        l6 = findViewById(R.id.out);
        user = findViewById(R.id.user);

        sd = getSharedPreferences(USER_NAME , MODE_PRIVATE);
        String name = sd.getString(KEY_NAME , null);

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(info.this,about.class);
                startActivity(i);
            }
        });
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(info.this);
                bottomSheetDialog.setContentView(R.layout.rate);
                ImageView imageView = bottomSheetDialog.findViewById(R.id.close);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.hide();
                    }
                });
                RatingBar r1 = bottomSheetDialog.findViewById(R.id.rateBar);
                Button b1 = bottomSheetDialog.findViewById(R.id.submit);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(r1.getRating()>0){
                            bottomSheetDialog.hide();
                            Toast.makeText(info.this, "Thank You For Rating Us!!", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            bottomSheetDialog.hide();
                            Toast.makeText(info.this, "Please Select Number Of Stars", Toast.LENGTH_SHORT).show();

                        }
                    }

                });
                bottomSheetDialog.show();
            }
        });
      l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "cofight.app2022@gmail.com", null));
                startActivity(i);
            }
        });
        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(info.this);
                builder.setTitle("Signout!!");
                builder.setMessage("Are you sure want to Signout??").setCancelable(false).
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                info.super.onBackPressed();
                                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME , MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.apply();

                                startActivity(new Intent(info.this , MainActivity.class));
                                finish();
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
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(info.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
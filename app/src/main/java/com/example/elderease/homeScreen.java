package com.example.elderease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class homeScreen extends AppCompatActivity {

    CardView medRem;
    CardView magnify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_screen);

        magnify = findViewById(R.id.magnify);

        magnify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeScreen.this,magnifier.class);
                startActivity(intent);
            }
        });

        medRem = findViewById(R.id.medRem);

        medRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeScreen.this,medicineReminder.class);
                startActivity(intent);
            }

        });





    }
}
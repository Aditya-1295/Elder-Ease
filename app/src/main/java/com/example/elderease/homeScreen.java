package com.example.elderease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.elderease.medicine.MedicineActivity;

public class homeScreen extends AppCompatActivity {

    CardView medRem;
    CardView magnify;
    Button sos;

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
                Intent intent = new Intent(homeScreen.this, MedicineActivity.class);
                startActivity(intent);
            }

        });

        sos = findViewById(R.id.sos);

        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeScreen.this, sos.class);
                startActivity(intent);
            }
        });






    }
}
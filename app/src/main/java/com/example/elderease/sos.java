package com.example.elderease;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.*;


import androidx.appcompat.app.AppCompatActivity;


public class sos extends AppCompatActivity {

    EditText phoneOne , phoneTwo, message;
    Button save;
    private DBHelper db;
    int id_To_Update = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        phoneOne = findViewById(R.id.emergencyNo1);
        phoneTwo = findViewById(R.id.emergencyNo2);
        message = findViewById(R.id.message);
        save = findViewById(R.id.save);
        db = new DBHelper(this);
        Cursor cursor = db.getData();
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            phoneOne.setText(cursor.getString(1));
            phoneTwo.setText(cursor.getString(2));
            message.setText(cursor.getString(3));


        }




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String numberOne = phoneOne.getText().toString();
                String numberTwo = phoneTwo.getText().toString();
                String mess  = message.getText().toString();

                if (cursor.getCount() == 0){
                    boolean insert = db.insertContact(numberOne,numberTwo,mess);

                    if (insert){
                        Toast.makeText(getApplicationContext(),"Added Emergency Contact",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    boolean update = db.updateContact(numberOne,numberTwo,mess,1);
                    if (update){
                        Toast.makeText(getApplicationContext(),"Updated Emergency Contact",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),"You must restart the app to apply the following changes",Toast.LENGTH_SHORT).show();
                    }



                }

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


            }
        });

    }

}
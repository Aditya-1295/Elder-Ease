package com.example.elderease;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class phoneCall extends AppCompatActivity {

    EditText etNumber,phone1,phone2,phone3;
    Button btCall,save,call1,call2,call3;
    private DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);

        etNumber =findViewById(R.id.et_number);
        btCall =findViewById(R.id.bt_call);
        phone1 = findViewById(R.id.quickPhone1);
        phone2 = findViewById(R.id.quickPhone2);
        phone3 = findViewById(R.id.quickPhone3);
        save = findViewById(R.id.phoneSave);
        call1 = findViewById(R.id.callb1);
        call2 = findViewById(R.id.callb2);
        call3 = findViewById(R.id.callb3);


        db = new DBHelper(this);
        Cursor cursor = db.getPhoneData();
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            phone1.setText(cursor.getString(1));
            phone2.setText(cursor.getString(2));
            phone3.setText(cursor.getString(3));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberOne = phone1.getText().toString();
                String numberTwo = phone2.getText().toString();
                String numberThree = phone3.getText().toString();

                if (cursor.getCount() ==0){
                    boolean insert = db.insertPhone(numberOne,numberTwo,numberThree);
                    if (insert){
                        Toast.makeText(getApplicationContext(), "Updated easy call contacts", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    boolean update = db.updatePhoneData(numberOne,numberTwo,numberThree,1);
                    if (update){
                        Toast.makeText(getApplicationContext(), "Easy call number updated", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "You must restart the app to apply changes", Toast.LENGTH_SHORT).show();
                    }
                }
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


            }
        });


        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etNumber.getText().toString();
                if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please Enter Number !",Toast.LENGTH_SHORT).show();

                }else {
                    String s= "tel:"+ phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }
            }
        });

        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phone1.getText().toString();
                if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please Enter Number !",Toast.LENGTH_SHORT).show();

                }else {
                    String s= "tel:"+ phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }
            }
        });

        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phone2.getText().toString();
                if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please Enter Number !",Toast.LENGTH_SHORT).show();

                }else {
                    String s= "tel:"+ phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }
            }
        });
        call3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phone3.getText().toString();
                if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please Enter Number !",Toast.LENGTH_SHORT).show();

                }else {
                    String s= "tel:"+ phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }
            }
        });
    }
}
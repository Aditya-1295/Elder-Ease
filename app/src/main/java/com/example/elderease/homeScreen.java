package com.example.elderease;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.elderease.medicine.MedicineActivity;
import android.Manifest;

public class homeScreen extends AppCompatActivity  {

    CardView medRem;
    CardView magnify;
    CardView music;
    CardView phone;
    CardView sos;
    CardView game;
    TextView S;
    private DBHelper db;
//    int color = Color.parseColor("#FF000");

    private int LOCATION_PERMISSION_CODE =1;
    private int MESSAGE_CODE = 2;
    private int PHONE_PERMISSION_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_screen);

// Asking for Fucking permission !!!!!!!

        db = new DBHelper(this);
        Cursor cursor = db.getData();


        phone = findViewById(R.id.quickPhone);
        magnify = findViewById(R.id.magnify);
        medRem = findViewById(R.id.medRem);
        sos = findViewById(R.id.SOS);
        music = findViewById(R.id.relaxingMusic);
        game  = findViewById(R.id.game);
        S = findViewById(R.id.textView);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(homeScreen.this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(homeScreen.this,phoneCall.class);
                    startActivity(intent);
                }else{
                    requestPhonePermission();
                }

            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeScreen.this,gameSelect.class);
                startActivity(intent);
            }
        });



        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeScreen.this,MusicPlayer2.class);
                startActivity(intent);
            }
        });

        magnify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeScreen.this,magnifier.class);
                startActivity(intent);
            }
        });



        medRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeScreen.this, MedicineActivity.class);
                startActivity(intent);
            }

        });



        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeScreen.this, sos.class);
                startActivity(intent);
            }
        });

        sos.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (ContextCompat.checkSelfPermission(homeScreen.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(homeScreen.this,Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    S.setText("Message Sent");
                    S.setTextColor(getResources().getColor(R.color.emergencyRed));
                    if (cursor.getCount() !=0){
                        cursor.moveToFirst();
                        SmsManager sms = SmsManager.getDefault();
                        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        Location location = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                        String Location = "https://www.google.co.in/maps/@"+location.getLatitude()+","+ location.getLongitude() + ",15z";

                        String preMessage = "This is an autogenerated message via Elder Ease app SOS help function : " + cursor.getString(3)+ "\n"
                                + "  " + Location;

                        sms.sendTextMessage((cursor.getString(1)),null,preMessage,null,null);
                        sms.sendTextMessage((cursor.getString(2)),null,preMessage,null,null);

                        Toast.makeText(getApplicationContext(),"Emergency message sent successfully",
                                Toast.LENGTH_LONG).show();
                        System.out.println("Messages sent");

                    }


                }else{
                    requestSOSPermission();


                }
                return true;
            }
        });






    }

    private void requestPhonePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE) && ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to make phone calls")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(homeScreen.this,new String[] {Manifest.permission.CALL_PHONE},PHONE_PERMISSION_CODE);
                        }
                    }).create().show();

        }else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE},PHONE_PERMISSION_CODE);
        }


    }

    private void requestSOSPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to send your location to your Emergency contacts")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(homeScreen.this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_CODE);
                        }
                    }).create().show();

        }else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.SEND_SMS},LOCATION_PERMISSION_CODE);
        }


    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_CODE || requestCode == MESSAGE_CODE){
            if (grantResults.length > 0 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
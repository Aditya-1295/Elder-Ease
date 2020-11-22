package com.example.elderease;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "emergency.db";
    public static final String TABLE_NAME = "contacts";
    public static final String PHONE = "phone";
    public static final String MESSAGE = "message";


    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts (id int ,phone1 text,phone2 text,message text)");
        db.execSQL("create table phone (id int , phone1 text ,phone2 text,phone3 text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE if exists phone");
        onCreate(db);
    }

    public boolean insertPhone(String phone1,String phone2,String phone3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",1);
        contentValues.put("phone1",phone1);
        contentValues.put("phone2",phone2);
        contentValues.put("phone3",phone3);
        long result = db.insert("phone",null,contentValues);
        if (result == -1)
            return false;
        else
            return true;


    }

    public Cursor getPhoneData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from phone where id = 1",null);
        return cursor;
    }

    public boolean updatePhoneData(String phone1,String phone2,String phone3,Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone1",phone1);
        contentValues.put("phone2",phone2);
        contentValues.put("phone3",phone3);
        db.update("phone",contentValues,"id=?",new String[]{String.valueOf(id)});
        return true;
    }

    public boolean insertContact (String phone1,String phone2, String message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",1);
        contentValues.put("phone1", phone1);
        contentValues.put("phone2", phone2);
        contentValues.put("message", message);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result ==-1)
            return false;
        else
            return true;

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from contacts where id = 1",null);
        return cursor;
    }

    public boolean updateContact(String phone1,String phone2,String message,Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone1",phone1);
        contentValues.put("phone2",phone2);
        contentValues.put("message",message);
        db.update("contacts",contentValues,"id=?",new String[]{String.valueOf(id)});
        return  true;
    }

}

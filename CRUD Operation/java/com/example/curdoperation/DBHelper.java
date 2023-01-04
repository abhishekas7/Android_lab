package com.example.curdoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userdetails (name TEXT primary key, contact TEXT , dob TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userdetails");

    }
    public Boolean insertuserdatas (String name,String contact, String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("name", name);
        contentvalues.put("contact", contact);
        contentvalues.put("dob", dob);
        long result = DB.insert("userdetails", null, contentvalues);
        if (result==-1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean updateuserdatas (String name,String contact, String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("contact", contact);
        contentvalues.put("dob", dob);
        Cursor cursor = DB.rawQuery("select * from userdetails where name= ?",new String[]{name});
        if(cursor.getCount()>0)
        {
        long result = DB.update("userdetails", contentvalues,"name=?", new String[]{name});
        if (result==-1) {
            return false;
        } else {
            return true;
        }
        }
        else
        {
            return false;
        }
    }

    public Boolean deleteuserdatas (String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        Cursor cursor = DB.rawQuery("select * from userdetails where name= ?",new String[]{name});
        if(cursor.getCount()>0)
        {
            long result = DB.delete("userdetails","name=?", new String[]{name});
            if (result==-1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false;
        }
    }


public Cursor getdata()
{
    SQLiteDatabase DB = this.getWritableDatabase();
    Cursor cursor = DB.rawQuery("select * from userdetails",null);
    return cursor;
}

}

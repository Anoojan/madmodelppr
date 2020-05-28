package com.example.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "anoo";
    public static final String TABLE_NAME = "UserInfo";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "USERNAME";
    public static final String COL_2 = "DATEOFBIRTH";
    public static final String COL_3 = "GENDER";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , userName TEXT , dateOfBirth TEXT , Gender TEXT  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addInfo(String userName , String dateOfBirth , String Gender  ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,userName);
        contentValues.put(COL_2,dateOfBirth);
        contentValues.put(COL_3,Gender);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor readAllInfor(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }
    public Integer deleteInfor(String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " USERNAME  = ? ", new String[]{ userName });


    }
    public boolean updateInfor(String userName , String dateOfBirth , String Gender  ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,userName);
        contentValues.put(COL_2,dateOfBirth);
        contentValues.put(COL_3,Gender);

        db.update(TABLE_NAME, contentValues, " USERNAME = ?" ,new String[]{ userName });
        return true;

    }
    public Cursor search(String value){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE USERNAME='"+value+"'",null);
        return res;


    }


    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return  data;
    }


}


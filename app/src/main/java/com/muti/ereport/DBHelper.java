package com.muti.ereport;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "pelaporan.db";

    public DBHelper(@Nullable Context context) {
        super(context, "pelaporan.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users(email VARCHAR, password VARCHAR, nama VARCHAR, nik VARCHAR, telepon VARCHAR, alamat VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE if exists users");
    }

    public Boolean insertDataUsers(String email, String password, String nama, String nik, String telepon, String alamat){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("nama", nama);
        contentValues.put("nik", nik);
        contentValues.put("telepon", telepon);
        contentValues.put("alamat", alamat);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean updateDataUsers(String email, String nama, String telepon, String alamat){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("telepon", telepon);
        contentValues.put("alamat", alamat);
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ?", new String[] {email});
        if(cursor.getCount()>0){
            long result = MyDB.update("users", contentValues, "email=?", new String[]{email});
            if(result==-1) return false;
            else
                return true;
        }
        else
            return false;
    }

    public Boolean checkUsernameUsers(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ?", new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkPasswordUsers(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ? and password = ?", new String[] {email, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void getDataUsers(Context context, String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ? and password = ?", new String[] {email, password});
        if(cursor.getCount()>0){
            String[] columnNames = cursor.getColumnNames();
            if (cursor.moveToFirst()) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                do {
                    for (String columnName : columnNames) {
                        int columnIndex = cursor.getColumnIndex(columnName);
                        String columnValue = cursor.getString(columnIndex);
                        editor.putString(columnName, columnValue);
                    }
                } while (cursor.moveToNext());
                editor.apply();
            }
        }
    }
}

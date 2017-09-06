package com.example.admin.savingdataapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.InputStream;

/**
 * Created by Admin on 9/6/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="myDatabase";
    private static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="contacts";
    public static final String CONTACT_NAME ="name";
    public static final String CONTACT_PHONE ="phone";
    private static final String TAG = "DatabaseTAG";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "("+
                CONTACT_NAME + " TEXT," +
                CONTACT_PHONE+ " TEXT PRIMARY KEY"+
                ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long saveNewContact(String name, String phone){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(CONTACT_NAME, name);
        contentValues.put(CONTACT_PHONE,phone);

        //sqLiteDatabase.insert(TABLE_NAME,null,contentValues);//null is if you are passing no values
        long rowNumber = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        Log.d(TAG, "saveNewContact: "+rowNumber);
return rowNumber;
    }

    public void getContacts(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                Log.d(TAG, "getContacts: "+
                    "Name: "    + cursor.getString(0) +
                            " Phone: "    + cursor.getString(1));
            }while(cursor.moveToNext());
        }


    }

   /* public void getPDF(){
        InputStream inputStream = getAssets().open("asdf.pdf")
    }
*/

}

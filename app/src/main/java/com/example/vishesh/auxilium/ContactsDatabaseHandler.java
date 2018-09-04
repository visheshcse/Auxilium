package com.example.vishesh.auxilium;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class ContactsDatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="Contacts.db";
    public static final String TABLE_CONTACTS="Contacts_Imported";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_PHONENO="number";

    public ContactsDatabaseHandler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query="CREATE TABLE "+TABLE_CONTACTS+" ( "+COLUMN_ID+" INTEGER PRIMARY KEY, "
                        +COLUMN_NAME+" TEXT, "+COLUMN_PHONENO+" INTEGER "+");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACTS);
            onCreate(db);
    }

    public void addContacts(String id,String contactName,String contactPhoneNo)
    {
        ContentValues contentValues=new ContentValues();
        SQLiteDatabase db=getWritableDatabase();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_NAME,contactName);
        contentValues.put(COLUMN_PHONENO,contactPhoneNo);
        db.insert(TABLE_CONTACTS,null,contentValues);
     //   db.close();
    }

    public void onStop()
    {

    }
    public void deleteContacts(String contactName)
    {
        SQLiteDatabase db=getWritableDatabase();
        String query="DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_NAME + "=\"" + contactName +"\";";
        db.execSQL(query);
    }

    Cursor getCursor(String name)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + ";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        return cursor;
    }
/*
    public ArrayList<String> databaseToString()
    {

        ArrayList<String> arrayList=new ArrayList<>();
        String dbNameString="";
        String dbPhoneNoString="";
        SQLiteDatabase db=getWritableDatabase();

        String query="SELECT * FROM "+TABLE_CONTACTS;
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("name"))!=null)
            {
                dbNameString+=c.getString(c.getColumnIndex("name"));
                dbPhoneNoString+=c.getString(c.getColumnIndex("number"));
                arrayList.add(dbNameString+"\n("+dbPhoneNoString+")");
            }
            c.moveToNext();
        }
        db.close();
        return arrayList;
    }
*/

    Cursor getListContacts()
    {  String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + ";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        return cursor;
    }
}

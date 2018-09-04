package com.example.vishesh.auxilium;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ReliefMessagesDatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="ReliefMessages.db";
    public static final String TABLE_MESSAGES="Relief_Messages";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_TAG="tag";
    public static final String COLUMN_MESSAGE="name";

    public ReliefMessagesDatabaseHandler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query="CREATE TABLE "+TABLE_MESSAGES+" ( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_TAG+" TEXT, "+COLUMN_MESSAGE+" TEXT"+");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MESSAGES);
        onCreate(db);
    }

    public void addMessages(String id,String messageName)
    {
        ContentValues contentValues=new ContentValues();
        SQLiteDatabase db=getWritableDatabase();
        contentValues.put(COLUMN_TAG,id);
        contentValues.put(COLUMN_MESSAGE,messageName);
        db.insert(TABLE_MESSAGES,null,contentValues);
        //   db.close();
    }

    public void onStop()
    {

    }
    public void deleteMessages(String messageId)
    {
        SQLiteDatabase db=getWritableDatabase();
        String query="DELETE FROM " + TABLE_MESSAGES + " WHERE " + COLUMN_TAG + "=\"" + messageId +"\";";
        db.execSQL(query);
    }
    /*
        public Cursor getMessageId(String message)
        {
            SQLiteDatabase db=getWritableDatabase();
            String query="SELECT "+COLUMN_ID+" FROM " + TABLE_MESSAGES + " WHERE " + COLUMN_MESSAGE + "=\"" + message +"\";";
            Cursor cursor=db.rawQuery(query,null);
            return cursor;
        }
    */
    Cursor getMessageContent(String messageId)
    {
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT "+COLUMN_MESSAGE+" FROM " + TABLE_MESSAGES + " WHERE " + COLUMN_TAG + "=\"" + messageId +"\";";
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }


    Cursor getAllMessages()
    {
        String selectQuery = "SELECT  * FROM " + TABLE_MESSAGES + ";";
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
    {  String selectQuery = "SELECT  * FROM " + TABLE_MESSAGES + ";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        return cursor;
    }

}

package com.example.vishesh.auxilium;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewContacts extends AppCompatActivity
{

    ArrayList<String> contact=new ArrayList<>();;
    ListView myListView;

    String id,name,phoneNo;
    private ArrayAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_view_contacts);

        final ContactsDatabaseHandler handler= new ContactsDatabaseHandler(this);
        myAdapter = new ImportContactsCustomAdapter(this, contact);
        myListView = (ListView)findViewById(R.id.listView2);
        myListView.setAdapter(myAdapter);

        //      getSharedPreferences();
        Cursor cursor=handler.getListContacts();
        if(cursor!=null && cursor.moveToFirst())
        {   do
        {
            name=cursor.getString(cursor.getColumnIndex("name"));
            phoneNo=cursor.getString(cursor.getColumnIndex("number"));
            contact.add(name+"\n("+phoneNo+")");
            myAdapter.notifyDataSetChanged();
            cursor.moveToNext();
        }while (!cursor.isAfterLast());
            cursor.close();
        }
    }
}

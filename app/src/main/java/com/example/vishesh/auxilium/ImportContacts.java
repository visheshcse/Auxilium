package com.example.vishesh.auxilium;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ImportContacts extends AppCompatActivity
{
    ArrayList<String> contact=new ArrayList<>();;
    ListView myListView;

    String id,name,phoneNo;
    static final int RESULT_PICK_CONTACT=1;
    private ArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_import_contacts);

        final ContactsDatabaseHandler handler= new ContactsDatabaseHandler(this);
        myAdapter = new ImportContactsCustomAdapter(this, contact);
        myListView = (ListView)findViewById(R.id.listView);
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

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                TextView textView = (TextView)view.findViewById(R.id.myText);
                final String mySourceString = textView.getText().toString();
                int endIndex=mySourceString.indexOf("\n");
                final String substr=mySourceString.substring(0,endIndex);
                AlertDialog.Builder ab =new AlertDialog.Builder(ImportContacts.this);
                ab.setMessage("Do you want to delete contact of "+substr+"?")
                        .setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        handler.deleteContacts(substr);
                        contact.remove(position);
                        myAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),mySourceString+"\n"+"Successfully Removed",Toast.LENGTH_SHORT).show();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                AlertDialog alert=ab.create();
                alert.setTitle("DELETION CONFIRMATION");
                alert.show();

                return true;
            }
        });

        ImageButton addContact=(ImageButton)findViewById(R.id.add_contacts);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);

            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {        // check whether the result is ok
        if (resultCode ==MainActivity.RESULT_OK)
        {   // Check for the request code, we might be using multiple startActivityForReslut
            switch (requestCode)
            {   case RESULT_PICK_CONTACT:
                Cursor cursor = null;
                try
                {   Uri uri = data.getData();
                    cursor = getContentResolver().query(uri, null, null, null, null);
                    cursor.moveToFirst();
                    int idIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
                    id=cursor.getString(idIndex);

                    int nameIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    name=cursor.getString(nameIndex);

                    int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    phoneNo = cursor.getString(phoneIndex);
                    storeView();
                }catch (Exception e)
                {   e.printStackTrace();
                }
                break;
            }
        }else
        {   Log.e("MainActivity", "Failed to pick contact");
        }
    }

    void storeView()
    {    ContactsDatabaseHandler handler=new ContactsDatabaseHandler(this);
        contact.add(name+"\n("+phoneNo+")");
        myAdapter.notifyDataSetChanged();
        handler.addContacts(id,name,phoneNo);
    }


}

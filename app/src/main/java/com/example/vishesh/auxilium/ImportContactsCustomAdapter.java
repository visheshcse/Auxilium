package com.example.vishesh.auxilium;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class ImportContactsCustomAdapter extends ArrayAdapter<String>
{
    public ImportContactsCustomAdapter(Context context, ArrayList<String> Contacts)
    {
        super(context, R.layout.import_contacts_custom_view,Contacts);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater myInflater=LayoutInflater.from(getContext());
        View custom_view=myInflater.inflate(R.layout.import_contacts_custom_view,parent,false);

        String contact=getItem(position);
        TextView textView=(TextView)custom_view.findViewById(R.id.myText);
        ImageView imageView=(ImageView)custom_view.findViewById(R.id.myImage);

        textView.setText(contact);
        imageView.setImageResource(R.drawable.unknown_person_icon);
        return  custom_view;
    }
}


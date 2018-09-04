package com.example.vishesh.auxilium;


/*package net.simplifiedcoding.androidtablayout;
*/
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;



//Our class extending fragment
public class Tab1 extends Fragment
{
    private View v;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab1, container, false);
        ImageView image_view =(ImageView) v.findViewById(R.id.imageView15);

/*        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.h1), 2000);
        animation.addFrame(getResources().getDrawable(R.drawable.h2), 2000);
        animation.addFrame(getResources().getDrawable(R.drawable.h3), 2000);
        animation.addFrame(getResources().getDrawable(R.drawable.h4), 2000);

        animation.setOneShot(false);
        image_view.setBackgroundDrawable(animation);

        animation.start();
*/

/*        // create the transition layers
        Drawable backgrounds[] = new Drawable[4];
        Resources res = getResources();
        backgrounds[0] = res.getDrawable(R.drawable.h1);
        backgrounds[1] = res.getDrawable(R.drawable.h2);
        backgrounds[2] = res.getDrawable(R.drawable.h3);
        backgrounds[3] = res.getDrawable(R.drawable.h4);

        TransitionDrawable crossfader = new TransitionDrawable(backgrounds);
        image_view.setImageDrawable(crossfader);
        crossfader.startTransition(1000);
*/

        ImageButton btn_clicked=(ImageButton)v.findViewById(R.id.imageButton);
        ImageButton relief_btn_clicked=(ImageButton)v.findViewById(R.id.imageButton2);
        btn_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    toSendEmergencyMessageMethod();
            }
        });
        relief_btn_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSendReliefMessageMethod();
            }
        });
        return v;
    }

    public void toSendEmergencyMessageMethod()
    {
        AlertDialog.Builder ab =new AlertDialog.Builder(getActivity());
        ab.setMessage("Do you want to send the Message")
                .setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent=new Intent(getActivity(),SendingEmergencyMessage.class);
                startActivity(intent);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert=ab.create();
        alert.setTitle("SENDING CONFIRMATION");
        alert.show();
    }

    public void toSendReliefMessageMethod()
    {
        AlertDialog.Builder ab =new AlertDialog.Builder(getActivity());
        ab.setMessage("Do you want to send the Message")
                .setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent=new Intent(getActivity(),SendingReliefMessage.class);
                startActivity(intent);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert=ab.create();
        alert.setTitle("SENDING CONFIRMATION");
        alert.show();
    }

}







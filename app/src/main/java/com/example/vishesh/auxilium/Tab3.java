package com.example.vishesh.auxilium;
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;

/*package net.simplifiedcoding.androidtablayout;
*/
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


//Our class extending fragment
public class Tab3 extends Fragment
{
    private View v;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.tab3, container, false);
        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes

        return v;

    }
}


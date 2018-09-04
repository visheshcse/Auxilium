package com.example.vishesh.auxilium;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
                                                               TabLayout.OnTabSelectedListener
{   private int id;

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("HOME"));
        tabLayout.addTab(tabLayout.newTab().setText("FIRST AID"));
        tabLayout.addTab(tabLayout.newTab().setText("LOOK FOR HELP"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);


        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar1, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {   super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        id = item.getItemId();

        if(id == R.id.home)
        {
 //               setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            /*
                Fragment homeFragment=new HomeFragment();
                android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_page,homeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
*/
        }

        else if (id == R.id.import_contacts)
        {
  //              setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                Intent intent=new Intent(this,ImportContacts.class);
                startActivity(intent);

            /*
                Fragment importContacts = new ImportContacts();
                android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout,importContacts);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
*/        }

        else if (id == R.id.view_contacts)
        {
  //          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            Intent intent=new Intent(this,ViewContacts.class);
            startActivity(intent);
        }

        else if (id == R.id.emergeny_message)
        {
    //        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            Intent intent=new Intent(this,EmergencyMessages.class);
            startActivity(intent);
        }

        else if (id == R.id.relief_message)
        {
    //        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            Intent intent=new Intent(this,ReliefMessages.class);
            startActivity(intent);
        }

        else if (id == R.id.share_app)
        {
            try{
                ArrayList<Uri> uris = new ArrayList<Uri>();
                Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                sendIntent.setType("application/*");
                uris.add(Uri.fromFile(new File(getApplicationInfo().publicSourceDir)));
                sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
                startActivity(Intent.createChooser(sendIntent, null));

            }catch(Exception e)
            {     e.printStackTrace();
            }
        }

        else if (id == R.id.disclaimer_bar)
        {
                showInputDialog();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void showInputDialog()
    {
        String s="We have tried our best of ability to keep the application up and running smoothly but due to the nature of internal" +
                " GPS services involved,we take no responsibility and will not be liable for any loss and damage suffered as a result " +
                "of using this application";
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View promptView = layoutInflater.inflate(R.layout.disclaimer_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);
        TextView tv=(TextView)promptView.findViewById(R.id.textView16);
        tv.setText(s);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {      viewPager.setCurrentItem(tab.getPosition());
           if(tab.getPosition()==2)
           {
               Intent i=new Intent(this,MapsActivity.class);
               startActivity(i);
           }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab)
    {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab)
    {
    }

}

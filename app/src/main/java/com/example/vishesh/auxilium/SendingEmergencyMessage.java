package com.example.vishesh.auxilium;

import android.*;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendingEmergencyMessage extends AppCompatActivity {
    TextView tv1, tv2;
    BroadcastReceiver brsent;
    Intent intesent;
    PendingIntent pisent;
    IntentFilter infsent;
    String sms_sent = "MessageSent";

    BroadcastReceiver brdel;
    Intent intedel;
    PendingIntent pidel;
    IntentFilter infdel;
    String sms_del = "MessageDelivered";

    String phoneNo = "";
    private String message;
    double latitude=0;
    double longitude=0;
    String addstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_emergeny_message);

        intesent = new Intent(sms_sent);
        pisent = PendingIntent.getBroadcast(getApplicationContext(), 0, intesent, 0);
        infsent = new IntentFilter(sms_sent);

        brsent = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case RESULT_OK:
                        Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getApplicationContext(), "Sending Failed because of PDU", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getApplicationContext(), "Sending Failed", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getApplicationContext(), "Sending Failed because Radio is off", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Error occured while sending the message", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        intedel = new Intent(sms_del);
        pidel = PendingIntent.getBroadcast(getApplicationContext(), 0, intedel, 0);
        infdel = new IntentFilter(sms_del);

        brdel = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case RESULT_OK:
                        Toast.makeText(getApplicationContext(), "SMS Delivered", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getApplicationContext(), "Delivery Failed because of PDU", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getApplicationContext(), "Delivery Failed", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getApplicationContext(), "Delivery Failed because Radio is off", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Error occured while delivery the message", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        sendSMS();
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(brsent, infsent);
        registerReceiver(brdel, infdel);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(brsent);
        unregisterReceiver(brdel);
    }

    public void sendSMS() {
        final ContactsDatabaseHandler handler = new ContactsDatabaseHandler(this);
        Cursor cursor = handler.getListContacts();
        if (cursor != null && cursor.moveToFirst()) {
            phoneNo = cursor.getString(cursor.getColumnIndex("number"));
            do {
                cursor.moveToNext();
                if (!cursor.isAfterLast()) {
                    phoneNo += ",";
                    phoneNo += cursor.getString(cursor.getColumnIndex("number"));
                }
            } while (!cursor.isAfterLast());
            cursor.close();
        }
        String numbers[] = phoneNo.split(", *");

        tv2 = (TextView) findViewById(R.id.textView12);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SendingEmergencyMessage.this);
        tv2.setText(pref.getString("idid", "null"));
        message = tv2.getText().toString();

        Double d= Double.valueOf(0);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SendingEmergencyMessage.this);
        latitude= Double.parseDouble(preferences.getString("Latitude", String.valueOf(d)));
        longitude= Double.parseDouble(preferences.getString("Longitude", String.valueOf(d)));
        addstr = "";
        Geocoder gc=new Geocoder(getApplicationContext());
        try
        {
            List<Address> address=gc.getFromLocation(latitude,longitude,1);
            for(int i=0; i<address.size(); i++)
            {
                Address a=address.get(i);
                for(int j=0;j<a.getMaxAddressLineIndex();j++)
                {   addstr +=a.getAddressLine(j)+"\n";
                }
            }

            if (phoneNo.length() == 0)
            {
                Toast.makeText(getApplicationContext(), "Import Contacts", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder ab = new AlertDialog.Builder(SendingEmergencyMessage.this);
                ab.setMessage("Import the Contacts From your Contact List before you want to send the Message")
                    .setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                        }
            });
                AlertDialog alert = ab.create();
                alert.setTitle("MESSAGE");
                alert.show();

            } else if (message.equals("null") || message.equals("New Text") || message.equals("PLEASE SELECT THE MESSAGE YOU WANT TO SEND")
                || message.equals("Default message will be displayed here"))
                {
                AlertDialog.Builder ab = new AlertDialog.Builder(SendingEmergencyMessage.this);
                ab.setMessage("SELECT THE MESSAGE YOU WANT TO SEND")
                    .setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
                AlertDialog alert = ab.create();
                alert.setTitle("MESSAGE");
                alert.show();
                }
            else
            {
                message=message+"\nThis message is sent from ";
                message=message + addstr;
                tv2.setText(message);

                tv1 = (TextView) findViewById(R.id.textView11);
                tv1.setText(phoneNo);

                final SmsManager sms = SmsManager.getDefault();
                for (String messageNumber : numbers)
                {
                    messageNumber = "+" + messageNumber;
                    sms.sendTextMessage(messageNumber, null, message, pisent, pidel);
                    final Handler sleep = new Handler();
                    sleep.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {         // Do something after 5s = 5000m
                        }
                    }, 2000);
                }

            }
        }catch (IOException e) {
            Toast.makeText(getApplication(),"IOException"+e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

/*
    public double getLati(Location location)
    {
        return location.getLatitude();

    }

    public  double getLongi(Location location)
    {
        return location.getLongitude();
    }
    */
}

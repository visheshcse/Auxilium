package com.example.vishesh.auxilium;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.FloatMath;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;


public class MyService extends Service implements SensorEventListener
{
    private SensorManager sensorMan;
    private Sensor accelerometer;

    private float[] mGravity;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        sensorMan = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        sensorMan.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_UI);

        return START_STICKY;
    }

/*
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        sensValues=event.values;
        float x=sensValues[0];
        float y=sensValues[1];
        float z=sensValues[2];

        if(x>15 || y>15 || z>15)
        {
            Toast.makeText(getApplicationContext(),"Phone Shaked",Toast.LENGTH_LONG).show();
            Intent i=new Intent(FLAG_,MainActivity.class);
            startActivity(i);
        }
    }
*/

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values.clone();
            // Shake detection
            float x = mGravity[0];
            float y = mGravity[1];
            float z = mGravity[2];
            mAccelLast = mAccelCurrent;
            if(x>15 || y>15 || z>15)
            {
                Intent i=new Intent(getBaseContext(),MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                final Handler sleep = new Handler();
                sleep.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {      onDestroy();
                    }
                }, TimeUnit.MINUTES.toMillis(60));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        sensorMan.unregisterListener(this);
    }


}

package com.example.vishesh.auxilium;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.concurrent.TimeUnit;

public class HomeScreenActivity extends AppCompatActivity  implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener
{

    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        final Handler sleep = new Handler();
        sleep.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startService(new Intent(getBaseContext(), MyService.class));
                Intent i=new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        }, TimeUnit.SECONDS.toMillis(5));

        this.gestureDetector=new GestureDetector(this,this);
        gestureDetector.setOnDoubleTapListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        startService(new Intent(getBaseContext(), MyService.class));
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}

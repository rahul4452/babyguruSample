package com.example.poplify.baby_guru_sample.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;

public class Timer_Service extends Service {

    Chronometer cm;
    Boolean resume = false;
    long elapsedTime;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    Chronometer chronometer;
    private static final int LOC_API_CALL_INTERVAL = 60 * 1000;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        chronometer = new Chronometer(Timer_Service.this);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();


        return START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        return super.onUnbind(intent);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();



    }


}

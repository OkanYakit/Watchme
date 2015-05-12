package com.okanyakit.watchme;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class StopCountDownTimer extends ActionBarActivity {
    Button buttonStopCountDownTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_count_down_timer);

        buttonStopCountDownTimer = (Button)findViewById(R.id.btnStopCountDownTimer);

        buttonStopCountDownTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If the alarm has been set, cancel it.
                if (MyApplication.alarmManager!= null) {
                    MyApplication.alarmManager.cancel(MyApplication.getAlarmIntent());
                }
                Log.d("Nurettin","I AM OK");
                MyAlarmService.stopRunnable();
            }
        });
    }
}


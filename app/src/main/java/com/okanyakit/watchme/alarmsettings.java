package com.okanyakit.watchme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by okan on 4/23/2015.
 */
public class alarmsettings extends android.support.v4.app.Fragment {
    View rootview;
    Button setalarm;
    EditText hour,minute,message,phonenumber;
    final  Calendar calendar = Calendar.getInstance();
    int iHour, iMinute;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.alarmsettings_layout,container,false);

        final Calendar calendar = Calendar.getInstance();
        hour = (EditText)rootview.findViewById(R.id.etHour);
        minute = (EditText)rootview.findViewById(R.id.etMinute);
        setalarm = (Button)rootview.findViewById(R.id.btnSetNotif);
        message = (EditText)rootview.findViewById(R.id.etMessage);
        phonenumber=(EditText)rootview.findViewById(R.id.etPhoneNumber);

        setalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iHour = Integer.parseInt(hour.getText().toString());
                iMinute = Integer.parseInt(minute.getText().toString());
//                ((MyApplication) getActivity().getApplication()).setMessage(message.getText().toString());
//                ((MyApplication) getActivity().getApplication()).setPhonenumber(phonenumber.getText().toString());

                calendar.set(Calendar.HOUR_OF_DAY, iHour);
                calendar.set(Calendar.MINUTE, iMinute);
                calendar.set(Calendar.SECOND, 0);
//                calendar.set(Calendar.AM_PM, 0);

                Intent myIntent = new Intent(getActivity(), MyReceiver.class);
                PendingIntent alarmIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent, 0);

                MyApplication.alarmManager = (AlarmManager)getActivity(). getSystemService(ALARM_SERVICE);
                MyApplication.alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), alarmIntent);

                // setRepeating() lets you specify a precise custom interval--in this case,
                // 20 minutes.
                MyApplication.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        1000 * 10 * 1, alarmIntent);

                MyApplication.setAlarmIntent(alarmIntent);
                Context context = MyApplication.getAppContext();



//                AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//                Intent intent = new Intent(context, BroadcastReceiver.class);
//                alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
//
//                // Set the alarm to start at 8:30 a.m.
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTimeInMillis(System.currentTimeMillis());
//                calendar.set(Calendar.HOUR_OF_DAY, 24);
//                calendar.set(Calendar.MINUTE, 18);
//
//                // setRepeating() lets you specify a precise custom interval--in this case,
//                // 20 minutes.
//                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                        1000 * 30 * 1, alarmIntent); //30 saniyede 1

            }
        });
        return rootview;
    }
}

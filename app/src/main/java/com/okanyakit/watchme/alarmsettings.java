package com.okanyakit.watchme;

import android.app.AlarmManager;
import android.app.PendingIntent;
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
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent, 0);

                AlarmManager alarmManager = (AlarmManager)getActivity(). getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);


            }
        });
        return rootview;
    }
}

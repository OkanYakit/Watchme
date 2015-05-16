package com.okanyakit.watchme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by okan on 4/23/2015.
 */
public class alarmsettings extends android.support.v4.app.Fragment {
    View rootview;
    Button setalarm, cancelalarm;
    EditText hour,minute;
    final  Calendar calendar = Calendar.getInstance();
    int iHour, iMinute;
    boolean alarmCreated= false;
    Scheduler scheduler = new Scheduler();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.alarmsettings_layout,container,false);

        final Calendar calendar = Calendar.getInstance();
        hour = (EditText)rootview.findViewById(R.id.etHour);
        minute = (EditText)rootview.findViewById(R.id.etMinute);
        setalarm = (Button)rootview.findViewById(R.id.btnSetNotif);
        cancelalarm = (Button)rootview.findViewById(R.id.cancelAlarm);
        SharedPreferences alarmpreferences = this.getActivity().getSharedPreferences("Alarm", Context.MODE_PRIVATE);
        final SharedPreferences.Editor alarmEditor= alarmpreferences.edit();

        cancelalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarmCreated){
//                    alarmCreated = false;
                    alarmEditor.putBoolean("alarmCreated",false);
                }else
                {
                    Toast.makeText(getActivity(),"You haven't defined and alarm".toString(),Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        setalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validationError = false;

                StringBuilder validationErrorMessage = new StringBuilder("Please ");
                if (isEmpty(hour))
                {
                    validationError = true;
                    validationErrorMessage.append("enter a hour ");
                }
                if (isEmpty(minute))
                {
                    if (validationError){
                        validationErrorMessage.append("and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter a minute ");
                }
                validationErrorMessage.append(".");
                if (validationError){
                    Toast.makeText(getActivity(),validationErrorMessage.toString(),Toast.LENGTH_LONG).show();
                    return;
                }

                iHour = Integer.parseInt(hour.getText().toString());
                iMinute = Integer.parseInt(minute.getText().toString());


                    calendar.set(Calendar.HOUR_OF_DAY, iHour);
                    calendar.set(Calendar.MINUTE, iMinute);
                    calendar.set(Calendar.SECOND, 0);


                Intent myIntent = new Intent(getActivity(), MyReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent, 0);

                AlarmManager alarmManager = (AlarmManager)getActivity(). getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
//                alarmCreated = true;
                alarmEditor.putBoolean("alarmCreated",true);
            }
        });
        return rootview;
    }


    private boolean isEmpty(EditText editText){
        if (editText.getText().toString().trim().length() > 0){
            return false;
        }else {
            return true;
        }
    }

}

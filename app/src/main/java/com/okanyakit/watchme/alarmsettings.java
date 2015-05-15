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
    EditText hour,minute,message,phonenumber;
    final  Calendar calendar = Calendar.getInstance();
    int iHour, iMinute;
    boolean alarmCreated = false;
    String smessage;
    String sphonenumber;
    String myadress = "";
    String mymessage = "";
    Scheduler scheduler = new Scheduler();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.alarmsettings_layout,container,false);

//        GPSTracker gps = new GPSTracker(getActivity());

        final Calendar calendar = Calendar.getInstance();

        hour = (EditText)rootview.findViewById(R.id.etHour);
        minute = (EditText)rootview.findViewById(R.id.etMinute);
        setalarm = (Button)rootview.findViewById(R.id.btnSetNotif);
        message = (EditText)rootview.findViewById(R.id.etMessage);
        phonenumber=(EditText)rootview.findViewById(R.id.etPhoneNumber);
        cancelalarm = (Button)rootview.findViewById(R.id.cancelAlarm);

        cancelalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarmCreated){
                    alarmCreated = false;
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
                alarmCreated = true;

//                startAlarm(getView());
                final Timer waitingTimer = new Timer();
                waitingTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (alarmCreated == false){
                            waitingTimer.cancel();
                        }else {
                            sendmessage();
                        }


                    }
                }, 60000, 180000);


            }
        });
        return rootview;
    }
      private void sendmessage() {
        GPSTracker gps = new GPSTracker(getActivity());
          SharedPreferences messagepreferences = this.getActivity().getSharedPreferences("Message", Context.MODE_PRIVATE);
          smessage = messagepreferences.getString("Message",null);
          sphonenumber = messagepreferences.getString("PhoneNumber",null);

          if (smessage==null||sphonenumber==null)
          {
              Toast.makeText(getActivity(),"please define a message/phone number in message and contacts section",Toast.LENGTH_LONG).show();
          }else {

        if (gps.canGetLocation()){
            String slatitude;
            String slongitude;
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            String streetAddress = gps.getstreetAddress();

            slongitude = Double.toString(longitude);
            slatitude = Double.toString(latitude);
            String coordinates = " "+slatitude+" , "+slongitude+" ";
             myadress = "My addres is: \n"+ streetAddress + "\n My coordinates are :" + coordinates+" ";




        } else {
             myadress = "";
        }


        String prewrittenmessage =smessage+ myadress;
        String phone = sphonenumber;
        Intent sendtoemail = new Intent(Intent.ACTION_SEND);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, prewrittenmessage, null, null);
          }
    }


    private boolean isEmpty(EditText editText){
        if (editText.getText().toString().trim().length() > 0){
            return false;
        }else {
            return true;
        }
    }

    public void startAlarm(View view) {

        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(getActivity(), AlarmReceiver.class);
        scheduler.pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, alarmIntent, 0);

        scheduler.manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        int interval = 60000;

        Scheduler.manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, Scheduler.pendingIntent);
        Log.d("Nurettin", "Alarm Set! 10 seconds");
    }


}

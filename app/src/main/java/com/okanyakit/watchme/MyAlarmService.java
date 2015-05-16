package com.okanyakit.watchme;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyAlarmService extends Service {
    public static Handler handler = new Handler();
    NotificationCompat.Builder mBuilder;
    public static final int NOTIFICATION_ID = 4567;
    String smessage;
    String sphonenumber;
    String myadress = "";
    boolean alarmCreated;

    public MyAlarmService() {
    }
    private NotificationManager mManager;

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {

        // TODO Auto-generated method stub
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
        SharedPreferences alarmpreferences = this.getApplication().getSharedPreferences("Alarm", Context.MODE_PRIVATE);
        alarmCreated = alarmpreferences.getBoolean("alarmCreated",true);



        Intent intent1 = new Intent(this.getApplication(),alarmsettings.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplication(),0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setAutoCancel(true);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setTicker("This is the ticker");
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setContentTitle("Watch me");
        mBuilder.setContentText("Are you ok ?");
        mBuilder.setSound(RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION));
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager nManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_ID, mBuilder.build());
        final Timer waitingTimer = new Timer();
        waitingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (alarmCreated == false) {
                    waitingTimer.cancel();
                } else {
                    sendmessage();
                }


            }
        }, 6000, 180000);

    }


    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }



    private void sendmessage() {
        GPSTracker gps = new GPSTracker(getApplication());
        SharedPreferences messagepreferences = this.getApplication().getSharedPreferences("Message", Context.MODE_PRIVATE);
        smessage = messagepreferences.getString("Message",null);
        sphonenumber = messagepreferences.getString("PhoneNumber",null);

        if (smessage==null||sphonenumber==null)
        {
            Toast.makeText(getApplication(), "please define a message/phone number in message and contacts section", Toast.LENGTH_LONG).show();
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
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, prewrittenmessage, null, null);
        }
    }

}
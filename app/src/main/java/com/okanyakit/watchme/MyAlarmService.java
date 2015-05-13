package com.okanyakit.watchme;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;

public class MyAlarmService extends Service {
//    static MyCountDownTime myCountDownTimer= new MyCountDownTime(60000, 20000);
    public static Handler handler = new Handler();
    NotificationCompat.Builder mBuilder;
    public static final int NOTIFICATION_ID = 4567;

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
        Intent intent1 = new Intent(this.getApplication(),alarmsettings.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplication(),0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setAutoCancel(true);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setTicker("This is the ticker");
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setContentTitle("Watch me");
        mBuilder.setContentText("Are you ok ?");
        mBuilder.setSound(RingtoneManager.getActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION));
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager nManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_ID,mBuilder.build());





//        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
//        Intent intent1 = new Intent(this.getApplicationContext(),alarmsettings.class);
//
//        Notification notification = new Notification(R.mipmap.ic_launcher,"This is a test message!", System.currentTimeMillis());
//        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notification.setLatestEventInfo(this.getApplicationContext(), "AlarmManagerDemo", "This is a test message!", pendingNotificationIntent);
//
//        mManager.notify(1, notification);

        //myCountDownTimer.start();






    }

    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

   static Runnable runnable = new Runnable() {
        @Override
        public void run() {
      /* do what you need to do */

            sendmessage();
      /* and here comes the "trick" */
            handler.postDelayed(this, 10000);
        }
    };



    static private void sendmessage() {


        String prewrittenmessage ="Test message2";
        String phone = "0702901539";
        Intent sendtoemail = new Intent(Intent.ACTION_SEND);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, prewrittenmessage, null, null);
    }

    static public void stopRunnable (){
        handler.removeCallbacks(runnable);
    }



}
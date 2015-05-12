package com.okanyakit.watchme;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;

/**
 * Created by okan on 5/7/2015.
 */
public class MyApplication extends Application {

    private static Context context;
    private String message;
    private static PendingIntent alarmIntent;
    public static AlarmManager alarmManager;

    public static PendingIntent getAlarmIntent() {
        return alarmIntent;
    }

    public static void setAlarmIntent(PendingIntent alarmIntent) {
        alarmIntent = alarmIntent;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String someVariable) {
        this.message = someVariable;
    }
    private String phonenumber;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String someVariable) {
        this.phonenumber = someVariable;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}

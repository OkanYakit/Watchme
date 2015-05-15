package com.okanyakit.watchme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.util.Log;
import android.view.View;

/**
 * Created by okan on 5/12/2015.
 */
public class Scheduler {
    public static PendingIntent pendingIntent;
    public static AlarmManager manager;

    public static void cancelAlarm(View view) {
        if (manager != null) {
            manager.cancel(pendingIntent);
            Log.d("Nurettin", "Alarm Cancelled!");
        }
    }
}

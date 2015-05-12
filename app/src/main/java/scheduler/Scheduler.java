package scheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by nurettinyakit on 12/05/15.
 */
public class Scheduler {

    public static PendingIntent pendingIntent;
    public static AlarmManager manager;



    public static void cancelAlarm(View view) {
        if (manager != null) {
            manager.cancel(pendingIntent);
            Log.d("Nurettin","Alarm Cancelled!");
        }
    }
}

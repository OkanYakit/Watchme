package scheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by nurettinyakit on 12/05/15.
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        // For our recurring task, we'll just display a message
        Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
    }
}

package com.okanyakit.watchme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

/**
 * Created by okan on 5/12/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        sendmessage();
    }
    static private void sendmessage() {


        String prewrittenmessage ="Test message3";
        String phone = "0702901539";
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, prewrittenmessage, null, null);
    }
}

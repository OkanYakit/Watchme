package com.okanyakit.watchme;

import android.content.Intent;
import android.os.CountDownTimer;
import android.telephony.SmsManager;

/**
 * Created by okan on 5/7/2015.
 */
public class MyCountDownTime extends CountDownTimer {
    boolean firstTime = true;




    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public MyCountDownTime(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);


    }

    @Override
    public void onTick(long millisUntilFinished) {


        if (firstTime){
            firstTime = false;
        }else {


            String prewrittenmessage;
            prewrittenmessage = "Deneme mesaj?";
            String phone = "0702901539";
            Intent sendtoemail = new Intent(Intent.ACTION_SEND);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, prewrittenmessage, null, null);
        }
    }

    @Override
    public void onFinish() {


    }
    public void onPause(){

    }


}

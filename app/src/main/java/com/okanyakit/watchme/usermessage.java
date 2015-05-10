package com.okanyakit.watchme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by okan on 4/23/2015.
 */
public class usermessage extends android.support.v4.app.Fragment implements View.OnClickListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    View rootview;
    EditText usermessage,phonenumber;
    Button savemessage, send_message;
    String sUserMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.usermessage_layout,container,false);
        usermessage = (EditText)rootview.findViewById(R.id.usermessage);
        phonenumber = (EditText)rootview.findViewById(R.id.phonenumber);
        savemessage = (Button)rootview.findViewById(R.id.savemessage);
        send_message = (Button)rootview.findViewById(R.id.send_message);
        savemessage.setOnClickListener(this);
        send_message.setOnClickListener(this);
        sUserMessage = "I can't reach my phone, i may be in a dangerous situation. My latest location is below please try to reach me ";

        return rootview;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.savemessage:

                sUserMessage = usermessage.getText().toString();

                break;
            case R.id.send_message:

                String prewrittenmessage =sUserMessage;
                String phone = phonenumber.getText().toString();
                Intent sendtoemail = new Intent(Intent.ACTION_SEND);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone,null,prewrittenmessage,null,null);

                break;
        }
    }
}

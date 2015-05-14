package com.okanyakit.watchme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by okan on 4/23/2015.
 */
public class usermessage extends android.support.v4.app.Fragment implements View.OnClickListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    View rootview;
    EditText usermessage,phonenumber;
    Button savemessage, send_message,savephonenumber;
    String sUserMessage,sPhoneNumber,smessage, myadress,sphonenumber;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.usermessage_layout,container,false);
        SharedPreferences messagepreferences = this.getActivity().getSharedPreferences("Message", Context.MODE_PRIVATE);
        sUserMessage = messagepreferences.getString("Message",null);
        sPhoneNumber = messagepreferences.getString("PhoneNumber",null);

        usermessage = (EditText)rootview.findViewById(R.id.usermessage);
        if (sUserMessage != null){
            usermessage.setText(sUserMessage);
        }
        phonenumber = (EditText)rootview.findViewById(R.id.phonenumber);
        if (sPhoneNumber != null)
        {
            phonenumber.setText(sPhoneNumber);
        }
        usermessage = (EditText)rootview.findViewById(R.id.usermessage);
        phonenumber = (EditText)rootview.findViewById(R.id.phonenumber);
        savemessage = (Button)rootview.findViewById(R.id.savemessage);
        send_message = (Button)rootview.findViewById(R.id.send_message);
        savephonenumber =(Button)rootview.findViewById(R.id.savephonenumber);
        savephonenumber.setOnClickListener(this);
        savemessage.setOnClickListener(this);
        send_message.setOnClickListener(this);
        sUserMessage = "";

        return rootview;
    }

    @Override
    public void onClick(View v) {
        SharedPreferences messagepreferences = this.getActivity().getSharedPreferences("Message", Context.MODE_PRIVATE);
        SharedPreferences.Editor addressEditor= messagepreferences.edit();
        switch (v.getId()) {
            case R.id.savemessage:

                sUserMessage = usermessage.getText().toString();

                addressEditor.putString("Message", sUserMessage);
                addressEditor.commit();



                break;
            case R.id.send_message:

               sendmessage();

                break;
            case R.id.savephonenumber:
                sPhoneNumber = phonenumber.getText().toString();

                addressEditor.putString("PhoneNumber",sPhoneNumber);
                addressEditor.commit();

                break;
        }
    }
    private void sendmessage() {
        GPSTracker gps = new GPSTracker(getActivity());
        SharedPreferences messagepreferences = this.getActivity().getSharedPreferences("Message", Context.MODE_PRIVATE);
        smessage = messagepreferences.getString("Message",null);
        sphonenumber = messagepreferences.getString("PhoneNumber",null);

        if (smessage==null||sphonenumber==null)
        {
            Toast.makeText(getActivity(), "please define a message/phone number in message and contacts section", Toast.LENGTH_LONG).show();
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
}

package com.okanyakit.watchme;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import scheduler.AlarmReceiver;
import scheduler.Scheduler;


public class loginscreen extends ActionBarActivity implements View.OnClickListener {

    Button loginbutton;
    EditText logusername, logpassword;
    TextView registerheretv;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);

        logusername = (EditText) findViewById(R.id.logusername);
        logpassword = (EditText) findViewById(R.id.logpassword);

        loginbutton = (Button) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(this);

        registerheretv =(TextView) findViewById(R.id.registerheretv);
        registerheretv.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.loginbutton:

                String username = logusername.getText().toString();
                String password = logpassword.getText().toString();
                User user = new User(username,password);
                parseAuthenticate(user);
//                authenticate(user);


                break;

            case R.id.registerheretv:

                startActivity(new Intent(this,registerscreen.class));
                break;
        }
    }


    public void parseAuthenticate(final User user){


        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("name", logusername.getText().toString());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("parse.com", "Retrieved " + parseObjects.size() + " scores");
                } else {
                    Log.d("parse.com", "Error: " + e.getMessage());
                }

                if(parseObjects!=null){
                    Log.d("parse.com",""+parseObjects.size());
                    if(parseObjects!=null&&parseObjects.size()>0){
                        showMessage("Welcome "+parseObjects.get(0).get("name"));
                        loguserIn(user);
                    }else{
                        showMessage("Please enter a valid user");
                    }

                }else{
                    Log.d("parse.com","Houston 5");
                }
            }
        });


//        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("User");
//        query2.getInBackground("roRQubUf8t", new GetCallback<ParseObject>() {
//
//            @Override
//            public void done(ParseObject parseObject, com.parse.ParseException e) {
//                if (parseObject!=null) {
//                    Log.d("parse.com",parseObject.getString("name"));
//                    // object will be your game score
//                    loguserIn(user);
//                } else {
//                    Log.d("parse.com","Houston");
//                    showErrorMessage();
//                }
//            }
//        });
    }





    public void authenticate (User user){
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    loguserIn(returnedUser);
                }
            }
        });

    }

    private void showErrorMessage(){
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(loginscreen.this);
        dialogbuilder.setMessage("Incorrect username or password");
        dialogbuilder.setPositiveButton("Ok",null);
        dialogbuilder.show();
    }

    private void showMessage(String message){
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(loginscreen.this);
        dialogbuilder.setMessage(message);
        dialogbuilder.setPositiveButton("Ok",null);
        dialogbuilder.show();
    }

    private void loguserIn(User returnedUser){

        userLocalStore.storeUserData(returnedUser);
        NavigationDrawerFragment mNavigationDrawerFragment = new NavigationDrawerFragment();
        startActivity(new Intent(this,slidemenu.class));

        startAlarm(findViewById(R.id.textView3));

    }

    public void startAlarm(View view) {

        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        Scheduler.pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        Scheduler.manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 10000;

        Scheduler.manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, Scheduler.pendingIntent);
        Log.d("Nurettin","Alarm Set! 10 seconds");
    }

}

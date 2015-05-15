package com.okanyakit.watchme.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.okanyakit.watchme.loginscreen;
import com.okanyakit.watchme.slidemenu;
import com.parse.ParseUser;


public class DispatchActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Hangi activity acilacaksa o set edilecek.
        Intent nextIntent = null;
        // Check if there is current user info
        if (ParseUser.getCurrentUser() != null) {
            // Start an intent for the logged in activity
            nextIntent= new Intent(getBaseContext(),slidemenu.class);
        } else {
            // Start and intent for the logged out activity
            nextIntent= new Intent(getBaseContext(),loginscreen.class);
        }
        startActivity(nextIntent);
    }

    //Eski ornek uzerinden gitmek istersek diye.
    public void old(){
        // Check if there is current user info
        if (ParseUser.getCurrentUser() != null) {
            // Start an intent for the logged in activity
            startActivity(new Intent(this, MyActivity.class));
        } else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, SignUpOrLoginActivity.class));
        }
    }
}

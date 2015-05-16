package com.okanyakit.watchme;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.okanyakit.watchme.activities.DispatchActivity;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

import java.util.List;


public class loginscreen extends ActionBarActivity implements View.OnClickListener {

    Button loginbutton;
    ImageButton twitterLogin;
    EditText logusername, logpassword;
    TextView registerheretv;


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

        twitterLogin = (ImageButton) findViewById(R.id.loginWithTwitter);
        twitterLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.loginbutton:
                login();

                break;

            case R.id.registerheretv:

                startActivity(new Intent(this,registerscreen.class));
                break;
            case R.id.loginWithTwitter:
                loginWithTwitter();
                break;
        }
    }


    private void loginWithTwitter(){
        Toast.makeText(loginscreen.this, "Hello Twitter!", Toast.LENGTH_LONG)
                .show();

        ParseTwitterUtils.logIn(loginscreen.this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "Uh oh. The user cancelled the Twitter login.");
                } else if (user.isNew()) {
                    Log.d("MyApp", "User signed up and logged in through Twitter!");
                } else {
                    Log.d("MyApp", "User logged in through Twitter!");
                }
            }
        });
    }


        private void login(){
        String username = logusername.getText().toString();
        String password = logpassword.getText().toString();

        // Validate the log in data
        boolean validationError = false;

        StringBuilder validationErrorMessage =
                new StringBuilder(getResources().getString(R.string.error_intro));
        new StringBuilder(getResources().getString(R.string.error_intro));

        if (isEmpty(logusername)) {
            validationError = true;
            validationErrorMessage.append(getResources().getString(R.string.error_blank_username));
        }
        if (isEmpty(logusername)) {
            if (validationError) {
                validationErrorMessage.append(getResources().getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getResources().getString(R.string.error_blank_password));
        }
        validationErrorMessage.append(getResources().getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(loginscreen.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dlg = new ProgressDialog(loginscreen.this);
        dlg.setTitle("Please wait.");
        dlg.setMessage("Logging in.  Please wait.");
        dlg.show();

        // Call the Parse login method
        ParseUser.logInInBackground(logusername.getText().toString(), logpassword.getText()
                .toString(), new LogInCallback() {

            @Override
            public void done(ParseUser user, ParseException e) {
                dlg.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(loginscreen.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(loginscreen.this, DispatchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
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


}

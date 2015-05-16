package com.okanyakit.watchme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.okanyakit.watchme.activities.DispatchActivity;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class registerscreen extends ActionBarActivity implements View.OnClickListener {

    Button regbutton;
    EditText regusername, regpassword, regemail, regphonenumber, regbloodtype, regbirthday, regaddress, regrepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerscreen);


        regusername = (EditText) findViewById(R.id.regusername);
        regpassword = (EditText) findViewById(R.id.regpassword);
        regemail = (EditText) findViewById(R.id.regemail);
        regphonenumber = (EditText) findViewById(R.id.regphonenumber);
        regbloodtype = (EditText) findViewById(R.id.regbloodtype);
        regbirthday = (EditText) findViewById(R.id.regbirthday);
        regaddress = (EditText) findViewById(R.id.regaddress);
        regrepassword =(EditText)findViewById(R.id.regrepassword);

        regbutton = (Button) findViewById(R.id.regbutton);
        regbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.regbutton:
                boolean validationError = false;
                StringBuilder validationErrorMessage =
                        new StringBuilder(getResources().getString(R.string.error_intro));

                if (isEmpty(regusername))
                {
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_username));
                }
                if (isEmpty(regpassword))
                {
                    if (validationError){
                        validationErrorMessage.append(getResources().getString(R.string.error_join));
                    }
                    validationError =true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_password));
                }
                if (!isMatching(regpassword,regrepassword)){
                    if (validationError){
                        validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter the same password twice");
                }
                validationErrorMessage.append(".");

                if (validationError){
                    Toast.makeText(registerscreen.this,validationErrorMessage.toString(),Toast.LENGTH_LONG).show();
                    return;
                }

                // Set up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(registerscreen.this);
                dlg.setTitle("Please Wait");
                dlg.setMessage("Signing up, Please wait...");
                dlg.show();


                String username = regusername.getText().toString();
                String password = regpassword.getText().toString();
                String email = regemail.getText().toString();
                String phonenumber = regphonenumber.getText().toString();
                String bloodtype = regbloodtype.getText().toString();
                String birthday = regbirthday.getText().toString();
                String address = regaddress.getText().toString();


                // Set up a new Parse user
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.put("phonenumber",phonenumber);
                user.put("bloodtype",bloodtype);
                user.put("birthday",birthday);
                user.put("adress",address);

                // Call the Parse signup method
                user.signUpInBackground(new SignUpCallback() {

                    @Override
                    public void done(ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            // Show the error message
                            Toast.makeText(registerscreen.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            // Start an intent for the dispatch activity
                            Intent intent = new Intent(registerscreen.this, DispatchActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });

                break;
        }
    }

    private boolean isEmpty(EditText editText){
        if (editText.getText().toString().trim().length() > 0){
            return false;
        }else {
            return true;
        }
    }

    private boolean isMatching(EditText editText1, EditText editText2){
        if (editText1.getText().toString().equals(editText2.getText().toString())){
            return true;
        }else {
            return false;
        }
    }
}

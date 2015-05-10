package com.okanyakit.watchme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class registerscreen extends ActionBarActivity implements View.OnClickListener {

    Button regbutton;
    EditText regusername, regpassword, regemail, regphonenumber, regbloodtype, regbirthday, regaddress;
    User user;

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

        regbutton = (Button) findViewById(R.id.regbutton);
        regbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.regbutton:

                String username = regusername.getText().toString();
                String password = regpassword.getText().toString();
                String email = regemail.getText().toString();
                String phonenumber = regphonenumber.getText().toString();
                String bloodtype = regbloodtype.getText().toString();
                String birthday = regbirthday.getText().toString();
                String address = regaddress.getText().toString();

                User user = new User(username,password,email,phonenumber,bloodtype,birthday,address);
                registerUser(user);
                break;
        }
    }

     private void registerUser(User user){
         ServerRequest serverRequest = new ServerRequest(this);
         serverRequest.storeUserDataInBackground(user, new GetUserCallBack() {
             @Override
             public void done(User returnedUser) {
                 startActivity(new Intent(registerscreen.this,loginscreen.class));
             }
         });
     }

}

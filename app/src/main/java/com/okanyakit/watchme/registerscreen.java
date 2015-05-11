package com.okanyakit.watchme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;


public class registerscreen extends ActionBarActivity implements View.OnClickListener {

    Button regbutton;
    EditText regusername, regpassword, regemail, regphonenumber, regbloodtype, regbirthday, regaddress, regrepassword;
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
        regrepassword =(EditText)findViewById(R.id.regrepassword);

        regbutton = (Button) findViewById(R.id.regbutton);
        regbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.regbutton:
                boolean validationError = false;
                StringBuilder validationErrorMessage = new StringBuilder("Please ");
                if (isEmpty(regusername))
                {
                    validationError = true;
                    validationErrorMessage.append("enter a username ");
                }
                if (isEmpty(regpassword))
                {
                    if (validationError){
                        validationErrorMessage.append("and ");
                    }
                    validationError =true;
                    validationErrorMessage.append("enter a password ");
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


                ParseObject userObject = new ParseObject("User");
                userObject.put("name",username);
                userObject.put("password",password);
                userObject.put("email",email);
                userObject.put("phonenumber",phonenumber);
                userObject.put("bloodtype",bloodtype);
                userObject.put("birthday",birthday);
                userObject.put("adress",address);

                userObject.saveInBackground();
                dlg.dismiss();
                startActivity(new Intent(registerscreen.this, loginscreen.class));

                break;
        }
    }

     private void registerUser(User user){
         ServerRequest serverRequest = new ServerRequest(this);
         serverRequest.storeUserDataInBackground(user, new GetUserCallBack() {
             @Override
             public void done(User returnedUser) {
                 startActivity(new Intent(registerscreen.this, loginscreen.class));
             }
         });
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

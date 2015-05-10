package com.okanyakit.watchme;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


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

                authenticate(user);


                break;

            case R.id.registerheretv:

                startActivity(new Intent(this,registerscreen.class));
                break;
        }
    }
    public void authenticate (User user){
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null){
                    showErrorMessage();
                }else {
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
    private void loguserIn(User returnedUser){

        userLocalStore.storeUserData(returnedUser);
//        userLocalStore.setUserLoggedIn(true);
        NavigationDrawerFragment mNavigationDrawerFragment = new NavigationDrawerFragment();
//        DrawerLayout mLayout = (DrawerLayout) mNavigationDrawerFragment.findViewById(R.id.activity_slidemenu.xml);
//        mLayout.openDrawer(mLayout);
        startActivity(new Intent(this,slidemenu.class));

    }

}

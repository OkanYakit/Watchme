package com.okanyakit.watchme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by okan on 4/23/2015.
 */
public class userprofile extends android.support.v4.app.Fragment implements View.OnClickListener{
    View rootview;
    Button logoutbutton;
    EditText regusername, regpassword, regemail, regphonenumber, regbloodtype, regbirthday, regaddress;
    UserLocalStore userLocalStore;
    Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.userprofile_layout,container,false);
        regusername = (EditText)rootview.findViewById(R.id.regusername);
        regpassword = (EditText)rootview.findViewById(R.id.regpassword);
        regemail = (EditText)rootview.findViewById(R.id.regemail);
        regphonenumber = (EditText)rootview.findViewById(R.id.regphonenumber);
        regbloodtype = (EditText)rootview.findViewById(R.id.regbloodtype);
        regbirthday = (EditText)rootview.findViewById(R.id.regbirthday);
        regaddress = (EditText)rootview.findViewById(R.id.regaddress);

        logoutbutton = (Button)rootview.findViewById(R.id.logoutbutton);
        logoutbutton.setOnClickListener(this);


        displayUserDetails();


        return rootview;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        if (authenticate()== true)
//        {
//        displayUserDetails();
//        }
//        else{
//            Intent intent = new Intent(getActivity(),loginscreen.class);
//            startActivity(intent);
//        }
//    }
    private void displayUserDetails()
    {
        UserLocalStore userLocalStore = new UserLocalStore(getActivity());
        User storedUser = userLocalStore.getLoggedInUser();
        regusername.setText(storedUser.username);
        regemail.setText(storedUser.email);
        regphonenumber.setText(storedUser.phonenumber);
        regbloodtype.setText(storedUser.bloodtype);
        regaddress.setText(storedUser.address);
    }

//    private boolean authenticate()
//    {
//        return userLocalStore.getuserloogedin();
//
//    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.logoutbutton:

                UserLocalStore userLocalStore = new UserLocalStore(getActivity());
                userLocalStore.clearUserData();
//                userLocalStore.setUserLoggedIn(false);
                Intent intent = new Intent(getActivity(), loginscreen.class);
                startActivity(intent);

                break;
        }
    }
}

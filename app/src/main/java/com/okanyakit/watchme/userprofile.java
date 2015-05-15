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

import com.okanyakit.watchme.Utilities.StringUtilities;
import com.okanyakit.watchme.activities.DispatchActivity;
import com.parse.ParseUser;

/**
 * Created by okan on 4/23/2015.
 */
public class userprofile extends android.support.v4.app.Fragment implements View.OnClickListener{
    View rootview;
    Button logoutbutton;
    EditText regusername, regpassword, regemail, regphonenumber, regbloodtype, regbirthday, regaddress;

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


    private void displayUserDetails()
    {
        ParseUser user = ParseUser.getCurrentUser();

        if(user!=null){
            regusername.setText(user.getUsername());
            regemail.setText(user.getEmail());
            regphonenumber.setText(getString(user.get("phonenumber")));
            regbloodtype.setText(getString(user.get("bloodtype")));
            regaddress.setText(getString(user.get("adress")));
        }

    }

    //burada kullanilirken uzun gorunmesin diye yeni methoda cekildi
    private String getString(Object string){
        return StringUtilities.getString(string);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.logoutbutton:
                ParseUser.getCurrentUser().logOut();
                startActivity(new Intent(getActivity(), DispatchActivity.class));
                break;
        }
    }
}

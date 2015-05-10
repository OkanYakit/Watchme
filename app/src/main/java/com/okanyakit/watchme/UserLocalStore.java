package com.okanyakit.watchme;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by okan on 4/15/2015.
 */
public class UserLocalStore
{
    public static final String SP_NAME = "userdetails";
    SharedPreferences userLocalDatabase;


    public UserLocalStore(Context context)
    {
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }
    public void storeUserData(User returnedUser)
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("username",returnedUser.username);
        spEditor.putString("password",returnedUser.password);
        spEditor.putString("email",returnedUser.email);
        spEditor.putString("phonenumber",returnedUser.phonenumber);
        spEditor.putString("bloodtype",returnedUser.bloodtype);
        spEditor.putString("birthday",returnedUser.birthday);
        spEditor.putString("address",returnedUser.address);
        spEditor.commit();
    }

    public User getLoggedInUser() {
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        String email = userLocalDatabase.getString("email", "");
        String phonenumber = userLocalDatabase.getString("phonenumber", "");
        String bloodtype = userLocalDatabase.getString("bloodtype", "");
        String birthday = userLocalDatabase.getString("birthday", "");
        String address = userLocalDatabase.getString("address", "");

        User storedUser;
        storedUser = new User(username, password, email, phonenumber, bloodtype, birthday, address);
        return storedUser;
    }

//    public void setUserLoggedIn(boolean loggedIn)
//    {
//        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
//        spEditor.putBoolean("LoggedIn",loggedIn);
//        spEditor.commit();
//    }

//    public boolean getuserloogedin()
//    {
//        if (userLocalDatabase.getBoolean("LoggedIn",false)!= true)
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//
//    }

    public void clearUserData()
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();

    }
}

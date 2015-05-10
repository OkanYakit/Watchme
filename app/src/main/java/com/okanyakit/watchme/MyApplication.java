package com.okanyakit.watchme;

import android.app.Application;

/**
 * Created by okan on 5/7/2015.
 */
public class MyApplication extends Application {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String someVariable) {
        this.message = someVariable;
    }
    private String phonenumber;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String someVariable) {
        this.phonenumber = someVariable;
    }

}

package com.okanyakit.watchme.application;

import android.app.Application;

import com.okanyakit.watchme.R;
import com.parse.Parse;
import com.parse.ParseTwitterUtils;


public class SampleApplication extends Application {

    public void onCreate(){
        //Initialize Parse
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_id));
     //   ParseTwitterUtils.initialize(getString(R.string.twitter_consumer_key), getString(R.string.twitter_secret));
    }
}

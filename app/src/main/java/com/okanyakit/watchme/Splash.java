package com.okanyakit.watchme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by okan on 4/8/2015.
 */
public class Splash extends Activity {
    Boolean log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "75jJmACA0Zbg83q3Rfm3kSsz4ymlwH2YzqoWUnWi", "HN8ZUpBI68fmDRDYCtZuK2WrPhnpTSl50pcCqRet");
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        final ImageView iv = (ImageView)findViewById(R.id.loadingcircle);
        final Animation an =  AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);
        SharedPreferences mySharedPrefecences = getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        log = mySharedPrefecences.getBoolean("UserLoggedIn", false);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);

                finish();
                if (log){
                    Intent x = new Intent(getBaseContext(),slidemenu.class);
                    startActivity(x);
                }else {
                    Intent i = new Intent(getBaseContext(),loginscreen.class);
                    startActivity(i);
                }



            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}

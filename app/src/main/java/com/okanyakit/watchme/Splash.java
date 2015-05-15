package com.okanyakit.watchme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.okanyakit.watchme.activities.DispatchActivity;


/**
 * Created by okan on 4/8/2015.
 */
public class Splash extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final ImageView iv = (ImageView)findViewById(R.id.loadingcircle);
        final Animation an =  AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        //Splash animasyonunu calistir.
        iv.startAnimation(an);

        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                finish();

                // Splash icinde logic olmamasi icin hangi sayfa acilacagi karari dispatch'e tasindi.
                Intent nextIntent = new Intent(getBaseContext(),DispatchActivity.class);
                startActivity(nextIntent);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}

package com.okanyakit.watchme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by okan on 4/23/2015.
 */
public class mainscreen extends android.support.v4.app.Fragment {
    View rootview;
    Button btnShowLocation;
    GPSTracker gps;
    TextView tshowcor, tshowstrt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.mainscreen_layout,container,false);
        btnShowLocation = (Button)rootview.findViewById(R.id.show_location);
        tshowcor = (TextView)rootview.findViewById(R.id.tvshowcoordinates);
        tshowstrt = (TextView)rootview.findViewById(R.id.tvshowstreetaddress);
        GPSTracker gps = new GPSTracker(getActivity());


        if (gps.canGetLocation()){
            String slatitude;
            String slongitude;
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            String streetAddress = gps.getstreetAddress();

            slongitude = Double.toString(longitude);
            slatitude = Double.toString(latitude);
            String coordinates = " "+slatitude+" , "+slongitude+" ";
            tshowcor.setText(coordinates);
            tshowstrt.setText(streetAddress);


        } else {
            gps.showSettingsAlert();
        }

        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker gps = new GPSTracker(getActivity());

                //Nurettin
                Scheduler.cancelAlarm(getView());

                if (gps.canGetLocation()){
                    String slatitude;
                    String slongitude;
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    String streetAddress = gps.getstreetAddress();

                    slongitude = Double.toString(longitude);
                    slatitude = Double.toString(latitude);
                    String coordinates = " "+slatitude+" , "+slongitude+" ";
                    tshowcor.setText(coordinates);
                    tshowstrt.setText(streetAddress);
                    SharedPreferences addresstercih = getActivity().getSharedPreferences("Addressinfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor addresstercihedit = addresstercih.edit();
                    addresstercihedit.putString("coordinates",coordinates);
                    addresstercihedit.putString("streetAddress",streetAddress);
                    addresstercihedit.commit();


                } else {
                    gps.showSettingsAlert();
                }
            }
        });
        return rootview;
    }
}

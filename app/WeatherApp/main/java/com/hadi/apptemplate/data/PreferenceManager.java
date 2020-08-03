package com.hadi.apptemplate.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.hadi.apptemplate.base.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class PreferenceManager {
    Context context;


    public PreferenceManager(Application context) {
        this.context = context;
    }

    public void saveGPSLocation (String lat, String lon){
        SharedPreferences GPSPref = this.context.getSharedPreferences("GPS", 0);
        SharedPreferences.Editor GPSEditor = GPSPref.edit();
        GPSEditor.putString("lat", lat );
        GPSEditor.putString("lon", lon );
        GPSEditor.commit();
    }

    public List<String> getGPSLocation (){
        List<String> GPSCordinates = new ArrayList<>();
        SharedPreferences GPSPref = this.context.getSharedPreferences("GPS", 0);
        SharedPreferences.Editor GPSEditor = GPSPref.edit();
        String lat_long = GPSPref.getString("lat", null );
        String lon_long = GPSPref.getString("lon", null );
        Log.e("GPS 1", lat_long + " "+ lon_long);
        if(lat_long != null && lon_long != null ){
            GPSCordinates.add(0, lat_long);
            GPSCordinates.add(1, lon_long);

        }
        return GPSCordinates;
    }

}

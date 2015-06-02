package com.example.vladimirsinicyn.thermostat;

import android.app.Application;

public class ThermostatApp extends Application {

    public final static int MIN_TEMP = 5;
    public final static int MAX_TEMP = 30;

    private static TCConroller troller;
    private boolean exists = false;

    public void initContorller() {
        if (!exists) {
            troller = new TCConroller();
            exists = true;
        }

        //getMainLooper();
    }

    public TCConroller getConroller() {
        return troller;
    }

//    @Override
//    public void onCreate() {
//        super.onCreate();
//    }
}

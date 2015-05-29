package com.example.vladimirsinicyn.thermostat;

import android.app.Application;

public class ThermostatApp extends Application {

    private static TCConroller conroller;
    private boolean exists = false;

    public void initContorller() {
        if (!exists) {
            conroller = new TCConroller();
            exists = true;
        }
    }

    public TCConroller getConroller() {
        return conroller;
    }

//    @Override
//    public void onCreate() {
//        super.onCreate();
//    }
}

package com.example.vladimirsinicyn.thermostat;

import java.io.Serializable;

public class Time implements Serializable, TimeInterface {

    private int minutes;
    private int hours;

    public Time() {

    }

    public Time(int minutes) {

        parseTime(minutes);
    }

    @Override
    public boolean checkMidnight() {

        return minutes == 0 && hours == 0;
    }

    @Override
    public void incrementTime() {

        int temp = toMinutes();
        temp++;

        parseTime(temp);
    }

    private int toMinutes() {

        return minutes + hours * 60;
    }

    private void parseTime(int minutes) {

        this.hours = minutes / 60;
        this.minutes = minutes % 60;
    }

    @Override
    public String toString() {

        return hours + ":" + minutes;
    }
}

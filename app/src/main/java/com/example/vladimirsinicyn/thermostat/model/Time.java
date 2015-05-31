package com.example.vladimirsinicyn.thermostat.model;

import java.io.Serializable;

public class Time implements Serializable,  TimeInterface{

    private final int minutesInDay = 60 * 24;
    private int minutes;
    private int hours;

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

    public void decrementTime() throws Exception {

        int temp = toMinutes();

        temp--;
        if (temp < 0) {
            throw new Exception("Time must be positive value.");
        }
        parseTime(temp);
    }

    public int toMinutes() {

        return minutes + hours * 60;
    }

    private void parseTime(int minutes) {

        minutes = minutes % minutesInDay;
        this.hours = minutes / 60;
        this.minutes = minutes % 60;
    }

    @Override
    public String toString() {

        String h = hours + "";
        if (hours < 10) {
            h = "0" + h;
        }

        String m = minutes + "";
        if (minutes < 10) {
            m = "0" + m;
        }

        return h + ":" + m;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {

        return minutes;
    }

    public boolean equals(Time time) {

        return minutes == time.getMinutes() && hours == time.getHours();
    }

    public boolean isGreater(Time time) {

        return hours > time.hours || (hours == time.hours && minutes > time.minutes);
    }
}

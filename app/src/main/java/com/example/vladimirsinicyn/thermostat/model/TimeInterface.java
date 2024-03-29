package com.example.vladimirsinicyn.thermostat.model;

//interface for class com.example.vladimirsinicyn.thermostat.model.Time

public interface TimeInterface {

    //increment minutes, simply minutes++
    void incrementTime();

    //return true if hours == 0 and minutes == 0
    boolean checkMidnight();

    //return time in string like "12:00"
    String toString();
}



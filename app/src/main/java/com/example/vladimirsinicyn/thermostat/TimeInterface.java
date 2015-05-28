package com.example.vladimirsinicyn.thermostat;

//interface for class sinitsynPackage.Time

public interface TimeInterface {

    //increment minutes, simply minutes++
    void incrementTime();

    //return true if hours == 0 and minutes == 0
    boolean checkMidnight();

    //return time in string like "12:00"
    String toString();
}



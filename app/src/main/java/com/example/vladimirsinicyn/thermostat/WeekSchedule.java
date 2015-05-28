package com.example.vladimirsinicyn.thermostat;

import java.io.Serializable;
import java.util.ArrayList;

public class WeekSchedule implements Serializable {

    private Temperature nightTemperature;
    private Temperature dayTemperature;

    private ArrayList<DaySchedule> schedule;

    public WeekSchedule() {

        schedule = new ArrayList<DaySchedule>();
    }

    public void setNightTemperature(Temperature temp) {

        nightTemperature = temp;
    }

    public void setDayTemperature(Temperature temp) {

        dayTemperature = temp;
    }

    public Temperature getNightTemperature() {
        return nightTemperature;
    }

    public Temperature getDayTemperature() {
        return dayTemperature;
    }

    public void setDaySchedule(int index, DaySchedule daySchedule) {

        schedule.add(index, daySchedule);
    }

    public DaySchedule getDaySchedule(int index) {

        return schedule.get(index);
    }

    public
 }

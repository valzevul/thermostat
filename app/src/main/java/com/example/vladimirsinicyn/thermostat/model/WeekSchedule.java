package com.example.vladimirsinicyn.thermostat.model;

import java.io.Serializable;
import java.util.ArrayList;

public class WeekSchedule implements Serializable {

    private Temperature nightTemperature;
    private Temperature dayTemperature;

    // default morning and evening changes!
    public static final TemperatureChange firstDefaultTemperatureChange =
            new TemperatureChange(LightCondition.DAY, new Time(480));       // 8:00
    public static final TemperatureChange lastDefaultTemperatureChange =
            new TemperatureChange(LightCondition.NIGHT, new Time(1380));    // 23:00

    // midnight change
    public static final TemperatureChange midnightTemperatureChange =
            new TemperatureChange(LightCondition.DAY, new Time(0));       // 00:00

    private ArrayList<DaySchedule> schedule;

    public WeekSchedule() {

        nightTemperature = new Temperature(19, 2);
        dayTemperature = new Temperature(22, 8);

        schedule = new ArrayList<DaySchedule>();
        
        for (int i = 0; i < 7; i++) {

            schedule.add(new DaySchedule());
        }
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
 }

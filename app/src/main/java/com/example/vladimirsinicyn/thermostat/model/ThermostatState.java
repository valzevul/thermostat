package com.example.vladimirsinicyn.thermostat.model;

import java.io.*;

public class ThermostatState implements Serializable {

    private Temperature vacationTemperature;
    private Temperature customTemperature;
    private WeekSchedule weekSchedule;

    private Temperature temperatureRoom;
    private ChangeType currentType;
    private int dayIndex;

    // only one mod is on in one moment of time
    // if both are off, so usual mod is on
    private boolean customModOn;
    private boolean vacationModOn;

    public ThermostatState() {

        weekSchedule = new WeekSchedule();
    }

// ============ GETTERS/SETTERS ============
    public Temperature getVacationTemperature() {
        return vacationTemperature;
    }

    public void setVacationTemperature(Temperature vacationTemperature) {
        this.vacationTemperature = vacationTemperature;
    }

    public Temperature getTemperatureRoom() {
        return temperatureRoom;
    }

    public void setTemperatureRoom(Temperature temperatureRoom) {
        this.temperatureRoom = temperatureRoom;
    }

    public Temperature getCustomTemperature() {
        return customTemperature;
    }

    public void setCustomTemperature(Temperature customTemperature) {
        this.customTemperature = customTemperature;
    }

    public WeekSchedule getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekSchedule weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void incrementDayIndex() {

        dayIndex++;

        if (dayIndex >= 7) {
            dayIndex = 0;
        }
    }

    public boolean isCustom() {
        return customModOn;
    }

    public void setCustom(boolean customModOn) {
        this.customModOn = customModOn;
    }

    public boolean isVacation() {
        return vacationModOn;
    }

    public void setVacation(boolean vacationModOn) {
        this.vacationModOn = vacationModOn;
    }

    // ============ END GETTERS/SETTERS ============

    public static void save(ThermostatState state, String name) throws Exception {

        FileOutputStream fos = new FileOutputStream(name + ".out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(state);
        oos.flush();
        oos.close();
    }

    public static ThermostatState load(String name) throws Exception {

        FileInputStream fis = new FileInputStream(name);
        ObjectInputStream oin = new ObjectInputStream(fis);

        return (ThermostatState) oin.readObject();
    }

    public ChangeType getCurrentType() {
        return currentType;
    }

    public void changeCurrentType() {
        if (currentType == ChangeType.DAY) {
            currentType = ChangeType.NIGHT;
        } else {
            currentType = ChangeType.DAY;
        }
    }
}

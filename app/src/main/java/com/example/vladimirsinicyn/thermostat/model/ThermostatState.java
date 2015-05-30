package com.example.vladimirsinicyn.thermostat.model;

import java.io.*;

public class ThermostatState implements Serializable {

    private Temperature vacationTemperature;
    private Temperature customTemperature;
    private WeekSchedule weekSchedule;
    private TemperatureChange lastChange; // TODO: init, handle

    private Temperature temperatureRoom; // current temperature (showed on main screen)
    private LightCondition currentLightCondition; // current day/night (showed on main screen)
    private int dayIndex; // current day (monday - sunday) index

    // only one mod is on in one moment of time
    // if both are off, so usual mod is on
    private boolean customModOn = false;
    private boolean vacationModOn = false;

    // flag: was state recently changed?
    private boolean changed;

    public ThermostatState() {
        weekSchedule = new WeekSchedule();

        temperatureRoom = new Temperature(22, 8);

        customTemperature = new Temperature(20, 5);
        vacationTemperature = new Temperature(15, 5);


//        lastChange = new TemperatureChange();
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

    public void setDayIndex(int index) {
        dayIndex = index;
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
        if (vacationModOn) {
            return;
        }
        this.customModOn = customModOn;
    }

    public boolean isVacation() {
        return vacationModOn;
    }

    public void setVacation(boolean vacationModOn) {

        if (customModOn) {
            setCustom(false);
        }
        this.vacationModOn = vacationModOn;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public LightCondition getCurrentLightCondition() {
        return currentLightCondition;
    }

    public void changeCurrentLightCondition() {
        if (currentLightCondition == LightCondition.DAY) {
            currentLightCondition = LightCondition.NIGHT;
        } else {
            currentLightCondition = LightCondition.DAY;
        }
    }

    public TemperatureChange getLastChange() {
        return lastChange;
    }

    public void setLastChange(TemperatureChange lastChange) {
        this.lastChange = lastChange;
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

        ThermostatState state = (ThermostatState) oin.readObject();
        // TODO: INIT LAST CHANGE according to current TIME
        // that is: find nearest previous TemperatureChange of current day

        return state;
    }


}

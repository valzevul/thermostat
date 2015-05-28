package com.example.vladimirsinicyn.thermostat;

import java.io.*;

public class ThermostatState implements Serializable {

    private Temperature vacationTemperature;
    private Temperature temperatureRoom;
    private WeekSchedule weekSchedule;
    private ChangeType currentType;

    public ThermostatState() {


    }


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

    public WeekSchedule getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekSchedule weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

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

    public void setCurrentType(ChangeType currentType) {
        this.currentType = currentType;
    }
}

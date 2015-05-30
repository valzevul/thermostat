package com.example.vladimirsinicyn.thermostat.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DaySchedule implements Serializable {

    private ArrayList<TemperatureChange> changes;

    private int dayChanges = 0;
    private int nightChanges = 0;
    public DaySchedule() {

        changes = new ArrayList<TemperatureChange>();
        changes.add(WeekSchedule.firstDefaultTemperatureChange); // 08:00
        changes.add(WeekSchedule.lastDefaultTemperatureChange);  // 23:00
    }

    public void addChange(TemperatureChange change) throws Exception {

        if (find(change.getTime())!= null) {

            throw new Exception("Similar times mustn't present.");
        }

        if (change.getTargetCondition() == LightCondition.DAY) {

            dayChanges++;
        } else {
            nightChanges++;
        }

        if (dayChanges == 6) {
            dayChanges--;
            throw new Exception("Too many day changes.");
        }

        if (nightChanges == 6) {
            nightChanges--;
            throw new Exception("Too many night changes.");
        }

        changes.add(change);
    }

    public void deleteTimeChange(TemperatureChange change) throws Exception {

        if (change.getTargetCondition() == LightCondition.DAY) {
            dayChanges--;
        } else {
            nightChanges--;
        }

        for (int i = 0; i < changes.size(); i++) {
            if (changes.get(i).equals(change)) {

                changes.remove(changes.get(i));
                break;
            }
        }
    }

    public TemperatureChange find(Time time) {

        for (int i = 0; i < changes.size(); i++) {
            if (time.equals(changes.get(i).getTime())) {
                return changes.get(i);
            }
        }

        return null;
    }

    public void print() {

        for (int i = 0; i < changes.size(); i++) {
            System.out.println("light condition: " + changes.get(i).getTargetCondition() + ", time: " + changes.get(i).getTime().toString());
        }
    }

    public ArrayList<TemperatureChange> getChanges() {

        return changes;
    }
}

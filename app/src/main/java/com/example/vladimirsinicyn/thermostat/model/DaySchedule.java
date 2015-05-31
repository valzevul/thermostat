package com.example.vladimirsinicyn.thermostat.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DaySchedule implements Serializable {

    private ArrayList<TemperatureChange> changes;

    public static int MAX_TODAY = 5;
    public static int MAX_TONIGHT = 5;

    private int dayChanges = 0;
    private int nightChanges = 0;
    public DaySchedule() {

        changes = new ArrayList<TemperatureChange>(MAX_TODAY + MAX_TONIGHT);
        dayChanges++;
        nightChanges++;
        changes.add(WeekSchedule.firstDefaultTemperatureChange);
        changes.add(WeekSchedule.lastDefaultTemperatureChange);
    }
    
    public void clear() {

        changes = new ArrayList<TemperatureChange>(MAX_TODAY + MAX_TONIGHT);
    }
    
    public TemperatureChange findClosest(Time time) {

        int min = Integer.MAX_VALUE;
        int compareTime = time.toMinutes();
        TemperatureChange closest = null;

        for (int i = 0; i < changes.size(); i++) {

            int tempValue = changes.get(i).getTime().toMinutes() - compareTime;
            if (tempValue >= 0 && tempValue < min) {

                closest = changes.get(i);
                min = tempValue;
            }
        }

        return closest;
    }
    
    public TemperatureChange findClosestLess(Time time) {

        int min = Integer.MAX_VALUE;
        int compareTime = time.toMinutes();
        TemperatureChange closest = null;

        for (int i = 0; i < changes.size(); i++) {

            int tempValue = changes.get(i).getTime().toMinutes() - compareTime;
            if (tempValue < 0 && -tempValue < min) {

                closest = changes.get(i);
                min = -tempValue;
            }
        }

        if (closest == null) {
            return WeekSchedule.midnightTemperatureChange.clone();
        } else {
            return closest;
        }
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

        if (dayChanges == MAX_TODAY + 1) {
            dayChanges--;
            throw new Exception("Too many day changes.");
        }

        if (nightChanges == MAX_TONIGHT + 1) {
            nightChanges--;
            throw new Exception("Too many night changes.");
        }

        changes.add(change);
    }

    public void deleteTimeChange(TemperatureChange change) throws Exception {

        for (int i = 0; i < changes.size(); i++) {
            if (changes.get(i).equals(change)) {

                changes.remove(changes.get(i));
                if (change.getTargetCondition() == LightCondition.DAY) {
                    dayChanges--;
                } else {
                    nightChanges--;
                }
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

    public TemperatureChange getLatestChange() {
        if (changes.isEmpty()) {
            TemperatureChange midnight = WeekSchedule.midnightTemperatureChange.clone();
            return midnight;
        } else {
            return changes.get(changes.size() - 1);
        }
    }

    public int getNumberOfChanges() {
        return changes.size();
    }

    public TemperatureChange getChange(int index) {
        return changes.get(index);
    }
}

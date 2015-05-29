package com.example.vladimirsinicyn.thermostat.model;

import java.io.Serializable;

public class TemperatureChange implements Serializable {

    private LightCondition targetCondition;
    private Time time;

    public TemperatureChange(LightCondition targetCondition, Time time) {

        this.targetCondition = targetCondition;
        this.time = time;
    }

    public LightCondition getTargetCondition() {

        return targetCondition;
    }

    public Time getTime() {

        return time;
    }

    public boolean equals(TemperatureChange change) {

        return time.equals(change.getTime()) && targetCondition == change.getTargetCondition();
    }
}

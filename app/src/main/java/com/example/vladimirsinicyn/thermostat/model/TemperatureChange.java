package com.example.vladimirsinicyn.thermostat.model;

import java.io.Serializable;

public class TemperatureChange implements Serializable {

    private ChangeType type;
    private Time time;

    public TemperatureChange(ChangeType type, Time time) {

        this.type = type;
        this.time = time;
    }

    public ChangeType getType() {

        return type;
    }

    public Time getTime() {

        return time;
    }

    public boolean equals(TemperatureChange change) {

        return time.equals(change.getTime()) && type == change.getType();
    }
}

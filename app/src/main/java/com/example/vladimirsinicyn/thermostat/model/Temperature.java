package com.example.vladimirsinicyn.thermostat.model;


import com.example.vladimirsinicyn.thermostat.ThermostatApp;

import java.io.Serializable;

public class Temperature implements Serializable {

    private int integerPart;
    private int fractionalPart;

    public Temperature() {
        integerPart = 0;
        fractionalPart = 0;
    }

    public Temperature(int integer, int fractional) throws IllegalArgumentException {

        if (fractional >= 10 || fractional < 0) {
            throw new IllegalArgumentException("Temperature(int integer, int fractional): Incorrect fractional" +
                    " part of the temperature.");
        }

        if (integer < 0) {
            throw new IllegalArgumentException("Temperature(int integer, int fractional): Incorrect integer" +
                    " part of the temperature.");
        }

        this.integerPart = integer;
        this.fractionalPart = fractional;
    }

    public void incrementTemperature() throws Exception {

        if ((integerPart == ThermostatApp.MAX_TEMP) && fractionalPart == 0) {
            throw new Exception();
        }

        fractionalPart++;
        if (fractionalPart == 10) {

            integerPart++;
            fractionalPart = 0;
        }
    }

    public void decrementTemperature() throws Exception {

        if ((integerPart == ThermostatApp.MIN_TEMP) && fractionalPart == 0) {
            throw new Exception();
        }

        fractionalPart--;
        if (fractionalPart == -1) {

            integerPart--;
            fractionalPart = 9;
        }
    }

    public String toString() {

        return integerPart + "." + fractionalPart;
    }

    public int getIntegerPart() {

        return integerPart;
    }

    public int getFractionalPart() {

        return fractionalPart;
    }
}

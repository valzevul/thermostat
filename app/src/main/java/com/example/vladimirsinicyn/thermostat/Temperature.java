package sinitsynPackage;


import java.io.Serializable;

public class Temperature implements Serializable {

    private int integerPart;
    private int fractionalPart;

    public Temperature() {
        integerPart = 0;
        fractionalPart = 0;
    }

    public Temperature(int integer, int fractional) {

        this.integerPart = integer;
        this.fractionalPart = fractional;
    }

    public void incrementTemperature() {

        fractionalPart++;
        if (fractionalPart == 10) {

            integerPart++;
            fractionalPart = 0;
        }
    }

    public void decrementTemperature() {

        fractionalPart--;
        if (fractionalPart == -1) {

            integerPart--;
            fractionalPart = 0;
        }
    }
}

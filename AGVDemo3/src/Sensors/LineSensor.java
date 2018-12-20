package Sensors;

import TI.BoeBot;

public class LineSensor {
    private int pin;

    public LineSensor(int pin) {
        this.pin = pin;
    }

    public int readValue() {
        return BoeBot.analogRead(pin);
    }
}

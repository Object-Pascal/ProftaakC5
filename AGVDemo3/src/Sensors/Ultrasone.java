package Sensors;

import TI.BoeBot;

public class Ultrasone {
    private int pinIn;
    private int pinOut;

    public Ultrasone(int pinIn, int pinOut) {
        this.pinIn = pinIn;
        this.pinOut = pinOut;
    }
    public int echoLocation() {
        BoeBot.digitalWrite(pinOut, true);
        BoeBot.wait(0, 800);
        BoeBot.digitalWrite(pinOut, false);

        int value = (BoeBot.pulseIn(pinIn, true, 10000) / 58);
        return value == 0 ? 40 : value;
    }
}

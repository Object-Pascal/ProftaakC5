package Sensors;

import TI.BoeBot;

public class Infrared {
    private boolean onOff;
    private int pin;

    public Infrared(int pin) {
        this.pin = pin;
        onOff = true;
    }

    public int getSignal() {
        int initialPulse = BoeBot.pulseIn(this.pin, false, 6000);
        if (initialPulse >= 2000) {
            int lengths[] = new int[12];
            for (int i = 0; i < 12; i++)
                lengths[i] = BoeBot.pulseIn(this.pin, false, 6000);

            int code = 0;
            for (int i = 0; i < 12; i++)
                if (lengths[i] > 700)
                    code |= 1 << i;
            if (code > 0 && code < 800) {
                return code;
            }
        }
        return 999;
    }

    public void turnOnOff() {
        this.onOff = !onOff;
    }

    public boolean getOnOff() {
        return this.onOff;
    }
}

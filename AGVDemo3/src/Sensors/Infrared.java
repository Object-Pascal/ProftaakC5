package Sensors;

import Interfaces.InfraredUpdate;
import Interfaces.Updatable;
import TI.BoeBot;
import TI.Timer;

public class Infrared implements Updatable {

    private InfraredUpdate observer;
    private int pin;
    private boolean onOff;
    private Timer timer;


    public Infrared(InfraredUpdate observer, int pin) {
        this.observer = observer;
        this.pin = pin;
        this.onOff = false;
        this.timer = new Timer(5);
    }

    public void update() {
        observer.onInfraredUpdate(getSignal());

    }

    public boolean getOnOff() {
        //return this.onOff;
        return true;
    }

    public void turnOnOff() {
        this.onOff = !onOff;
    }

    public int getSignal() {
        if (timer.timeout()) {
            // PIN MOET VERANDERD WORDEN PER BOEBOT!
            int initialPulse = BoeBot.pulseIn(this.pin, false, 6000);
            if (initialPulse >= 2000) {
                int lengths[] = new int[12];
                for (int i = 0; i < 12; i++)
                    // PIN MOET VERANDERD WORDEN PER BOEBOT!
                    lengths[i] = BoeBot.pulseIn(this.pin, false, 6000);

                int code = 0;
                for (int i = 0; i < 12; i++)
                    if (lengths[i] > 700)
                        code |= 1 << i;
                if (code > 0 && code < 800) {
                    return code;
                }
            }
        }
        return 999;

    }
}

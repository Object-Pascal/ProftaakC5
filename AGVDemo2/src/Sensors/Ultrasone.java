package Sensors;

import Interfaces.UltrasoneUpdate;
import Interfaces.Updatable;
import TI.BoeBot;
import TI.Timer;

import java.util.Random;

public class Ultrasone implements Updatable {

    private UltrasoneUpdate observer;
    private Timer timer = new Timer(100);

    public Ultrasone(UltrasoneUpdate observer) {
        this.observer = observer;
    }

    @Override
    public void update() {
        if (timer.timeout()) {
            BoeBot.digitalWrite(1, true);
            BoeBot.wait(0, 800);
            BoeBot.digitalWrite(1, false);
            int value = BoeBot.pulseIn(2, true, 10000) / 58;
            this.observer.onUltrasoneUpdate(value == 0 ? 40 : value);
        }
    }
}

package Sensors;

import Interfaces.UltrasoneUpdate;
import Interfaces.Updatable;
import TI.BoeBot;
import TI.Timer;

import java.util.Random;

public class Ultrasone implements Updatable {

    private UltrasoneUpdate observer;
    private Timer timer = new Timer(200);

    public Ultrasone(UltrasoneUpdate observer) {
        this.observer = observer;
    }

    @Override
    public void update() {
        if (timer.timeout()) {
            BoeBot.digitalWrite(5, true);
            BoeBot.wait(0, 800);
            BoeBot.digitalWrite(5, false);
            int value = BoeBot.pulseIn(6, true, 10000) / 58;
            this.observer.onUltrasoneUpdate(value == 0 ? 40 : value);
        }
    }
}

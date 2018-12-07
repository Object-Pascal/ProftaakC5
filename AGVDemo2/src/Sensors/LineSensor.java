package Sensors;

import Interfaces.LineSensorUpdate;
import Interfaces.Updatable;
import TI.BoeBot;
import TI.Timer;

public class LineSensor implements Updatable {

    private LineSensorUpdate observer;
    private int pin;
    private Timer timer = new Timer(350);

    public LineSensor(LineSensorUpdate observer, int pin) {
        this.observer = observer;
        this.pin = pin;
    }

    public void update() {
        if (timer.timeout()) {
            int value = BoeBot.analogRead(pin);
            observer.onLineSensorUpdate(value);
        }
    }
}

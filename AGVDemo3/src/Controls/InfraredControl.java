package Controls;

import Sensors.Infrared;
import Interfaces.InfraredUpdate;
import Interfaces.Updatable;
import TI.Timer;

public class InfraredControl implements Updatable {

    private InfraredUpdate observer;
    private Infrared infrared;
    private Timer timer;


    public InfraredControl(InfraredUpdate observer, int pin) {
        this.observer = observer;
        this.infrared = new Infrared(pin);
        this.timer = new Timer(5);
    }

    public void update() {
        if (timer.timeout()) {
            observer.onInfraredUpdate(this.infrared.getSignal());
        }
    }

    public boolean getOnOff() {
        return this.infrared.getOnOff();
    }

    public void turnOnOff() {
        this.infrared.turnOnOff();
    }
}

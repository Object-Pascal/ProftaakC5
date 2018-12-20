package Controls;

import Sensors.Ultrasone;
import Interfaces.UltrasoneUpdate;
import Interfaces.Updatable;
import TI.Timer;

public class UltrasoneControl implements Updatable {

    private UltrasoneUpdate observer;
    private Ultrasone ultrasone;
    private Timer timer;

    public UltrasoneControl(UltrasoneUpdate observer) {
        this.observer = observer;
        this.timer = new Timer(100);
        this.ultrasone = new Ultrasone(6, 5);
    }

    @Override
    public void update() {
        if (timer.timeout()) {
            this.observer.onUltrasoneUpdate(ultrasone.echoLocation());
        }
    }
}

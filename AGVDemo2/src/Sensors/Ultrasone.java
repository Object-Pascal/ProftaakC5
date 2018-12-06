package Sensors;

import Interfaces.UltrasoneUpdate;
import Interfaces.Updatable;
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
            this.observer.onUltrasoneUpdate(10);
    }
}

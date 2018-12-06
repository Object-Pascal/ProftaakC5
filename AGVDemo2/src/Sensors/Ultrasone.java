package Sensors;

import Interfaces.UltrasoneUpdate;

import java.util.Random;

public class Ultrasone implements Updatable {

    // Step 3
    private UltrasoneUpdate observer = null;
    private Random random = new Random();
    private int lastValue;
    private Timer timer = new Timer(100);

    public Ultrasone() {
        this.lastValue = 0;
    }

    public Ultrasone(UltrasoneUpdate observer) {
        this();
        this.observer = observer;
    }

    @Override
    public void update() {
        if (timer.timeout()) {
            int value = random.nextInt(100);
            this.lastValue = value;
            timer.setInterval(100);
            this.observer.onUltrasoneUpdate(this.lastValue);
        }
    }

    public int getLastValue() {
        return this.lastValue;
    }
}

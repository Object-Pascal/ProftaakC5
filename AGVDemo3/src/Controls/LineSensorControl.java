package Controls;

import Sensors.LineSensor;
import Interfaces.LineSensorUpdate;
import Interfaces.Updatable;
import TI.Timer;

public class LineSensorControl implements Updatable {

    private LineSensorUpdate observer;

    private LineSensor sensorLeft;
    private LineSensor sensorRight;
    private LineSensor sensorCenter;

    private Timer timer = new Timer(10);

    public LineSensorControl(LineSensorUpdate observer, int pinRight,int pinLeft,int pinCenter) {
        this.observer = observer;
        sensorRight = new LineSensor(pinRight);
        sensorLeft = new LineSensor(pinLeft);
        sensorCenter = new LineSensor(pinCenter);
    }

    public void update() {
        if (timer.timeout()) {
            double right = (sensorRight.readValue() - 1017) / (double)596;
            double left = (sensorLeft.readValue() - 900) / (double)676;

            /*
            gekalibreerd:
            delta left: 1576 - 900 = 676.
            delta center: 1603 - 857 = 746.
            delta right: 1613 - 1017 = 596.
             */

            //System.out.println("1: " + left + " | 3: " + right);
            observer.onLineSensorUpdate(left,right);
        }
    }
}

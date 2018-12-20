package Sensors;

import Interfaces.LineSensorUpdate;
import Interfaces.Updatable;
import TI.BoeBot;
import TI.Timer;

public class LineSensorControl implements Updatable {

    private LineSensorUpdate observer;
    private int pin1;
    private int pin2;
    private int pin3;
    private Timer timer = new Timer(10);


    public LineSensorControl(LineSensorUpdate observer, int pin1,int pin2,int pin3) {
        this.observer = observer;
        this.pin1 = pin1;
        this.pin2 = pin2;
        this.pin3 = pin3;
    }

    public void update() {
        if (timer.timeout()) {
            double right = (BoeBot.analogRead(pin1)-1017)/(double)596;
            double left = ((BoeBot.analogRead(pin2)-900)/(double)676);
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

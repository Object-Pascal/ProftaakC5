package Sensors;

import Interfaces.LineSensorUpdate;
import Interfaces.Updatable;
import TI.BoeBot;
import TI.Timer;

public class LineSensor implements Updatable {

    private LineSensorUpdate observer;
    private int pin1;
    private int pin2;
    private int pin3;
    private Timer timer = new Timer(100);

    public LineSensor(LineSensorUpdate observer, int pin1,int pin2,int pin3) {
        this.observer = observer;
        this.pin1 = pin1;
        this.pin2 = pin2;
        this.pin3 = pin3;
    }

    public void update() {
        if (timer.timeout()) {
            double right = (BoeBot.analogRead(pin1)-1017)/(double)596;
            double left = ((BoeBot.analogRead(pin2)-900)/(double)676);
            boolean boost;
            if(((BoeBot.analogRead(pin3)-857) >= 500) && ((right<0.3)&&(left<0.3))){
                boost = true;
            }
            else{
                boost = false;
            }
            /*
            gekalibreerd:
            delta left: 1576 - 900 = 676.
            delta center: 1603 - 857 = 746.
            delta right: 1613 - 1017 = 596.

             */

            System.out.println("1: " + left + " | 2: " + boost + " | 3: " + right);
            //observer.onLineSensorUpdate(10);
        }
    }
}

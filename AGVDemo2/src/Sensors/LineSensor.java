package Sensors;

import Interfaces.LineSensorUpdate;
import Interfaces.Updatable;

public class LineSensor implements Updatable {

private LineSensorUpdate observer;
private int pin;

    public LineSensor(LineSensorUpdate observer,int pin){
        this.observer = observer;
        this.pin = pin;
    }

    public void update(){
        observer.onLineSensorUpdate(500);
    }
}

package Sensors;

import Interfaces.ServosUpdate;
import Interfaces.Updatable;

public class Servos implements Updatable {

private ServosUpdate observer;
private int currentSpeed;


    public Servos(ServosUpdate observer){
        this.observer = observer;
        this.currentSpeed = 0;
    }

    public void update(){
        observer.onServosUpdate(this.currentSpeed);
    }
}

package Sensors;

import Interfaces.InfraredUpdate;
import Interfaces.Updatable;

public class Infrared implements Updatable {

private InfraredUpdate observer;

    public Infrared(InfraredUpdate observer){
        this.observer = observer;
    }

    public void update(){
        observer.onInfraredUpdate(100);
    }
}

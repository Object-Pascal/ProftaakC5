import Interfaces.InfraredUpdate;
import Interfaces.UltrasoneUpdate;
import Interfaces.Updatable;
import Sensors.Infrared;
import Sensors.Servos;
import Sensors.Ultrasone;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FRANS implements UltrasoneUpdate, InfraredUpdate {

private Updatable ultrasone = new Ultrasone(this);
private Updatable servos = new Servos();
private Updatable infrared = new Infrared(this);
private List<Updatable> updatableList = new ArrayList<>();

    public FRANS(){
        this.updatableList.add(ultrasone);
        this.updatableList.add(servos);
        this.updatableList.add(infrared);
    }


    public void update(){
        for (Updatable x:updatableList) {
            x.update();
        }
    }

    @Override
    public void onUltrasoneUpdate(int value) {
        if (value <= 10) {
            System.out.println("object gespot");
        }
    }

    public void onInfraredUpdate(int value){
        if(value <= 100){
            System.out.println("input afstandsbediening werkt");
        }
    }
}

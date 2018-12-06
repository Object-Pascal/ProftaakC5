import Interfaces.*;
import Sensors.Infrared;
import Sensors.LineSensor;
import Sensors.Servos;
import Sensors.Ultrasone;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FRANS implements UltrasoneUpdate, InfraredUpdate, ServosUpdate, LineSensorUpdate {

private Updatable ultrasone = new Ultrasone(this);
private Updatable servos = new Servos(this);
private Updatable infrared = new Infrared(this);
private Updatable lineSensor1 = new LineSensor(this,0);
private Updatable lineSensor2 = new LineSensor(this,1);
private Updatable lineSensor3 = new LineSensor(this,2);

private List<Updatable> updatableList = new ArrayList<>();

    public FRANS(){
        //this.updatableList.add(ultrasone);
        //this.updatableList.add(servos);
        //this.updatableList.add(infrared);
        this.updatableList.add(lineSensor1);
        //this.updatableList.add(lineSensor2);
        //this.updatableList.add(lineSensor3);
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

    public void onServosUpdate(int value){
        System.out.println(value);
    }

    public void onLineSensorUpdate(int value){
        System.out.println(value);
    }

}

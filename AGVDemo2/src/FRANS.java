import Enums.DirectionType;
import Interfaces.*;
import Sensors.*;

import java.util.ArrayList;
import java.util.List;

public class FRANS implements UltrasoneUpdate, InfraredUpdate, ServosUpdate, LineSensorUpdate, BluetoothUpdate {

    private Updatable ultrasone = new Ultrasone(this);
    private Updatable servos = new Servos(this);
    private Updatable infrared = new Infrared(this);
    private Updatable bluetooth = new Bluetooth(this);
    private Updatable lineSensor1 = new LineSensor(this,0);
    private Updatable lineSensor2 = new LineSensor(this,1);
    private Updatable lineSensor3 = new LineSensor(this,2);

private List<Updatable> updatableList = new ArrayList<>();

    public FRANS(){
        this.updatableList.add(ultrasone);
        this.updatableList.add(servos);
        //this.updatableList.add(infrared);
        this.updatableList.add(bluetooth);
        //this.updatableList.add(lineSensor1);
        //this.updatableList.add(lineSensor2);
        //this.updatableList.add(lineSensor3);
    }


    public void update(){
        for (Updatable x:updatableList) {
            x.update();
        }
    }

    public void onUltrasoneUpdate(int value) {
        if (value <= 10) {
            if (((Servos)servos).currenctDirection == DirectionType.Forward) {
                ((Servos)servos).stopBot();
                ((Servos)servos).objectDetected = true;
                System.out.println("Object detected");
            }
        }
        else if (value != 40 && value > 10) {
            ((Servos)servos).objectDetected = false;
        }
    }

    public void onInfraredUpdate(int value) {
        if(value <= 100){
            System.out.println("input afstandsbediening werkt");
        }
    }

    public void onServosUpdate(int value){
        //System.out.println(value);
    }

    public void onLineSensorUpdate(int value){
        System.out.println(value);
    }

    public void onBluetoothUpdate(int value) {
        switch (value) {
            case 119:
                ((Servos)servos).currenctDirection = DirectionType.Forward;
                System.out.println("Forward");
                ((Servos)servos).accelerate();
                break;

            case 115:
                ((Servos)servos).currenctDirection = DirectionType.Backward;
                System.out.println("Backward");
                ((Servos)servos).moveBackwards();
                break;

            case 97:
                ((Servos)servos).currenctDirection = DirectionType.Left;
                System.out.println("Left");
                ((Servos)servos).spinLeft();
                break;

            case 100:
                ((Servos)servos).currenctDirection = DirectionType.Right;
                System.out.println("Right");
                ((Servos)servos).spinRight();
                break;

            case 102:
                ((Servos)servos).currenctDirection = DirectionType.Stopped;
                System.out.println("Stop");
                ((Servos)servos).stopBot();
                break;

            default:
                //((Servos)servos).stopBot();
                break;
        }
    }
}

import Enums.DirectionType;
import Interfaces.*;
import Sensors.*;
import TI.BoeBot;
import com.sun.xml.internal.ws.developer.SerializationFeature;

import java.util.ArrayList;
import java.util.List;

public class FRANS implements UltrasoneUpdate, InfraredUpdate, ServosUpdate, LineSensorUpdate, BluetoothUpdate {

    private Updatable ultrasone = new Ultrasone(this);
    private Updatable servos = new Servos(this);
    private Updatable infrared = new Infrared(this,15);
    private Updatable bluetooth = new Bluetooth(this);
    private Updatable  linesensor = new LineSensor(this,0,1,2);

private List<Updatable> updatableList = new ArrayList<>();
private boolean on;

    public FRANS(){
        //this.updatableList.add(ultrasone);
        this.updatableList.add(servos);
        this.updatableList.add(infrared);
        this.updatableList.add(bluetooth);
        this.updatableList.add(linesensor);

    }


    public void update(){
        for (Updatable x:updatableList) {
            x.update();
        }
    }

    public void onUltrasoneUpdate(int value) {
        System.out.println(value);
        if (value <= 10) {
            if (((Servos)servos).currenctDirection == DirectionType.Forward) {
                ((Servos)servos).stopBot();
                ((Servos)servos).objectDetected = true;
                System.out.println("Object detected");
            }
        }
        else if (value != 40) {
            ((Servos)servos).objectDetected = false;
        }
    }

    public void onInfraredUpdate(int value) {
            if (value < 999) {
                if (value == 158) {
                    System.out.println("Naar voren");
                    ((Servos) servos).accelerate();
                } else if (value == 154) {
                    System.out.println("Naar rechts");
                    ((Servos) servos).spinRight();
                } else if (value == 159) {
                    System.out.println("Naar achter");
                    ((Servos) servos).moveBackwards();
                } else if (value == 155) {
                    System.out.println("Naar links");
                    ((Servos) servos).spinLeft();
                } else if (value == 149) {
                    System.out.println("Aan/uit");
                    if (on) {
                        ((Servos) servos).accelerate();
                    } else {
                        ((Servos) servos).stopBot();
                    }
                } else if (value == 128) {
                    System.out.println("Laagste snelheid");
                    ((Servos) servos).speed25();
                } else if (value == 129) {
                    System.out.println("Standaard snelheid");
                    ((Servos) servos).speed100();
                } else if (value == 130) {
                    System.out.println("Volle snelheid");
                    ((Servos) servos).maxSpeed();
                }
            }
        }


    public void onServosUpdate(int value){
        //System.out.println(value);
    }

    public void onLineSensorUpdate(double left,double right){
        if(left>0.8 && right>0.8) {
            ((Servos) servos).speedLeft(right);
            ((Servos) servos).speedRight(left);
        }
        else if(left>0.95){
            ((Servos) servos).speedLeft(0);
            return;
        }
        else if(right>0.95){
            ((Servos) servos).speedRight(0);
            return;
        }


        ((Servos) servos).speedLeft(right);
        ((Servos) servos).speedRight(left);
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

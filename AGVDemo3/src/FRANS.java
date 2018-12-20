import Enums.DirectionType;
import Interfaces.*;
import Controls.*;

import java.util.ArrayList;
import java.util.List;

public class FRANS implements UltrasoneUpdate, InfraredUpdate, ServosUpdate, LineSensorUpdate, BluetoothUpdate, LedControlUpdate {

    private Updatable ultrasone = new UltrasoneControl(this);
    private Updatable servos = new ServoControl(this);
    private Updatable infrared = new InfraredControl(this,15);
    private Updatable bluetooth = new BluetoothControl(this);
    private Updatable linesensor = new LineSensorControl(this,0,1,2);
    private Updatable ledControl = new LedControl(this);

    private List<Updatable> updatableList = new ArrayList<>();
    private boolean on = true;

    public FRANS(){
        this.updatableList.add(ultrasone);
        this.updatableList.add(servos);
        this.updatableList.add(infrared);
        this.updatableList.add(bluetooth);
        this.updatableList.add(linesensor);
    }


    public void update() {
        while (true) {
            while (on) {
                for (Updatable x : updatableList) {
                    x.update();
                }
                if (!on) {
                    updatableList.remove(linesensor);
                    break;
                }
            }
            for (Updatable x : updatableList) {
                x.update();
            }
            if (on) {
                updatableList.add(linesensor);
            }
        }
    }

    public void onUltrasoneUpdate(int value) {
        if (value <= 10) {
            ((LedControl) ledControl).alarmLights();
            if (((ServoControl)servos).currenctDirection == DirectionType.Forward) {
                ((ServoControl)servos).stopBot(true);
                ((ServoControl)servos).objectDetected = true;
                System.out.println("Object detected");
            }
        }
        else if (value != 40) {
            ((LedControl) ledControl).setLedsOff();
            ((ServoControl)servos).objectDetected = false;
        }
    }

    public void onInfraredUpdate(int value) {
        if (value < 999) {
            if (value == 158) {
                ((ServoControl)servos).currenctDirection = DirectionType.Forward;
                System.out.println("Naar voren");
                ((ServoControl) servos).accelerate();
            } else if (value == 154) {
                System.out.println("Naar rechts");
                ((ServoControl) servos).spinRight();
            } else if (value == 159) {
                ((ServoControl)servos).currenctDirection = DirectionType.Backward;
                System.out.println("Naar achter");
                ((ServoControl) servos).moveBackwards();
            } else if (value == 155) {
                System.out.println("Naar links");
                ((ServoControl) servos).spinLeft();
            } else if (value == 149) {
                System.out.println("Aan/uit");
                if (on) {
                    ((ServoControl) servos).accelerate();
                } else {
                    ((ServoControl) servos).stopBot(false);
                }
            } else if (value == 128) {
                System.out.println("Laagste snelheid");
                ((ServoControl) servos).setSpeedIncrement(25);
            } else if (value == 129) {
                System.out.println("Standaard snelheid");
                ((ServoControl) servos).setSpeedIncrement(100);
            } else if (value == 130) {
                System.out.println("Volle snelheid");
                ((ServoControl) servos).setSpeedIncrement(200);
            }
            else if(value == 137) {
                if(this.on){

                    System.out.println("linesensor off");
                    this.on = false;
                    ((ServoControl) servos).stopBot(false);
                }
                else{
                    System.out.println("linesensor on");
                    this.on = true;
                    ((ServoControl) servos).stopBot(false);
                }
            }
        }
    }

    public void onLineSensorUpdate(double left,double right) {
        ((ServoControl)servos).currenctDirection = DirectionType.Forward;
        if (!((ServoControl) servos).objectDetected) {
            if (left > 0.8 && right > 0.8) {
                ((LedControl) ledControl).intersectionBlink();
                ((ServoControl) servos).speedLeft(right);
                ((ServoControl) servos).speedRight(left);
            } else if (left > 0.95) {
                ((ServoControl) servos).speedLeft(0);
                return;
            } else if (right > 0.95) {
                ((ServoControl) servos).speedRight(0);
                return;
            }

            ((ServoControl) servos).speedLeft(right);
            ((ServoControl) servos).speedRight(left);
        }
    }

    public void onBluetoothUpdate(int value) {
        switch (value) {
            case 119:
                ((ServoControl)servos).currenctDirection = DirectionType.Forward;
                System.out.println("Forward");
                ((ServoControl)servos).accelerate();
                break;

            case 115:
                ((ServoControl)servos).currenctDirection = DirectionType.Backward;
                System.out.println("Backward");
                ((ServoControl)servos).moveBackwards();
                break;

            case 97:
                ((ServoControl)servos).currenctDirection = DirectionType.Left;
                System.out.println("Left");
                ((ServoControl)servos).spinLeft();
                break;

            case 100:
                ((ServoControl)servos).currenctDirection = DirectionType.Right;
                System.out.println("Right");
                ((ServoControl)servos).spinRight();
                break;

            case 102:
                ((ServoControl)servos).currenctDirection = DirectionType.Stopped;
                System.out.println("Stop");
                ((ServoControl)servos).stopBot(false);
                break;

            case 32:
                if(this.on) {
                    this.on = false;
                    ((ServoControl)servos).stopBot(false);
                }else{
                    this.on = true;
                    ((ServoControl)servos).stopBot(false);
                }
                break;
                
            default:
                //((Servos)servos).stopBot();
                break;
        }
    }

    public void onServosUpdate(int value){
        //System.out.println(value);
    }

    public void onLedControlUpdate(){

    }
}

import Enums.DirectionType;
import Enums.LinesensorState;
import Interfaces.*;
import Sensors.*;
import TI.BoeBot;
import TI.Timer;

import java.util.ArrayList;
import java.util.List;


public class FRANS implements UltrasoneUpdate, InfraredUpdate, ServosUpdate, LineSensorUpdate, BluetoothUpdate, LedControlUpdate {

    private Updatable ultrasone = new Ultrasone(this);
    private Updatable servos = new Servos(this);
    private Updatable infrared = new Infrared(this,15);
    private Updatable bluetooth = new Bluetooth(this);
    private Updatable linesensor = new LineSensorControl(this,0,1,2);
    private Updatable ledControl = new LedControl(this);

    private LinesensorState linesensorState;
    private Timer linesensorStateTimer = new Timer(700);


private List<Updatable> updatableList = new ArrayList<>();
private boolean on=true;

    public FRANS(){
        linesensorState = LinesensorState.Following;

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
            if (((Servos)servos).currenctDirection == DirectionType.Forward) {
                ((Servos)servos).stopBot();
                ((Servos)servos).objectDetected = true;
                System.out.println("Object detected");
            }
        }
        else if (value != 40) {
            ((LedControl) ledControl).setLedsOff();
            ((Servos)servos).objectDetected = false;
        }
    }

    public void onInfraredUpdate(int value) {
        if (value < 999) {
                if (value == 158) {
                    ((Servos)servos).currenctDirection = DirectionType.Forward;
                    System.out.println("Naar voren");
                    ((Servos) servos).accelerate();
                } else if (value == 154) {
                    System.out.println("Naar rechts");
                    ((Servos) servos).spinRight();
                } else if (value == 159) {
                    ((Servos)servos).currenctDirection = DirectionType.Backward;
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
                else if(value == 137) {
                    if(this.on){

                        System.out.println("linesensor off");
                        this.on = false;
                        ((Servos) servos).stopBot();
                    }
                    else{
                        System.out.println("linesensor on");
                        this.on = true;
                        ((Servos) servos).stopBot();                   }
                }
            }
        }


    public void onServosUpdate(int value){
        //System.out.println(value);
    }

    public void onLineSensorUpdate(double left,double right,double center) {

        if(linesensorStateTimer.timeout()){
            linesensorState = LinesensorState.Following;
        }
        ((Servos) servos).currenctDirection = DirectionType.Forward;


        if (!((Servos) servos).objectDetected && linesensorState == LinesensorState.Following) {


            if (left > 0.6 && right > 0.6) { //intersectie punt
                ((LedControl) ledControl).intersectionBlink();
                ((Servos) servos).stopBot();

                BoeBot.wait(0,100);
                ((Servos) servos).sharpTurnLeft();
               // BoeBot.wait(2,0);
                linesensorState = LinesensorState.Idle;
                linesensorStateTimer.mark();
                return;
//                 ((Servos) servos).speedLeft(right);
//                ((Servos) servos).speedRight(left);
            } else if (left > 0.92) {
                ((Servos) servos).speedLeft(0);
                return;
            } else if (right > 0.92) {
                ((Servos) servos).speedRight(0);
                return;
            }
            ((Servos) servos).speedLeft(right + 0.15);
            ((Servos) servos).speedRight(left + 0.15);
            ((LedControl) ledControl).setLedsOff();
        }
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

            case 32:
                if(this.on) {
                    this.on = false;
                }else{
                    this.on = true;
                }
                break;
                
            default:
                //((Servos)servos).stopBot();
                break;
        }
    }
    public void onLedControlUpdate(){
    }
}




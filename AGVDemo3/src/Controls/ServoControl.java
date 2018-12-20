package Controls;

import Sensors.Servo;
import Enums.DirectionType;
import Interfaces.ServosUpdate;
import Interfaces.Updatable;
import TI.BoeBot;
import TI.Timer;

public class ServoControl implements Updatable {

    private ServosUpdate observer;
    private int currentSpeed;
    private Servo leftWheel;
    private Servo rightWheel;
    private int first, second;
    public boolean objectDetected;
    public DirectionType currenctDirection;

    private Timer accelerationTimer;
    private int currentAccelerationSpeed;
    private boolean canAccelerate;


    public ServoControl(ServosUpdate observer){
        this.observer = observer;
        this.accelerationTimer = new Timer(100);

        this.currentSpeed = 0;
        this.objectDetected = false;
        this.canAccelerate = false;
        this.currenctDirection = DirectionType.Forward;

        this.leftWheel = new Servo(12);
        this.rightWheel = new Servo(13);
    }

    public void update(){
        if (canAccelerate) {
            int addedSpeed = 5;
            if (accelerationTimer.timeout()) {
                if (currentAccelerationSpeed < 200) {
                    rightWheel.setSpeed(1500 - currentAccelerationSpeed);
                    leftWheel.setSpeed(1500 + currentAccelerationSpeed);
                    currentAccelerationSpeed += addedSpeed;
                    System.out.println("tick");
                } else {
                    System.out.println("Max speed");
                    canAccelerate = false;
                }
            }
        }

        observer.onServosUpdate(this.currentSpeed);
    }

    public void stopBot(){
        leftWheel.setSpeed(1500);
        rightWheel.setSpeed(1500);
        currentAccelerationSpeed = 0;
        canAccelerate = false;
    }

    public void accelerate() {
        if (!objectDetected) {
            canAccelerate = true;
        }
    }

    public void turnLeft(int degree) {
        rightWheel.setSpeed(1300);
        leftWheel.setSpeed(1550);

        convert(degree);
        for (int i = 0; i < first * 10; i++) {
            BoeBot.wait(first / 10, second / 10);
        }
        accelerate();
    }

    public void turnRight(int degree) {
        rightWheel.setSpeed(1450);
        leftWheel.setSpeed(1700);

        convert(degree);
        for (int i = 0; i < first * 10; i++) {
            BoeBot.wait(first / 10, second / 10);
        }
        accelerate();
    }

    public void spinLeft(){
        rightWheel.setSpeed(1475);
        leftWheel.setSpeed(1475);
    }

    public void spinRight(){
        rightWheel.setSpeed(1525);
        leftWheel.setSpeed(1525);
    }

    public void moveBackwards(){
        rightWheel.setSpeed(1525);
        leftWheel.setSpeed(1475);
        currentAccelerationSpeed = 0;
        canAccelerate = false;
    }

    public void convert(int degree) {
        degree = degree * 8334;
        int first = (int) Math.floor(degree / 1000000);
        int second = degree % 1000000;
        System.out.println((int) Math.floor(degree / 1000000));
        System.out.println(second);
        this.first = first;
        this.second = second;
    }

    public void speedLeft(double indexleft){
        leftWheel.setSpeed((int)(1500+ (200*indexleft)));
    }
    public void speedRight(double indexright){
        rightWheel.setSpeed((int)(1500-(200*indexright)));
    }

    public void maxSpeed(){
        if(!objectDetected){
            leftWheel.setSpeed(1300);
            rightWheel.setSpeed(1700);
        }
    }
}
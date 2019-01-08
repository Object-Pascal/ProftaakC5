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

    private Timer movementTimer;
    private int currentAccelerationSpeed;
    private boolean canAccelerate;
    private boolean canStop;


    public ServoControl(ServosUpdate observer){
        this.observer = observer;
        this.movementTimer = new Timer(100);

        this.currentSpeed = 0;
        this.objectDetected = false;
        this.canAccelerate = false;
        this.currenctDirection = DirectionType.Forward;

        this.leftWheel = new Servo(12);
        this.rightWheel = new Servo(13);
    }

    public void update(){
        if (canAccelerate) {
            int increment = 2;
            if (movementTimer.timeout()) {
                if (currentAccelerationSpeed < 75) {
                    rightWheel.setSpeed(1500 - currentAccelerationSpeed);
                    leftWheel.setSpeed(1500 + currentAccelerationSpeed);
                    currentAccelerationSpeed += increment;
                    System.out.println("speed : " + currentAccelerationSpeed);
                } else {
                    System.out.println("Accelerated");
                    canAccelerate = false;
                }
            }
        }

        if (canStop) {
            int increment = 2;
            if (movementTimer.timeout()) {
                if (currentAccelerationSpeed > 0) {
                    if (currenctDirection == DirectionType.Forward) {
                        rightWheel.setSpeed(1500 - (currentAccelerationSpeed + increment));
                        leftWheel.setSpeed(1500 + (currentAccelerationSpeed - increment));
                    }
                    else if (currenctDirection == DirectionType.Backward) {
                        rightWheel.setSpeed(1500 + (currentAccelerationSpeed + increment));
                        leftWheel.setSpeed(1500 - (currentAccelerationSpeed - increment));
                    }
                    currentAccelerationSpeed -= increment;
                    System.out.println("speed : " + currentAccelerationSpeed);
                } else {
                    System.out.println("Stopped");
                    currentAccelerationSpeed = 0;
                    canStop = false;
                }
            }
        }

        observer.onServosUpdate(this.currentSpeed);
    }

    public void stopBot(boolean instant){
        if (instant) {
            leftWheel.setSpeed(1500);
            rightWheel.setSpeed(1500);
            currentAccelerationSpeed = 0;
        }
        else {
            if (!objectDetected && currentAccelerationSpeed > 0) {
                canAccelerate = false;
                canStop = true;
            }
        }
    }

    public void accelerate() {
        if (!objectDetected) {
            canAccelerate = true;
            canStop = false;
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

    public void setSpeedIncrement(int speed) {
        if(!objectDetected){
            leftWheel.setSpeed(1500 + speed);
            rightWheel.setSpeed(1500 - speed);
        }
    }

    public void sharpTurnLeft(){
        if(!objectDetected){
            leftWheel.setSpeed(1550);
            rightWheel.setSpeed(1550);
        }
    }
}
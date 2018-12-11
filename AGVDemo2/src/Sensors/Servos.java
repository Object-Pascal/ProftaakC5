package Sensors;

import Enums.DirectionType;
import Interfaces.ServosUpdate;
import Interfaces.Updatable;
import TI.BoeBot;
import TI.Servo;
import TI.Timer;

public class Servos implements Updatable {

    private ServosUpdate observer;
    private int currentSpeed;
    private Servo leftWheel = new Servo(12);
    private Servo rightWheel = new Servo(13);
    public boolean on_off;
    private int first, second;
    public boolean objectDetected;
    public DirectionType currenctDirection;

    private Timer accelerationTimer;
    private int currentAccelerationSpeed;
    private boolean canAccelerate;


    public Servos(ServosUpdate observer){
        this.observer = observer;
        this.currentSpeed = 0;
        this.objectDetected = false;
        this.accelerationTimer = new Timer(100);
        this.canAccelerate = false;
        this.currenctDirection = DirectionType.Stopped;
    }

    public void update(){
        if (canAccelerate) {
            int addedSpeed = 5;
            if (accelerationTimer.timeout()) {
                if (currentAccelerationSpeed < 200) {
                    rightWheel.update(1500 - currentAccelerationSpeed);
                    leftWheel.update(1500 + currentAccelerationSpeed);
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

    public void startBot() {
        accelerate();
    }

    public void stopBot(){
        leftWheel.update(1500);
        rightWheel.update(1500);
        currentAccelerationSpeed = 0;
        canAccelerate = false;
    }

    public void accelerate() {
        if (!objectDetected) {
            canAccelerate = true;
        }
    }

    public void turnLeft(int degree) {
        rightWheel.update(1300);
        leftWheel.update(1550);

        convert(degree);
        for (int i = 0; i < first * 10; i++) {
            BoeBot.wait(first / 10, second / 10);
        }
        startBot();
    }

    public void turnRight(int degree) {
        rightWheel.update(1450);
        leftWheel.update(1700);

        convert(degree);
        for (int i = 0; i < first * 10; i++) {
            BoeBot.wait(first / 10, second / 10);
        }
        startBot();
    }

    public void spinLeft(){
        rightWheel.update(1475);
        leftWheel.update(1475);
    }

    public void spinRight(){
        rightWheel.update(1525);
        leftWheel.update(1525);
    }

    public void moveBackwards(){
        leftWheel.update(1525);
        rightWheel.update(1475);
    }

    public void speed25(){
        if (!objectDetected) {
            leftWheel.update(1475);
            rightWheel.update(1525);
        }
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
        leftWheel.update((int)(1500+ (200*indexleft)));
    }
    public void speedRight(double indexright){
        rightWheel.update((int)(1500-(200*indexright)));
    }
}
package Sensors;

import Enums.DirectionType;
import Interfaces.ServosUpdate;
import Interfaces.Updatable;
import TI.BoeBot;
import TI.Servo;

public class Servos implements Updatable {

    private ServosUpdate observer;
    private int currentSpeed;
    private Servo leftWheel = new Servo(12);
    private Servo rightWheel = new Servo(13);
    public boolean on_off;
    private int first, second;
    public boolean objectDetected;
    public DirectionType currenctDirection;


    public Servos(ServosUpdate observer){
        this.observer = observer;
        this.currentSpeed = 0;
        this.objectDetected = false;
        this.currenctDirection = DirectionType.Stopped;
    }

    public void update(){
        observer.onServosUpdate(this.currentSpeed);
    }

    public void startBot() {
        accelerate();
    }

    public void stopBot(){
        leftWheel.update(1500);
        rightWheel.update(1500);
    }

    public void accelerate() {
        if (!objectDetected) {
            for (int i = 0; i <= 25; i++) {
                rightWheel.update(1500 - i);
                leftWheel.update(1500 + i);
                BoeBot.wait(5);
            }
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
}
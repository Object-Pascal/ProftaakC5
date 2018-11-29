import TI.BoeBot;
import TI.Servo;
import TI.Timer;

//Sets ServoController. Will
public class ServoController {

private Servo leftWheel = new Servo(12);
private Servo rightWheel = new Servo(13);
public boolean on_off;
private int first,second;
private Ultrasone ultrasone;

    public ServoController(Ultrasone ultrasone){
        this.on_off = true;
        leftWheel.update(1500);
        rightWheel.update(1500);
        this.ultrasone = ultrasone;
    }

    public void stopBot(){
        leftWheel.update(1500);
        rightWheel.update(1500);
    }

    public void startBot() {
        accelerate();
    }

    public void turnLeft90(){
        stopBot();
        turnLeft(90);
    }

    public void turnRight90() {
        stopBot();
        turnRight(90);
    }

    public void moveBackwards(){
        rightWheel.update(1700);
        leftWheel.update(1300);
    }

    public void accelerate() {
        for (int i = 0; i <= 200; i++) {
            rightWheel.update(1500 - i);
            leftWheel.update(1500 + i);
            BoeBot.wait(5);
            }
        }


    public void convert(int degree) {
        degree = degree * 16667;
        int first = (int) Math.floor(degree / 1000000);
        int second = degree % 1000000;
        System.out.println((int) Math.floor(degree / 1000000));
        System.out.println(second);
        this.first = first;
        this.second = second;
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
        rightWheel.update(1300);
        leftWheel.update(1300);
    }

    public void spinRight(){
        rightWheel.update(1700);
        leftWheel.update(1700);
    }

    public void turnOffBot(){
        stopBot();
        this.on_off = false;
    }

    public void speed25(){
        rightWheel.update(1450);
        leftWheel.update(1550);
    }
    public void speed50(){
        rightWheel.update(1400);
        leftWheel.update(1600);
    }
    public void speed100(){
        rightWheel.update(1300);
        leftWheel.update(1700);
    }
    public void figure(){
        //First smal spin right
        spinRight();
        convert(45);
        BoeBot.wait(first,second);

        //Forward for 1 sec first line
        startBot();
        BoeBot.wait(1,0);

        //Turn left with 135 degree
        spinLeft();
        convert(135);
        BoeBot.wait(first,second);

        //Forward for 1 sec second line
        startBot();
        BoeBot.wait(1,0);

        //Turn left with 135 degree
        spinLeft();
        convert(135);
        BoeBot.wait(first,second);

        //Forward for 1 sec third line
        startBot();
        BoeBot.wait(1,0);

        //Last turn to be back on first position
        spinLeft();
        convert(135);
        BoeBot.wait(first,second);
        stopBot();
    }
    }


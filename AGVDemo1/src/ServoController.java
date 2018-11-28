import TI.BoeBot;
import TI.Servo;
import TI.Timer;

//Sets ServoController. Will
public class ServoController {

private Servo leftWheel = new Servo(12);
private Servo rightWheel = new Servo(13);
public boolean on_off;
private int first,second;

    public ServoController(){
        this.on_off = true;
        leftWheel.update(1500);
        rightWheel.update(1500);
    }

    public void stopBot(){
        leftWheel.stop();
        rightWheel.stop();
    }

    public void startBot() {
        accelerate();
    }

    public void turnLeft(){
        stopBot();
        rightWheel.update(1700);
    }

    public void turnRight() {
        stopBot();
        leftWheel.update(1700);
    }

    public void moveBackwards(){
        rightWheel.update(1700);
        leftWheel.update(1300);
    }

    public void accelerate() {
        for (int i = 0; i <= 200; i++) {
            rightWheel.update(1500 - i);
            leftWheel.update(1500 + i);
            BoeBot.wait(20);
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
            Ultrasone.echoLocation();
            BoeBot.wait(first / 10, second / 10);
        }
        startBot();
    }


    public void turnRight(int degree) {
        rightWheel.update(1450);
        leftWheel.update(1700);

        convert(degree);
        for (int i = 0; i < first * 10; i++) {
            Ultrasone.echoLocation();
            BoeBot.wait(first / 10, second / 10);
        }
        startBot();
    }
    }


import TI.BoeBot;
import TI.Servo;
import TI.Timer;

//Sets Servos. Will
public class Servos {

private Servo leftWheel = new Servo(12);
private Servo rightWheel = new Servo(13);
public boolean on_off;

    public Servos(){
        this.on_off = true;
        leftWheel.update(1500);
        rightWheel.update(1500);
    }

    public void stopBot(){
        leftWheel.stop();
        rightWheel.stop();
    }

    public void turnLeft(){
            stopBot();
            leftWheel.update(1700);
    }

    public void echoLocation() {
        BoeBot.digitalWrite(5, true);
        BoeBot.wait(0, 800);
        BoeBot.digitalWrite(5, false);
        int length = BoeBot.pulseIn(6, true, 10000) / 58;
        System.out.println(length);
        BoeBot.wait(50);
    }
}

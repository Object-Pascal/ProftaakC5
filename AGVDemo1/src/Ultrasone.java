import TI.BoeBot;
import TI.Timer;

public class Ultrasone {
private Timer ultrasoneTimer;

    public Ultrasone(Timer ultrasoneTimer){
        this.ultrasoneTimer = ultrasoneTimer;
    }

    public int echoLocation() {
            int length;
            BoeBot.digitalWrite(5, true);
            BoeBot.wait(0,800);
            BoeBot.digitalWrite(5, false);
            length = echoFilter(BoeBot.pulseIn(6, true, 10000) / 58);
            return length;
    }

    public static int echoFilter(int length){
            return  length;
        }
}

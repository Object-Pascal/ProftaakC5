import TI.BoeBot;

public class Ultrasone {
    public int echoLocation() {
        BoeBot.digitalWrite(5, true);
        BoeBot.wait(0, 800);
        BoeBot.digitalWrite(5, false);
        int length = BoeBot.pulseIn(6, true, 10000) / 58;
        BoeBot.wait(50);
        return length;
    }
}

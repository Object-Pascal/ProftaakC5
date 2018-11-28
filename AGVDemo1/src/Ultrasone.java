import TI.BoeBot;

public class Ultrasone {
    public static int echoLocation() {
        BoeBot.digitalWrite(5, true);
        BoeBot.wait(0, 800);
        BoeBot.digitalWrite(5, false);
        int length = echoFilter(BoeBot.pulseIn(6, true, 10000) / 58);
        System.out.println(length);
        return length;
    }
    public static int echoFilter(int length){
        if(length >= 40 || length == 0){
            length = 40;
        }
        return length;
    }
}

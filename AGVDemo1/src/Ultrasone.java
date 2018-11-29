import TI.BoeBot;
import TI.Timer;

public class Ultrasone {
private Timer ultrasoneTimer;

private boolean hasStopped;

    public Ultrasone(Timer ultrasoneTimer){
        this.ultrasoneTimer = ultrasoneTimer;
        this.hasStopped = false;
    }

    public void echoLocation(ServoController servoController) {
            int length;
                length = getValue();
                BoeBot.wait(1);
                if (length < 10&&!this.hasStopped && length>0) {
                    servoController.stopBot();
                    this.hasStopped=true;
                }
                if(length>= 10 && this.hasStopped){
                    servoController.startBot();
                    this.hasStopped = false;
                }
        System.out.println(length);
            }

            public int getValue(){
                BoeBot.digitalWrite(5, true);
                BoeBot.wait(0,800);
                BoeBot.digitalWrite(5, false);
                return BoeBot.pulseIn(6, true, 10000) / 58;
            }




    public static int echoFilter(int length){
            return  length;
        }
}

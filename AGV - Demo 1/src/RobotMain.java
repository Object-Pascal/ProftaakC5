import TI.BoeBot;
import TI.Timer;

public class RobotMain {

    public static void main(String[] args) {
        Timer timerServo = new Timer(1000);
        Timer timerUltrasone = new Timer(850);
        Timer timerInfrared = new Timer(1250);

        while(true){
            if(timerServo.timeout()){
                System.out.println("skrrr");
            }
        }

    }
}

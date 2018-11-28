import TI.BoeBot;
import TI.*;


public class RobotMain {

    public static void main(String[] args) {
        Timer timerServo = new Timer(1000);
        Timer timerUltrasone = new Timer(850);
        Timer timerInfrared = new Timer(1250);
        Servos servos = new Servos();

        while(true){
            servos.echoLocation();
        }

    }
}

import TI.*;
import sun.misc.SignalHandler;


public class RobotMain {

    public static void main(String[] args) {
        Timer timerUltrasone = new Timer(100);
        Timer timerInfrared = new Timer(25);
        LedControl ledControl = new LedControl();
        Ultrasone ultrasone = new Ultrasone(timerUltrasone);
        ServoController servos = new ServoController(ultrasone);
        Infrared infrared = new Infrared();
        servos.startBot();
        //system loop
        while (true) {
            BoeBot.wait(1);
            //infrared.useInfrared(servos, timerInfrared);
            if(timerUltrasone.timeout()) {
                ultrasone.echoLocation(servos);
            }
            if(timerInfrared.timeout()){
                infrared.useInfrared(servos);
            }
            }
        }


    }



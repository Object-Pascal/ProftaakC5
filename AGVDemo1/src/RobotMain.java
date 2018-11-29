import TI.*;
import sun.misc.SignalHandler;


public class RobotMain {

    public static void main(String[] args) {
        Timer timerUltrasone = new Timer(200);
        Timer timerInfrared = new Timer(25);
        LedControl ledControl = new LedControl();
        Ultrasone ultrasone = new Ultrasone(timerUltrasone);
        ServoController servos = new ServoController(ultrasone);
        Infrared infrared = new Infrared();
        ledControl.setLedsGreen();
        BoeBot.wait(750);
        servos.startBot();
        ledControl.setLedsOff();
        //system loop
        while (true) {
            BoeBot.wait(1);
            if(timerUltrasone.timeout()) {
                ultrasone.echoLocation(servos,ledControl);
            }
            if(timerInfrared.timeout()){
                infrared.useInfrared(servos,ledControl);
            }
            ledControl.setLedsOff();
            }
        }


    }



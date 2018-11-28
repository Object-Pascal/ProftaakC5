import TI.*;
import sun.misc.SignalHandler;


public class RobotMain {

    public static void main(String[] args) {
        Timer timerUltrasone = new Timer(100);
        Timer timerInfrared = new Timer(25);
        Ultrasone ultrasone = new Ultrasone(timerUltrasone);
        ServoController servos = new ServoController(ultrasone);
        LedControl ledControl = new LedControl();
        Infrared infrared = new Infrared(35);
        servos.startBot();
        //system loop
        while (true) {
            BoeBot.wait(1);
            infrared.useInfrared(servos, timerInfrared);
            if (timerUltrasone.timeout()) {
                int tempLocation = ultrasone.echoLocation();
                if (tempLocation < 10) {
                    servos.stopBot();
                    servos.turnLeft90();
                    tempLocation = ultrasone.echoLocation();
                    if(tempLocation > 10) {
                        servos.startBot();
                    }
                }
            }


        }
    }
}

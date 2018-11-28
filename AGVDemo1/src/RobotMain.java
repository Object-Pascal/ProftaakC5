import TI.*;


public class RobotMain {

    public static void main(String[] args) {
        Timer timerUltrasone = new Timer(75);
        Timer timerInfrared = new Timer(10);
        ServoController servos = new ServoController();
        Ultrasone ultrasone = new Ultrasone();
        LedControl ledControl = new LedControl();

        servos.startBot();
        while(true) {
            if (timerUltrasone.timeout()) {
                if(Ultrasone.echoLocation() < 10){
                    servos.stopBot();
                }
            }
            BoeBot.wait(1);
        }
    }
}

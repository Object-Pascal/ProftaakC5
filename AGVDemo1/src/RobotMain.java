import TI.*;


public class RobotMain {

    public static void main(String[] args) {
        Timer timerUltrasone = new Timer(75);
        Timer timerInfrared = new Timer(10);
        ServoController servos = new ServoController();
        Ultrasone ultrasone = new Ultrasone();
        LedControl ledControl = new LedControl();

        servos.startBot();

        //system loop
        while(true) {
            //on off method
            //if(useInfrared.turnOnBot && !servos.on_off){
                servos.on_off = true;
            //}
            //software loop
            while (servos.on_off) {
                if (timerUltrasone.timeout()) {
                    if (Ultrasone.echoLocation() < 10) {
                        servos.stopBot();
                    }
                }
                //anti crash delay
                BoeBot.wait(1);
            }
        }
    }
}

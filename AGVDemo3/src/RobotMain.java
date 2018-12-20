import TI.BoeBot;

public class RobotMain{

    public static void main(String[] args) {
        FRANS Frans = new FRANS();

        while(true) {
            BoeBot.wait(1);
            Frans.update();
        }
    }
}

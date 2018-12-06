import TI.BoeBot;
import Interfaces.*;
import Sensors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class RobotMain{

    public static void main(String[] args) {
        FRANS Frans = new FRANS();

        while(true) {
            BoeBot.wait(1);
            Frans.update();
        }
    }
}

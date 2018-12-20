package Sensors;

import Interfaces.LedControlUpdate;
import Interfaces.Updatable;
import TI.BoeBot;

import javax.management.timer.TimerMBean;
import java.util.Observer;
import TI.Timer;

public class LedControl implements Updatable {

private LedControlUpdate observer;
private Timer timer;

    public LedControl(LedControlUpdate observer){
      this.observer = observer;
      this.timer = new Timer(200);
    }

        public void alarmLights() {
        if(timer.timeout()) {
            for (int i = 3; i < 6; i++) {
                BoeBot.rgbSet(i, 75, 0, 0);
            }
            BoeBot.rgbShow();
        }
        }

    public void lineSensorMode() {
        if(timer.timeout()) {
            for (int i = 0; i < 3; i++) {
                BoeBot.rgbSet(i, 0, 75, 0);
            }
            BoeBot.rgbShow();
        }
    }

        public  void setLedsOff() {
            for (int i = 0; i < 6; i++) {
                BoeBot.rgbSet(i, 0, 0, 0);
            }
            BoeBot.rgbShow();
        }

        public  void setLedsGreen() {
            for (int i = 0; i < 6; i++) {
                BoeBot.rgbSet(i, 0, 50, 0);
            }
            BoeBot.rgbShow();
        }

        public  void setLedsRed() {
            for (int i = 0; i < 6; i++) {
                BoeBot.rgbSet(i, 50, 0, 0);
            }
            BoeBot.rgbShow();
        }

        public  void setLedsBlue() {
            for (int i = 0; i < 6; i++) {
                BoeBot.rgbSet(i, 125, 50, 0);
            }
            BoeBot.rgbShow();
        }

        public void setCustomColor(int R, int G, int B) {
            for (int i = 0; i < 6; i++) {
                BoeBot.rgbSet(i, R, G, B);
            }
            BoeBot.rgbShow();
        }

        public void intersectionBlink(){
            BoeBot.rgbSet(0, 0,0,50);
            BoeBot.rgbSet(1, 0,0,50);
            BoeBot.rgbSet(2, 0,0,50);
            BoeBot.rgbShow();
        }

        public void update(){

        }

}

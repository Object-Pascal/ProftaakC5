package Controls;

import Sensors.Led;
import Interfaces.LedControlUpdate;
import Interfaces.Updatable;

import TI.Timer;

public class LedControl implements Updatable {

private LedControlUpdate observer;
private Led[] leds;
private Timer timer;

    public LedControl(LedControlUpdate observer){
      this.observer = observer;
      this.timer = new Timer(200);

      this.leds = new Led[] {
          new Led(0),
          new Led(1),
          new Led(2),
          new Led(3),
          new Led(4),
          new Led(5),
      };
    }

    public void alarmLights() {
        if(timer.timeout()) {
            for (int i = 3; i < 6; i++) {
                leds[i].rgbSet(75, 0, 0);
            }
            Led.rgbShow();
        }
    }

    public void lineSensorMode() {
        if(timer.timeout()) {
            for (int i = 0; i < 3; i++) {
                leds[i].rgbSet(0, 75, 0);
            }
            Led.rgbShow();
        }
    }

    public  void setLedsOff() {
        for (int i = 0; i < 6; i++) {
            leds[i].rgbSet(0, 0, 0);
        }
        Led.rgbShow();
    }

    public  void setLedsGreen() {
        for (int i = 0; i < 6; i++) {
            leds[i].rgbSet(0, 50, 0);
        }
        Led.rgbShow();
    }

    public  void setLedsRed() {
        for (int i = 0; i < 6; i++) {
            leds[i].rgbSet(50, 0, 0);
        }
        Led.rgbShow();
    }

    public  void setLedsBlue() {
        for (int i = 0; i < 6; i++) {
            leds[i].rgbSet(125, 50, 0);
        }
        Led.rgbShow();
    }

    public void setCustomColor(int r, int g, int b) {
        for (int i = 0; i < 6; i++) {
            leds[i].rgbSet(r, g, b);
        }
        Led.rgbShow();
    }

    public void intersectionBlink(){
        leds[0].rgbSet(0, 0, 100);
        leds[1].rgbSet(0, 0, 100);
        leds[2].rgbSet(0, 0, 100);
        Led.rgbShow();
        leds[0].rgbSet(0, 0, 0);
        leds[1].rgbSet(1, 0, 0);
        leds[2].rgbSet(2, 0, 0);
        Led.rgbShow();
    }

    public void update(){

    }
}
import TI.*;

public class LedControl {

    private int green;
    private int red;
    private int blue;

    public LedControl(){
        this.green = 0;
        this.red = 0;
        this.blue = 0;
    }

    public void alarmLights(){
        for (int i = 0; i < 6; i++) {
            BoeBot.rgbSet(i,150,0,0);
        }
        BoeBot.rgbShow();
    }

    public void setLedsOff(){
        for (int i = 0; i < 6 ; i++) {
            BoeBot.rgbSet(i,0,0,0);
        }
        BoeBot.rgbShow();
    }
    public void setLedsGreen(){
        for (int i = 0; i <6 ; i++) {
            BoeBot.rgbSet(i,0,50,0);
        }
        BoeBot.rgbShow();
    }
}

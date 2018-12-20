package Sensors;

import TI.BoeBot;

public class Led {
    private int index;

    public Led(int index) {
        this.index = index;
    }

    public void rgbSet(int r, int g, int b) {
        BoeBot.rgbSet(index, r, g, b);
    }

    public static void rgbShow() {
        BoeBot.rgbShow();
    }
}

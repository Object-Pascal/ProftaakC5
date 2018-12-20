package Sensors;

public class Servo {
    private TI.Servo servo;
    private int pin;

    public Servo(int pin) {
        this.pin = pin;
        servo = new TI.Servo(pin);
    }

    public void setSpeed(int speed) {
        servo.update(speed);
    }
}

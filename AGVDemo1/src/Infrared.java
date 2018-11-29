import TI.*;

public class Infrared {

    private Timer timer;
    private boolean stopped;

    public Infrared(){
        this.stopped = false;
    }

    public int getSignal() { {
        BoeBot.wait(1);
                int initialPulse = BoeBot.pulseIn(10, false, 6000);
                if (initialPulse >= 2000) {
                    int lengths[] = new int[12];
                    for (int i = 0; i < 12; i++)
                        lengths[i] = BoeBot.pulseIn(10, false, 6000);

                    int code = 0;
                    for (int i = 0; i < 12; i++)
                        if (lengths[i] > 700)
                            code |= 1 << i;

                    if (code > 0 && code < 800) {
                        return code;
                    }
                }
            }
        return 999;
    }

    public void useInfrared(ServoController servos) {
            int pulse = getSignal();
            if (pulse == 155) {
                System.out.println("Left");
                servos.spinLeft();
            } else if (pulse == 154) {
                System.out.println("Right");
                servos.spinRight();
            } else if (pulse == 158) {
                System.out.println("Straight forward");
                servos.startBot();
            } else if (pulse == 159) {
                System.out.println("Backwards");
                servos.moveBackwards();
            } else if (pulse == 149) {
                System.out.println("On/Off");
                if(this.stopped){
                    System.out.println("start");
                    servos.startBot();
                    this.stopped = false;
                }
                else {
                    System.out.println("stop");
                    servos.stopBot();
                    this.stopped = true;
                }
            } else if (pulse == 128) {
                System.out.println("Number 1");
                servos.speed25();
            } else if (pulse == 129) {
                System.out.println("Number 2");
                servos.speed50();
            } else if (pulse == 130) {
                System.out.println("Number 3");
                servos.speed100();
            } else if (pulse == 137) {
                System.out.println("Number 0");
                ///hier moet figure nog komen!
            }
        }
    }

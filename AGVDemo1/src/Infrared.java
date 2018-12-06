import TI.*;

public class Infrared {

    private Timer timer;

    public Infrared(){
    this.timer = new Timer(50);
    }

    public int getSignal() {
        boolean checking = true;
        while (checking) {
            if (timer.timeout()) {
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
        }
        return 999;
    }

    public void useInfrared() {
        int pulse = getSignal();
        if (pulse == 155) {
            System.out.println("Left");
        } else if (pulse == 154) {
            System.out.println("Right");
        } else if (pulse == 158) {
            System.out.println("Straight forward");
        } else if (pulse == 159) {
            System.out.println("Backwards");
        } else if (pulse == 149) {
            System.out.println("On/Off");
        } else if (pulse == 128) {
            System.out.println("Number 1");
        } else if (pulse == 129) {
            System.out.println("Number 2");
        } else if (pulse == 130) {
            System.out.println("Number 3");
        } else if (pulse == 137) {
            System.out.println("Number 0");
        }
        BoeBot.wait(5);
    }

    public boolean turnOnBot(){
        int pulse = getSignal();
        if(pulse == 149){
            return true;
        }

        return false;
    }

}

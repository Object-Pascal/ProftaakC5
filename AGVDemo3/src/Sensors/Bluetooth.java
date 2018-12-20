package Sensors;

import Interfaces.BluetoothUpdate;
import Interfaces.Updatable;
import TI.SerialConnection;
import TI.Timer;

public class Bluetooth implements Updatable {
    private BluetoothUpdate observer;
    private Timer timer = new Timer(50);
    SerialConnection conn = new SerialConnection(115200);

    public Bluetooth(BluetoothUpdate observer) {
        this.observer = observer;
    }

    public void update() {
        if (timer.timeout()) {
            if (conn.available() > 0) {
                int data = conn.readByte();
                this.observer.onBluetoothUpdate(data);
            }
        }
    }
}

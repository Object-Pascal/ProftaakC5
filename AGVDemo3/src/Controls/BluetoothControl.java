package Controls;

import Sensors.Bluetooth;
import Interfaces.BluetoothUpdate;
import Interfaces.Updatable;
import TI.Timer;

public class BluetoothControl implements Updatable {
    private BluetoothUpdate observer;
    private Bluetooth bluetooth;
    private Timer timer = new Timer(50);

    public BluetoothControl(BluetoothUpdate observer) {
        this.bluetooth = new Bluetooth(115200);
        this.observer = observer;
    }

    public void update() {
        if (timer.timeout()) {
            if (this.bluetooth.connectionAvailable()) {
                this.observer.onBluetoothUpdate(this.bluetooth.getData());
            }
        }
    }
}
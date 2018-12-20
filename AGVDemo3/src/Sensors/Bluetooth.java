package Sensors;

import TI.SerialConnection;

public class Bluetooth {
    private SerialConnection conn;

    public Bluetooth(int length) {
        conn = new SerialConnection(length);
    }

    public int getData() {
        if (conn.available() > 0) {
            return conn.readByte();
        }
        return 0;
    }

    public boolean connectionAvailable() {
        return conn.available() > 0 ? true : false;
    }
}

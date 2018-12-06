package Sensors;

import Interfaces.ServosUpdate;
import Interfaces.Updatable;

public class Servos implements Updatable {

private ServosUpdate observer;


    public void update(){
        System.out.println("Servos werkt");
        
    }
}

package com.aymen.iotmotion.Treads;


import com.aymen.iotmotion.resources.Devices;

public class initDevices extends Thread {

    @Override
    public void run() {
        super.run();
        while (true){
            Devices.initDevices();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

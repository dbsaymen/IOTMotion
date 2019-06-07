package com.aymen.iotmotion.Treads;

import com.aymen.iotmotion.resources.Devices;

public class detectInactive extends Thread {


    @Override
    public void run() {
        super.run();
        while (true){
            Devices.detectInactive();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

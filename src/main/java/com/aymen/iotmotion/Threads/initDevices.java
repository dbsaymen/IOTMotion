package com.aymen.iotmotion.Threads;


import com.aymen.iotmotion.resources.database;

public class initDevices extends Thread {

    @Override
    public void run() {
        super.run();
        database.initAllDevices();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

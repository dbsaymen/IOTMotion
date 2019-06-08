package com.aymen.iotmotion.Treads;

import com.aymen.iotmotion.resources.database;

public class pushAlarms extends Thread{

    @Override
    public void run() {
        super.run();
        while(true){
            database.pushAllAlarms();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

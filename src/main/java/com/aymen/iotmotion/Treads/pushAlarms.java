package com.aymen.iotmotion.Treads;

import com.aymen.iotmotion.resources.AlarmsResource;

public class pushAlarms extends Thread{

    @Override
    public void run() {
        super.run();
        while(true){
            AlarmsResource.NotifyAllAlarms();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

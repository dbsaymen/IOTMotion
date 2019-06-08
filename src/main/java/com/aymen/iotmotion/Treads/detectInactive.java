package com.aymen.iotmotion.Treads;

import com.aymen.iotmotion.Entity.Alarm;
import com.aymen.iotmotion.resources.database;

import java.util.ArrayList;

public class detectInactive extends Thread {


    @Override
    public void run() {
        super.run();
        while (true){
            new initDevices().start();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<String> notActive=database.detectInactiveDevices();
            if(notActive.size()>0){
                for(String id:notActive){
                    database.addAlarm(new Alarm(id,"Device "+id+" Disconnectd!"));
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

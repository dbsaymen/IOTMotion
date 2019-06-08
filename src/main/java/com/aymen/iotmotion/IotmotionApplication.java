package com.aymen.iotmotion;

import com.aymen.iotmotion.Entity.MosquittoSubscriber;
import com.aymen.iotmotion.Treads.detectInactive;
import com.aymen.iotmotion.Treads.initDevices;
import com.aymen.iotmotion.Treads.pushAlarms;
import com.aymen.iotmotion.callbacks.AlarmCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IotmotionApplication  {

    public static void main(String[] args) {
        SpringApplication.run(IotmotionApplication.class, args);
        new MosquittoSubscriber("sensors", new AlarmCallback()).Subscribe();
        new initDevices().start();
        new detectInactive().start();
        //new pushAlarms().start();
    }

}

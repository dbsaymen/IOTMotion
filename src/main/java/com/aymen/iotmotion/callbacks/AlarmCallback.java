package com.aymen.iotmotion.callbacks;

import com.aymen.iotmotion.Entity.Alarm;
import com.aymen.iotmotion.Entity.Device;
import com.aymen.iotmotion.resources.database;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class AlarmCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("\tmessage recived : " + new String(mqttMessage.getPayload()));
        String[] splittedMSG = new String(mqttMessage.getPayload()).split(",");
        int detected = Integer.parseInt(splittedMSG[1]);
        String deviceId = splittedMSG[0];
        database.activateDevice(deviceId);
        if (detected!=0) {
            database.addAlarm(new Alarm(deviceId, "Movement detected!"));
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}

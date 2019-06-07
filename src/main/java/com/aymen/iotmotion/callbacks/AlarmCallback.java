package com.aymen.iotmotion.callbacks;

import com.aymen.iotmotion.Entity.Device;
import com.aymen.iotmotion.interfaces.DeviceInterface;
import com.aymen.iotmotion.resources.Devices;
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
        int status = Integer.parseInt(splittedMSG[2]);
        String DeviceId = splittedMSG[0];
        Device device= Devices.getDevicebyID(DeviceId);

        if (detected!=0) {
            device.setDetector(true);
        }else{
            device.setDetector(false);
        }
        if (status!=0) {
            device.setStatus(true);
        }else {
            device.setStatus(false);
        }
        device.notifyUsers();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}

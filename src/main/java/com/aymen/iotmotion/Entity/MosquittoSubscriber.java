package com.aymen.iotmotion.Entity;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;

public class MosquittoSubscriber implements MqttCallback {
    String topic;
    MqttCallback callback;
    int qos             = 2;
    @Value("${spring.application.serverMQTT}")
    String broker       = "tcp://34.66.160.104:4496";
    String clientId     = "SpringAPI";
    MemoryPersistence persistence = new MemoryPersistence();
    MqttClient sampleClient;

    public MosquittoSubscriber(String topic, MqttCallback callback) {
        this.topic = topic;
        this.callback=callback;
    }

    public void connect(){
        try{
            sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName("user");
            char[] pass ={'1','2','3','4'};
            connOpts.setPassword(pass);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");

        }catch(MqttException me){
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
    public void disconnect(){
        try{
            sampleClient.disconnect();
            System.out.println("Disconnected");
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }

    }


    public void Subscribe(){
        try{
            System.out.println(broker);
            connect();
            System.out.println("Subscribing...: ");
            sampleClient.setCallback(callback);
            sampleClient.subscribe(topic, qos);
            System.out.println("Subsctibed on topic: "+topic);

        }catch(MqttException me){
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost because: " + throwable);
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}

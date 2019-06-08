package com.aymen.iotmotion.Entity;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;

public class MosquittoSubscriber implements MqttCallback {
    static String topic;
    static MqttCallback callback;
    static int qos             = 2;
    @Value("${spring.application.serverMQTT}")
    static String broker       = "tcp://34.66.160.104:4496";
    static String clientId     = "SpringAPI";
    static MemoryPersistence persistence = new MemoryPersistence();
    static MqttClient sampleClient;
    public MosquittoSubscriber(){
        try {
            sampleClient=new MqttClient(broker, clientId, persistence);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public synchronized static void connect(){
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


    public synchronized static void Subscribe(String topic, MqttCallback callback){
        MosquittoSubscriber.topic = topic;
        MosquittoSubscriber.callback=callback;
        try{
            //System.out.println(broker);
            if(!sampleClient.isConnected())connect();
            //System.out.println("Subscribing...: ");
            sampleClient.setCallback(callback);

            sampleClient.subscribe(topic, qos);
            //System.out.println("Subsctibed on topic: "+topic);

        }catch(MqttException me){
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }

    public synchronized static void publish(String topic,String content){
        try{
            if(!sampleClient.isConnected()) connect();
            //System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            //System.out.println("Message published");
            //disconnect();
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

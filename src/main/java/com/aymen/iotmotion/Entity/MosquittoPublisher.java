package com.aymen.iotmotion.Entity;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;

public class MosquittoPublisher {
    String topic;
    String content;
    int qos             = 2;
    @Value("${spring.application.serverMQTT}")
    String broker       = "tcp://34.66.160.104:4496";
    String clientId     = "SpringAPI";
    MemoryPersistence persistence = new MemoryPersistence();
    MqttClient sampleClient;

    public MosquittoPublisher(String topic, String content) {
        this.topic = topic;
        this.content = content;
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


    public void publish(){
        try{
            connect();
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
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

}

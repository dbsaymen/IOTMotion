package com.aymen.iotmotion.Entity;

import org.springframework.data.annotation.Id;

import java.util.Date;


public class Alarm {

    @Id
    private int id;
    private Device device;
    private boolean status;
    private Date creationDate;
    private String Label;
    private User user;

    public Alarm(Device device,User user,String Label) {
        this.device = device;
        creationDate = new Date();
        status = true;
        this.Label=Label;
        this.user=user;
    }
    public String getDeviceID(){
        return device.getId();
    }
    public String getUserid(){
        return user.getId();
    }

    public int getId() {
        return id;
    }

    public void disactivateAlarm(){
        this.status=false;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public boolean getStatus() {
        return status;
    }

    public boolean push(){
        try {
            new MosquittoPublisher("users/"+user.getMqttChannel(),device.getId()+":"+Label+";"+id).publish();
            return true;
        }catch (Exception e){
            return false;
        }
    }


}

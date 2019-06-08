package com.aymen.iotmotion.Entity;

import org.springframework.data.annotation.Id;

import java.util.Date;


public class Alarm {

    @Id
    private int id;
    private String deviceID;
    private boolean status;
    private Date creationDate;
    private String Label;

    public Alarm(String deviceID, String Label) {
        this.deviceID = deviceID;
        creationDate = new Date();
        status = true;
        this.Label = Label;
    }

    public int getId() {
        return id;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public void disactivateAlarm() {
        this.status = false;
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

    public int getStatus() {
        if(status) return 1;
        else return 0;
    }


}

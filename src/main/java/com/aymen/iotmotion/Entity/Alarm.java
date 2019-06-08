package com.aymen.iotmotion.Entity;

import org.springframework.data.annotation.Id;

import java.util.Date;


public class Alarm {

    @Id
    private int id;
    private String deviceID;
    private boolean status;
    private String  creationDate;
    private String Label;

    public Alarm(String deviceID, String Label) {
        this.deviceID = deviceID;
        creationDate = new Date().toString();
        status = true;
        this.Label = Label;
    }

    public Alarm(int id, String deviceID, boolean status, String creationDate, String label) {
        this.id = id;
        this.deviceID = deviceID;
        this.status = status;
        this.creationDate = creationDate;
        Label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
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

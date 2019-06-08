package com.aymen.iotmotion.Entity;

import org.springframework.data.annotation.Id;



public class Device  {
    @Id
    private String id;
    private boolean Status;
    private boolean Detector;
    private String Label;


    public Device(String id, String Label) {
        this.id = id;
        this.Label = Label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public boolean getDetector() {
        return Detector;
    }

    public void setDetector(boolean detector) {
        Detector = detector;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }




}

package com.aymen.iotmotion.Entity;

import org.springframework.data.annotation.Id;

public class User   {
    @Id
    String id;
    String MqttChannel;
    private boolean timeIntervalActive = false;
    private int TimeInterval[];
    int status;

    public User(String id) {
        this.id = id;
        this.MqttChannel = generateMqttChannel();

        TimeInterval = new int[4];
        TimeInterval[0] = 0;
        TimeInterval[1] = 0;
        TimeInterval[2] = 0;
        TimeInterval[3] = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMqttChannel() {
        return MqttChannel;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int[] getTimeInterval() {
        return TimeInterval;
    }

    public void setTimeInterval(int[] timeInterval) {
        TimeInterval = timeInterval;
    }


    public boolean isTimeIntervalActive() {
        return timeIntervalActive;
    }

    public void ActivateTimeIntervalActive() {
        this.timeIntervalActive = true;
    }

    public void disctivateTimeIntervalActive() {
        this.timeIntervalActive = false;
    }

    private String generateMqttChannel() {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static boolean isHourInInterval(int target, int start, int end) {
        if (start < end) {
            if (target >= start && target <= end) {
                return true;
            } else {
                return false;
            }
        } else {
            if ((target >= start && target < 25) || (target <= end && target >= 0)) {
                return true;
            } else {
                return false;
            }
        }
    }


}

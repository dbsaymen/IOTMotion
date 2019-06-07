package com.aymen.iotmotion.resources;

import com.aymen.iotmotion.Entity.Alarm;

import java.util.ArrayList;
import java.util.Iterator;

public class AlarmsResource {
    private static ArrayList<Alarm> AlarmList = new ArrayList<Alarm>();

    public static boolean addAlarm(Alarm alarm) {
        boolean NotExist = true;
        try {
            Iterator<Alarm> it =  AlarmList.iterator();
            while (it.hasNext()) {
                Alarm e=it.next();
                if (e.getId() == alarm.getId()) {
                    NotExist = false;
                }
            }
            if (NotExist) {
                AlarmList.add(alarm);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Alarm getAlarmbyID(int id) {
        Iterator<Alarm> it =  AlarmList.iterator();
        while (it.hasNext()) {
            Alarm e=it.next();
            if (e.getId() == id) {
                return e;
            }
        }
        return new Alarm(null, null, null);
    }

    public static boolean isAlarmExistbyUserIDandDeviceID(String userid,String deviceid) {
        Iterator<Alarm> it =  AlarmList.iterator();
        while (it.hasNext()) {
            Alarm alarm=it.next();
            if (alarm.getUserid().equals(userid) && alarm.getDeviceID().equals(deviceid)) {
                return true;
            }
        }
        return false;
    }

    public static boolean removeAlarmbyID(int id) {
        boolean NotExist = true;
        int index = -1;
        try {
            Iterator<Alarm> it =  AlarmList.iterator();
            while (it.hasNext()) {
                Alarm e=it.next();
                index++;
                if (e.getId() == id) {
                    AlarmList.remove(index);
                    NotExist = false;
                }
            }
            if (NotExist) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void NotifyAllAlarms() {
        Iterator<Alarm> it =  AlarmList.iterator();
        while (it.hasNext()) {
            Alarm alarm=it.next();
            if (alarm.getStatus()) {
                alarm.push();
            } else {
                removeAlarmbyID(alarm.getId());
            }
        }
    }
}

package com.aymen.iotmotion.resources;

import com.aymen.iotmotion.Entity.Device;

import java.util.ArrayList;
import java.util.Iterator;

public class Devices {

    public static ArrayList<Device> DeviceList = new ArrayList<Device>();

    public static boolean addDevice(Device device) {
        boolean NotExist = true;
        try {

            Iterator<Device> it =  DeviceList.iterator();
            while (it.hasNext()) {
                Device e=it.next();
                if (e.getId().equals(device.getId())) {
                    NotExist = false;
                }
            }
            if (NotExist) {
                DeviceList.add(device);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Device getDevicebyID(String id) {
        Iterator<Device> it =  DeviceList.iterator();
        while (it.hasNext()) {
            Device e=it.next();
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return new Device(null, null);
    }

    public static boolean isDeviceExistbyID(String id) {
        Iterator<Device> it =  DeviceList.iterator();
        while (it.hasNext()) {
            Device e=it.next();
            if (e.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean removeDevicebyID(String id) {
        boolean NotExist = true;
        int index = -1;
        try {
            Iterator<Device> it =  DeviceList.iterator();
            while (it.hasNext()) {
                Device e=it.next();
                index++;
                if (e.getId().equals(id)) {
                    DeviceList.remove(index);
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

    public static boolean updateDevice(Device device) {
        removeDevicebyID(device.getId());
        addDevice(device);
        return true;
    }

    public static void initDevices() {
        Iterator<Device> it =  DeviceList.iterator();
        while (it.hasNext()) {
            Device d=it.next();
            d.setStatus(false);
            d.setDetector(false);
            updateDevice(d);
        }
    }

    public static void detectInactive(){
        Iterator<Device> it =  DeviceList.iterator();
        while (it.hasNext()) {
            Device d=it.next();
            if(!d.getStatus()){
                d.notifyUsers();
            }
        }
    }
}

package com.aymen.iotmotion.controller;

import com.aymen.iotmotion.Entity.Device;
import com.aymen.iotmotion.Entity.User;
import com.aymen.iotmotion.resources.database;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/alarm")
public class IotService {

    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    public String addUser(@RequestParam Map<String, String> Params) {
        if (Params.containsKey("UserID")) {
            User u1 = new User(Params.get("UserID"));
            if(database.isUserExist(Params.get("UserID"))) return "User already exist!";
            if (database.addUser(u1)) {
                return (u1.getMqttChannel());
            } else {
                return ("Error!");
            }

        } else {
            return ("Error!");
        }
    }

    @RequestMapping(value = "/remove/user", method = RequestMethod.POST)
    public String removeUser(@RequestParam Map<String, String> Params) {
        if (Params.containsKey("UserID")) {
            if (database.isUserExist(Params.get("UserID")) && database.deleteUser(Params.get("UserID"))) {
                return ("Success!");
            } else {
                return ("Error!");
            }

        } else {
            return ("Error!");
        }
    }

    @RequestMapping(value = "/add/device", method = RequestMethod.POST)
    public String addDevice(@RequestParam Map<String, String> Params) {
        if (Params.containsKey("DeviceID") && Params.containsKey("DeviceLabel")) {

            Device d1 = new Device(Params.get("DeviceID"), Params.get("DeviceLabel"));
            if(database.isDeviceExist(Params.get("DeviceID"))) return "Device already exist";
            if (database.addDevice(d1)) {
                return ("device add with success!");
            } else {
                return ("Error!");
            }

        } else {
            return ("Error!");
        }
    }

    @RequestMapping(value = "/remove/device", method = RequestMethod.POST)
    public String removeDevice(@RequestParam Map<String, String> Params) {
        if (Params.containsKey("DeviceID") && Params.containsKey("DeviceLabel")) {
            if (database.isDeviceExist(Params.get("DeviceID"))) {
                if (database.deleteDevice(Params.get("DeviceID"))) {
                    return ("device removed with success!");
                } else {
                    return ("Error!");
                }
            } else {
                return "Error!";
            }
        } else {
            return ("Error!");
        }
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public String subscribe(@RequestParam Map<String, String> Params) {
        if (Params.containsKey("UserID") && Params.containsKey("DeviceID")) {
            String userId = Params.get("UserID");
            String deviceID = Params.get("DeviceID");
            if (database.isUserExist(userId) && database.isDeviceExist(deviceID)) {
                if(database.isUserSubscribed(userId,deviceID)) return "already subscribed!";
                database.subscribeUser(userId,deviceID);
                return ("Updete with success!");
            } else {
                return ("Error!");
            }


        } else {
            return "Error!";
        }

    }

    @RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
    public String unsubscribe(@RequestParam Map<String, String> Params) {
        if (Params.containsKey("UserID") && Params.containsKey("DeviceID")) {
            String userId = Params.get("UserID");
            String deviceID = Params.get("DeviceID");
            if(database.isUserSubscribed(userId,deviceID)){
                database.unsubscribeUser(userId,deviceID);
                return ("Updete with success!");
            }else
            {
                return "Error!";
            }
        } else {
            return "Error!";
        }

    }

    @RequestMapping(value = "/disactivate", method = RequestMethod.POST)
    public String disactivate(@RequestParam Map<String, String> Params){
        if(Params.containsKey("alarmID")){
            int id = Integer.parseInt(Params.get("alarmID"));
            database.disactivateAlarm(id);
            return ("Success!");
        }
        return "Error!";
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public void initAllDevices(){
        database.initAllDevices();
    }


}

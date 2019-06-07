package com.aymen.iotmotion.controller;

import com.aymen.iotmotion.Entity.Device;
import com.aymen.iotmotion.Entity.User;
import com.aymen.iotmotion.resources.AlarmsResource;
import com.aymen.iotmotion.resources.Devices;
import com.aymen.iotmotion.resources.Users;
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
            if (!database.isUserExist(Params.get("UserID")) && database.addUser(u1)) {
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
            if (Devices.addDevice(d1)) {
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
            Device d1 = new Device(Params.get("DeviceID"), Params.get("DeviceLabel"));
            if (Devices.isDeviceExistbyID("DeviceID")) {
                if (Devices.removeDevicebyID(d1.getId())) {
                    return ("device add with success!");
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
            String DeviceID = Params.get("DeviceID");
            if (Users.isUserExistbyID(userId) && Devices.isDeviceExistbyID(DeviceID)) {
                User u = Users.getUserbyID(userId);
                Device d = Devices.getDevicebyID(DeviceID);
                d.registerUser(u);
                Devices.updateDevice(d);
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
            String DeviceID = Params.get("DeviceID");
            if(Users.isUserExistbyID(userId) && Devices.isDeviceExistbyID(DeviceID)){
                User u = Users.getUserbyID(userId);
                Device d = Devices.getDevicebyID(DeviceID);
                d.unregisterUser(u);
                Devices.updateDevice(d);
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
            AlarmsResource.removeAlarmbyID(id);
            return ("Success!");
        }
        return "Error!";
    }


}

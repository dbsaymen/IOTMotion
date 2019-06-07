package com.aymen.iotmotion.interfaces;

import com.aymen.iotmotion.Entity.User;

import java.util.ArrayList;

public interface DeviceInterface {

    public boolean registerUser(UserInterface userInterface);
    public boolean unregisterUser(User user);
    public boolean notifyUsers();

}

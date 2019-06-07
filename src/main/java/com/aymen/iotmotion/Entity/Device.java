package com.aymen.iotmotion.Entity;

import com.aymen.iotmotion.interfaces.DeviceInterface;
import com.aymen.iotmotion.interfaces.UserInterface;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Iterator;

public class Device implements DeviceInterface {
    @Id
    private String id;
    private boolean Status;
    private boolean Detector;
    private String Label;
    public ArrayList<UserInterface> SubscribedUSers = new ArrayList<UserInterface>();

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

    @Override
    public boolean registerUser(UserInterface userInterface) {
        boolean added = false;
        boolean exist = false;
        Iterator<UserInterface> it = SubscribedUSers.iterator();
        while (it.hasNext()) {
            UserInterface user = it.next();
            User CastedUser = (User) user;
            if (CastedUser.getId().equals(((User) userInterface).getId())) {
                exist = true;
            }
        }
        if (!exist) {
            SubscribedUSers.add(userInterface);
            added = true;
            System.out.println("User Registred!");
        }
        return (added);
    }

    @Override
    public boolean unregisterUser(User user) {
        int userPos = -1;
        boolean deleted = false;
        Iterator<UserInterface> it = SubscribedUSers.iterator();
        while (it.hasNext()) {
            UserInterface userInterface = it.next();
            userPos++;
            User CastedUser = (User) userInterface;
            if (CastedUser.getId().equals(user.getId())) {
                SubscribedUSers.remove(userPos);
                deleted = true;
                System.out.println("User: " + getId() + " Unregistred!");
            }
        }
        return deleted;

    }

    @Override
    public boolean notifyUsers() {
        if (!Status || Detector) {
            String Label = new String("");
            if (!Status) {
                Label = "Device is Disconnected";
            }
            if (Detector) {
                Label = "Alarm Detected";
            }
            try {

                Iterator<UserInterface> it = SubscribedUSers.iterator();
                while (it.hasNext()) {
                    UserInterface userInterface = it.next();
                    userInterface.update(this, Label);

                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }
}

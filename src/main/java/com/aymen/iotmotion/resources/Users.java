package com.aymen.iotmotion.resources;

import com.aymen.iotmotion.Entity.User;

import java.util.ArrayList;
import java.util.Iterator;

public class Users {

    private static ArrayList<User> UserList = new ArrayList<User>();

    public static boolean addUser(User user) {
        boolean NotExist = true;
        try {
            Iterator<User> it =  UserList.iterator();
            while (it.hasNext()) {
                User e=it.next();
                if (e.getId().equals(user.getId())) {
                    NotExist = false;
                }
            }
            if (NotExist) {
                UserList.add(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User getUserbyID(String id) {
        Iterator<User> it =  UserList.iterator();
        while (it.hasNext()) {
            User e=it.next();
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return new User(null);
    }

    public static boolean isUserExistbyID(String id) {
        Iterator<User> it =  UserList.iterator();
        while (it.hasNext()) {
            User e=it.next();
            if (e.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean removeUserbyID(String id) {
        boolean NotExist = true;
        int index = -1;
        try {
            Iterator<User> it =  UserList.iterator();
            while (it.hasNext()) {
                User e=it.next();
                index++;
                if (e.getId().equals(id)) {
                    UserList.remove(index);
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

}

package com.aymen.iotmotion.resources;

import com.aymen.iotmotion.Entity.Alarm;
import com.aymen.iotmotion.Entity.Device;
import com.aymen.iotmotion.Entity.User;

import java.sql.*;
import java.util.ArrayList;

public class database {
    private static Connection connection;
    private static Statement statement;
    public static boolean isConnected = false;

    public static boolean connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://us-cdbr-gcp-east-01.cleardb.net/gcp_e7a46e60c56bf8a96bf2";
            connection = DriverManager.getConnection(url, "bece612d65961c", "1c88d6b0");
            statement = connection.createStatement();
            isConnected = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean disconnect() {

        try {
            statement.close();
            connection.close();
            isConnected = false;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addUser(User user) {
        if (!isConnected) connect();
        String sql = "INSERT INTO `gcp_e7a46e60c56bf8a96bf2`.`users` (`userID`, `MqttChannel`, `timeIntervalActive`) VALUES ('" + user.getId() + "', '" + user.getMqttChannel() + "', '0');";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateUser(User user) {
        int TimeInterval[] = user.getTimeInterval();
        String isactive = "0";
        if (user.isTimeIntervalActive()) {
            isactive = "1";
        }

        String sql = "UPDATE `gcp_e7a46e60c56bf8a96bf2`.`users` SET" +
                " `MqttChannel`='" + user.getMqttChannel() + "'," +
                " `timeIntervalActive`='" + isactive + "'," +
                " `TimeInterval0`='" + TimeInterval[0] + "'," +
                " `TimeInterval1`='" + TimeInterval[1] + "'," +
                " `TimeInterval2`='" + TimeInterval[2] + "'," +
                " `TimeInterval3`='" + TimeInterval[3] + "'," +
                " `status`='" + user.getStatus() + "'" +
                " WHERE `userID`='" + user.getId() + "';";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isUserExist(String userID){
        if (!isConnected) connect();
        String sql ="SELECT * FROM `gcp_e7a46e60c56bf8a96bf2`.`users` where `userID`= '"+userID+"';";
        try {
            ResultSet rs= statement.executeQuery(sql);
            int size =0;
            if (rs != null)
            {
                rs.last();    // moves cursor to the last row
                size = rs.getRow(); // get row id
            }
            rs.close();
            if(size>0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteUser(String userID) {

        if (!isConnected) connect();
        String sql = "DELETE FROM `gcp_e7a46e60c56bf8a96bf2`.`users` WHERE `userID`='" +userID+ "';";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addDevice(Device device) {
        try {
            if (!isConnected || statement.isClosed()) connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String status = "0";
        String detector = "0";
        if (device.getStatus()) status = "1";
        if (device.getDetector()) detector = "1";

        String sql = "INSERT INTO `gcp_e7a46e60c56bf8a96bf2`.`devices`" +
                "(`DeviceID`," +
                "`status`," +
                "`detector`," +
                "`label`)" +
                "VALUES" +
                "('" + device.getId() + "','" +
                status + "','" +
                detector + "'," +
                "'" + device.getLabel() + "');";

        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;

    }

    public static boolean isDeviceExist(String deviceID){
        if (!isConnected) connect();
        String sql ="SELECT * FROM `gcp_e7a46e60c56bf8a96bf2`.`devices` where `DeviceID`= '"+deviceID+"';";
        try {
            ResultSet rs= statement.executeQuery(sql);
            int size =0;
            if (rs != null)
            {
                rs.last();    // moves cursor to the last row
                size = rs.getRow(); // get row id
            }
            rs.close();
            if(size>0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateDevice(Device device) {
        String status = "0";
        String detector = "0";
        if (device.getStatus()) status = "1";
        if (device.getDetector()) detector = "1";
        if (!isConnected) connect();
        String sql = "UPDATE `gcp_e7a46e60c56bf8a96bf2`.`devices`" +
                "SET" +
                "`status` =" + status + "," +
                "`detector` = " + detector + "," +
                "`label` =`" + device.getLabel() + "`" +
                "WHERE `DeviceID` = `" + device.getId() + "`;";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteDevice(String deviceID) {
        if (!isConnected) connect();
        String sql = "DELETE FROM `gcp_e7a46e60c56bf8a96bf2`.`devices`" +
                "WHERE `DeviceID` = '" + deviceID + "';";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean subscribeUser(String userID, String deviceID) {
        if (!isConnected) connect();
        String sql = "INSERT INTO `gcp_e7a46e60c56bf8a96bf2`.`subscribed`" +
                "(`userID`," +
                "`deviceID`)" +
                "VALUES (" +
                " '" + userID + "'," +
                "'" + deviceID
                + "');";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isUserSubscribed(String userID, String deviceID){
        if (!isConnected) connect();
        String sql ="SELECT * FROM `gcp_e7a46e60c56bf8a96bf2`.`subscribed` where `userID`= '"+userID+"' and `deviceID` = '"+deviceID+"' ;";
        try {
            ResultSet rs= statement.executeQuery(sql);
            int size =0;
            if (rs != null)
            {
                rs.last();    // moves cursor to the last row
                size = rs.getRow(); // get row id
            }
            rs.close();
            if(size>0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean unsubscribeUser(String userID, String deviceID) {
        if (!isConnected) connect();
        String sql = "DELETE FROM `gcp_e7a46e60c56bf8a96bf2`.`subscribed`" +
                "WHERE `DeviceID` = '" + deviceID + "' AND `userID` =  '" +userID + "';";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addAlarm(Alarm alarm) {
        if (!isConnected) connect();
        if(isAlarmExist(alarm.getDeviceID(),alarm.getLabel())) return false;
        String sql = "INSERT INTO `gcp_e7a46e60c56bf8a96bf2`.`alarm` (" +
                " `status`, `creationdate`, `label`, `deviceID`) " +
                "    VALUES (" +
                "'" + alarm.getStatus() + "'," +
                "'" + alarm.getCreationDate() + "'," +
                "'" + alarm.getLabel() + "'," +
                "'" + alarm.getDeviceID() + "');";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteAlarm(Alarm alarm) {
        if (!isConnected) connect();
        String sql = "DELETE FROM `gcp_e7a46e60c56bf8a96bf2`.`alarm` where `alarmID` = '" + alarm.getId() + "';";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isAlarmExist( String deviceID, String Label){
        if (!isConnected) connect();
        String sql ="SELECT * FROM `gcp_e7a46e60c56bf8a96bf2`.`alarm` where `deviceID`= '"+deviceID+"' and `label`= '"+Label+"' and `status`='1';";
        try {
            ResultSet rs= statement.executeQuery(sql);
            int size =0;
            if (rs != null)
            {
                rs.last();    // moves cursor to the last row
                size = rs.getRow(); // get row id
            }
            rs.close();
            if(size>0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean disactivateAlarm(int alarmID) {
        if (!isConnected) connect();
        String sql = "UPDATE `gcp_e7a46e60c56bf8a96bf2`.`alarm` " +
                "SET " +
                "`status` = '0'" +
                "WHERE `alarmID` = '" + alarmID + "'; ";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Device getDeviceByID(String deviceId) {
        if (!isConnected) connect();
        String sql ="SELECT * FROM `gcp_e7a46e60c56bf8a96bf2`.`devices` where `DeviceID`= '"+deviceId+"';";
        try {
            ResultSet rs= statement.executeQuery(sql);
            if (rs != null)
            {
                rs.last();    // moves cursor to the last row
                Device d1=new Device(rs.getString(0),rs.getString("label"));
                boolean status=false;
                boolean detector=false;
                if(rs.getInt(1)>0) status=true;
                if(rs.getInt(2)>0) detector=true;
                d1.setStatus(status);
                d1.setDetector(detector);
                rs.close();
                return d1;
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean activateDevice(String deviceId) {
        String status = "1";
        if (!isConnected) connect();
        String sql = "UPDATE `gcp_e7a46e60c56bf8a96bf2`.`devices`" +
                " SET " +
                "`status` = '" + status + "' " +
                "WHERE `DeviceID` = '" +deviceId + "';";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean initAllDevices() {
        String status = "0";
        if (!isConnected) connect();
        String sql = "UPDATE `gcp_e7a46e60c56bf8a96bf2`.`devices`" +
                " SET " +
                "`status` = '" + status + "' " +
                "WHERE true ;";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<String> detectInactiveDevices(){
        ArrayList<String> notActive=new ArrayList<String>();
        if (!isConnected) connect();
        String sql ="SELECT * FROM `gcp_e7a46e60c56bf8a96bf2`.`devices` where `status`= '0';";
        try {
            ResultSet rs;
            rs = statement.executeQuery(sql);

            while(rs.next()){
                notActive.add(rs.getString("DeviceID"));
            }
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return notActive;
    }

    public static void pushAllAlarms() {

    }
}

package com.aymen.iotmotion.resources;

import com.aymen.iotmotion.Entity.Alarm;
import com.aymen.iotmotion.Entity.Device;
import com.aymen.iotmotion.Entity.User;

import java.sql.*;

public class database {
    private static Connection connection;
    private static Statement statement;
    public static boolean isConnected = false;

    public static boolean connect() {

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url = "us-cdbr-gcp-east-01.cleardb.net";
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
        String sql = "INSERT INTO `gcp_e7a46e60c56bf8a96bf2`.`users` (`userID`, `MqttChannel`, `timeIntervalActive`) VALUES ('" + user.getId() + "', '" + user.getMqttChannel() + "', '');";
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

    public static boolean isUserExist(User user){
        if (!isConnected) connect();
        String sql ="SELECT * FROM `gcp_e7a46e60c56bf8a96bf2`.`users` where `userID`= '"+user.getId()+"';";
        try {
            ResultSet rs= statement.executeQuery(sql);
            ResultSetMetaData rsmd;
            rsmd = rs.getMetaData();
            int nbCols = rsmd.getColumnCount();
            if(nbCols>0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteUser(User user) {

        if (!isConnected) connect();
        String sql = "DELETE FROM `gcp_e7a46e60c56bf8a96bf2`.`users` WHERE `userID`='" + user.getId() + "';";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addDevice(Device device) {
        if (!isConnected) connect();
        String status = "0";
        String detector = "0";
        if (device.getStatus()) status = "1";
        if (device.getDetector()) detector = "1";

        String sql = "INSERT INTO `gcp_e7a46e60c56bf8a96bf2`.`devices`" +
                "(`DeviceID`," +
                "`status`," +
                "`detector`," +
                "`label`," +
                "`userID`)" +
                "VALUES" +
                "(`" + device.getId() + "`,'" +
                status + "','" +
                detector + "'," +
                "`" + device.getLabel() + "`);";

        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean isDeviceExist(Device device){
        if (!isConnected) connect();
        String sql ="SELECT * FROM `gcp_e7a46e60c56bf8a96bf2`.`devices` where `DeviceID`= '"+device.getId()+"';";
        try {
            ResultSet rs= statement.executeQuery(sql);
            ResultSetMetaData rsmd;
            rsmd = rs.getMetaData();
            int nbCols = rsmd.getColumnCount();
            if(nbCols>0)
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

    public static boolean deleteDevice(Device device) {
        if (!isConnected) connect();
        String sql = "DELETE FROM `gcp_e7a46e60c56bf8a96bf2`.`devices`" +
                "WHERE `DeviceID` = `" + device.getId() + "`;";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean subscribeUser(User user, Device device) {
        if (!isConnected) connect();
        String sql = "INSERT INTO `gcp_e7a46e60c56bf8a96bf2`.`subscribed`" +
                "(`userID`," +
                "`deviceID`)" +
                "VALUES" +
                "`" + user.getId() + "`," +
                "`" + device.getId()
                + "`);";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isUserSubscribed(User user, Device device){
        if (!isConnected) connect();
        String sql ="";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean unsubscribeUser(User user, Device device) {
        if (!isConnected) connect();
        String sql = "DELETE FROM `gcp_e7a46e60c56bf8a96bf2`.`subscribed`" +
                "WHERE `DeviceID` = `" + device.getId() + "` AND `userID` =  `" + user.getId() + "`;";
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
        String sql = "INSERT INTO `gcp_e7a46e60c56bf8a96bf2`.`alarm` (" +
                "`alarmID`, `status`, `creationdate`, `label`, `deviceID`, `userID`) " +
                "    VALUES ('" + alarm.getId() + "'," +
                "'" + alarm.getStatus() + "'," +
                "'" + alarm.getCreationDate() + "'," +
                "'" + alarm.getLabel() + "'," +
                "'" + alarm.getDeviceID() + "'," +
                "'" + alarm.getUserid() + "');";
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

    public static boolean isAlarmExist(Alarm alarm){
        if (!isConnected) connect();
        String sql ="SELECT * FROM `gcp_e7a46e60c56bf8a96bf2`.`alarm` where `alarmID`= '"+alarm.getId()+"';";
        try {
            ResultSet rs= statement.executeQuery(sql);
            ResultSetMetaData rsmd;
            rsmd = rs.getMetaData();
            int nbCols = rsmd.getColumnCount();
            if(nbCols>0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean disactivateAlarm(Alarm alarm) {
        if (!isConnected) connect();
        String sql = "UPDATE `gcp_e7a46e60c56bf8a96bf2`.`alarm`" +
                "SET" +
                "`status` = '0'" +
                "WHERE `alarmID` = '" + alarm.getId() + "'; ";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

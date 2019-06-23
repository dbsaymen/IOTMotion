# IOTMotion

A back-end API based on Spring J2EE Framework and MQTT server to manage wireless motion detection sensors with the ability to add new users and subscribe in a list of sensers using sensor_ID.
  
This API comes with an **android application** for users and and **arduino ESP866** script for sensors

# Services :

 **`localhost:8000/alarm/add/user`**

add User: passing `UserID` in Header parameters, ex:`UserID:UserID9965`

return ```MQTT_topic``` to subscribe under, ex: /ZdF59DQS421dqDs
or "Error" if user already exist.

 **`localhost:8000/alarm/remove/user`**

Remove User: passing `UserID` in Header parameters, ex:UserID:UserID9965

return ``` "Success!"``` if user is removed else `"Error!"`

 **`localhost:8000/alarm/add/device`**

add Device: passing `DeviceID` and `DeviceLabel` in Header parameters, ex:`DeviceID:Device418 \n DeviceLabel:Front Door of my House`

return ```device add with success!``` if device is removed else `"Error!"`

 **`localhost:8000/alarm/subscribe`**

Subscribe User to get Alarms from device: passing `DeviceID` and `UserID` in Header parameters, ex:`DeviceID:Device418 \n UserID:UserID9965`

return ```Update with success!``` if user is subscribed else `"already subscribed!"` if user already subscribed else `"Error!"`

 **`localhost:8000/alarm/unsubscribe`**

UnSubscribe User from getting Alarms from device: passing `DeviceID` and `UserID` in Header parameters, ex:`DeviceID:Device418 \n UserID:UserID9965`

return ```Update with success!``` if user is UnSubscribed else `"Error!"`

 **`localhost:8000/alarm/disactivate`**

Disactivate an alarm by passing `alarmID` in Header parameters, ex:`alarmID:Alarm115` the alarmID is fetched from the Mqtt topic when the alarm is activated by detecting motion by the sensor

 **`localhost:8000/alarm/push`**

Push manually all activated alarms 

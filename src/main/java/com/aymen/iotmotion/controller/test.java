package com.aymen.iotmotion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class test {

    private boolean timeIntervalActive = true;
    private int TimeInterval[];

    @RequestMapping("/")
    public String test(){
        /*TimeInterval = new int[4];
        TimeInterval[0] = 0;
        TimeInterval[1] = 0;
        TimeInterval[2] = 0;
        TimeInterval[3] = 0;
        String S="";
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                for (int k = 0; k < 25; k++) {
                    TimeInterval[0]=i;
                    TimeInterval[2]=j;
                    S+=M1(k)+"<br>";

                }
            }
        }
        return S;*/
        return "test";

    }

    /*public String M1(int h){
        if (timeIntervalActive){
            //Date d =new Date();
            int H=h;

            if(isHourInInterval(H,TimeInterval[0],TimeInterval[2])){
                return ("alarm added H="+H+"      Between: "+TimeInterval[0]+"    and: "+TimeInterval[2]);
            }else {
                return ("not in interval H="+H+"    Between: "+TimeInterval[0]+"    and: "+TimeInterval[2]);
            }
        }else{
            return "time Intervall is not active!";
        }
    }
    public static boolean isHourInInterval(int target, int start, int end) {
        if(start<end){
            if(target>=start && target<=end){
                return true;
            }else {
                return false;
            }
        }else{
            if((target>=start && target<25)||(target<=end && target>=0)){
                return true;
            }else{
                return false;
            }
        }
    }*/
}

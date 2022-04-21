package org.firstinspires.ftc.teamcode.utils;

public class Time {

    public static double initTime, startTime, endTime;
    public static double delta;

    public static void start(){
        initTime = getTime();
        startTime = getTime();
    }

    public static double getTime(){
        return Double.longBitsToDouble(System.currentTimeMillis());
    }

    public static void update(){
        endTime = getTime();
        delta = endTime - startTime;
        startTime = endTime;
    }

}

package org.firstinspires.ftc.teamcode.utils;

public class Time {

    public static double deltaTime, startTime, endTime;

    public static long initTime, currentTime;

    public static void start(){
        initTime = System.currentTimeMillis();
        startTime = getTime();
    }

    public static double getTime(){
        currentTime = System.currentTimeMillis();
        return (double)(currentTime - initTime) / 1000;
    }

    public static void update(){
        endTime = getTime();
        deltaTime = endTime - startTime;
        startTime = getTime();
    }

}

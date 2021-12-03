package org.firstinspires.ftc.teamcode.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Chassis extends SubSystem{
    private DcMotor motorFrontLeft, motorFrontRight, motorBackLeft, motorBackRight;

    public Chassis(HardwareMap map){
        //initiating parts
        motorBackLeft = map.get(DcMotor.class, "motorBackLeft");
        motorBackRight = map.get(DcMotor.class, "motorBackRight");
        motorFrontRight = map.get(DcMotor.class, "motorFrontRight");
        motorFrontLeft = map.get(DcMotor.class, "motorFrontLeft");
    }
    //chassis run method
    public void chassisRun(double xLeft, double yLeft, double xRight){
        //power for motion
        double power = Math.hypot(xLeft, yLeft);
        //angle of the direction of motion the robot will travel in
        double motionAngle = Math.atan2(-yLeft, xLeft) - Math.PI / 4;
        //velocity of the Front left motor
        double v1 = power * Math.cos(motionAngle) + xRight;
        //velocity of the Front right motor
        double v2 = power * Math.sin(motionAngle) - xRight;
        //velocity of the Back left motor
        double v3 = power * Math.sin(motionAngle) + xRight;
        //velocity of the Back right motor
        double v4 = power * Math.cos(motionAngle) - xRight;
        //setting power for each motor
        motorFrontLeft.setPower(v1);
        motorFrontRight.setPower(v2);
        motorBackLeft.setPower(v3);
        motorBackRight.setPower(v4);
    }
    public void update(){

    }
}

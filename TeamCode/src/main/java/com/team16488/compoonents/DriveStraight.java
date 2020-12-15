package com.team16488.compoonents;

import com.qualcomm.robotcore.util.Range;
import com.team16488.common.RobotMap;

public class DriveStraight {

    // Call/Create Our Hardware/Hardware Instance
    RobotMap robot;


    public DriveStraight(RobotMap OpModeRobotMap) {
        // constructor --> initialize hardware here
        this.robot = OpModeRobotMap;
    }

    //---------------------------------------------------------------
    // Command Methods
    //---------------------------------------------------------------

    // Make this usable in both Teleoperated and Autonomous(use gamepad data types,
    // don't pass in whole gamepad)
    // Tank Mode uses one stick to control each wheel.
    public void driveTank(double leftPower, double rightPower){
        // Set Motor Power
        this.robot.FrontRightMotor.setPower(rightPower);
        this.robot.RearRightMotor.setPower(rightPower);
        this.robot.FrontLeftMotor.setPower(leftPower);
        this.robot.RearLeftMotor.setPower(leftPower);
    }

    // Arcade Mode uses yAxis(traditionally left stick) to go forward, and xAxis
    // (traditionally right stick) to turn.
    public void driveArcade(double xAxis, double yAxis){
        // variables to be set to motor powers
        double leftPower;
        double rightPower;

        // use built in Range feature to limit calculations to max an min power levels
        leftPower    = Range.clip(yAxis + xAxis, -1.0, 1.0) ;
        rightPower   = Range.clip(yAxis - xAxis, -1.0, 1.0) ;

        // Set Motor Power
        this.robot.FrontRightMotor.setPower(rightPower);
        this.robot.RearRightMotor.setPower(rightPower);
        this.robot.FrontLeftMotor.setPower(leftPower);
        this.robot.RearLeftMotor.setPower(leftPower);
    }

    // stop all motion
    public void stopDrive(){
        // Set Motor Power
        this.robot.FrontRightMotor.setPower(0);
        this.robot.RearRightMotor.setPower(0);
        this.robot.FrontLeftMotor.setPower(0);
        this.robot.RearLeftMotor.setPower(0);
    }


}

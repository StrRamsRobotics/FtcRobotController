package org.firstinspires.ftc.teamcode.driving;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Main;
import org.firstinspires.ftc.teamcode.systems.System;

public class motorDrive extends System {

    private final double incrementPower = 0.1;
    private final double lerpRate = 30;
    private final double fortyFiveInRads = -Math.PI/4,
    cosine45 = Math.cos(fortyFiveInRads),
    sine45 = Math.sin(fortyFiveInRads);

    private double milliSec = (double)1000000L;

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    private double leftMotorPower = 0.0;
    private double rightMotorPower = 0.0;
    private double backLeftMotorPower = 0.0;
    private double backRightMotorPower = 0.0;

    private org.firstinspires.ftc.teamcode.Main main;

    private double delta, endTime, currentTime, leftShift, rightShift,
            shiftAngle, x1, x2, y1, y2;

    public motorDrive(HardwareMap hardwareMap, Main main)
    {
        this.main = main;

        leftMotor = hardwareMap.dcMotor.get("frontLeftDrive");
        rightMotor = hardwareMap.dcMotor.get("frontRightDrive");

        backLeftMotor = hardwareMap.dcMotor.get("backLeftDrive");
        backRightMotor = hardwareMap.dcMotor.get("backRightDrive");
        currentTime = java.lang.System.nanoTime()/milliSec;
    }

    @Override
    public void init() {

    }

    public void update() {

        // find shift angle
        y1 = -main.gamepad1.left_stick_y;
        x1 = main.gamepad1.left_stick_x;

        y2 = x1*sine45 + y1*cosine45;
        x2 = x1*cosine45 - y1*sine45;

        leftMotorPower += x2 * delta;
        rightMotorPower += x2 * delta;
        backLeftMotorPower += y2 * delta;
        backRightMotorPower += y2 * delta;

        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMotor.setPower(leftMotorPower);

        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setPower(rightMotorPower);

        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setPower(backLeftMotorPower);

        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setPower(backRightMotorPower);

        main.telemetry.addData("Left Front Motor: ", String.format("%,.2f", leftMotorPower));
        main.telemetry.addData("Right Front Motor: ", String.format("%,.2f", rightMotorPower));
        main.telemetry.addData("Left Back Motor: ", String.format("%,.2f", backLeftMotorPower));
        main.telemetry.addData("Right Back Motor: ", String.format("%,.2f", backRightMotorPower));
        main.telemetry.addData("Delta Time", String.format("%,.3f", delta));
        main.telemetry.update();

        leftMotorPower = lerp(leftMotorPower, 0.0, lerpRate);
        rightMotorPower = lerp(rightMotorPower, 0.0, lerpRate);
        backLeftMotorPower = lerp(backLeftMotorPower, 0.0, lerpRate);
        backRightMotorPower = lerp(backRightMotorPower, 0.0, lerpRate);

        // calculate delta time
        endTime = java.lang.System.nanoTime()/milliSec;
        delta = endTime - currentTime;
        currentTime = endTime;

    }

    public double lerp(double start, double stop, double rate)
    {
        return start+(stop-start)*rate;
    }

}

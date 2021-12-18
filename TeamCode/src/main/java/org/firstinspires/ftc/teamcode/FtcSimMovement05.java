package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class FtcSimMovement05 extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    Servo servo1;
    DistanceSensor color1;
    DistanceSensor distance1;

    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");

        servo1 = hardwareMap.get(Servo.class, "servo1");
        color1 = hardwareMap.get(DistanceSensor.class, "color1");
        DistanceSensor distance1 = hardwareMap.get(DistanceSensor.class, "distance1");

        waitForStart();
        doRoutine();

        while (opModeIsActive()) { }
    }

    void doRoutine() {
        forward(0.5);
        sleep(2300);
        pointTurnRight(0.5);
        sleep(800);
        forward(0.5);
        sleep(1500);
        stopMotion();
    }

    void reverse(double power) {
        forward(-power);
    }

    void forward(double power) {
        move(power, power);
    }

    void pointTurnRight(double power) {
        move(power, -power);
    }

    void swingLeft(double power, double ratio) {
        move(power * ratio, power);
    }

    void swingRight(double power, double ratio) {
        move(power, power * ratio);
    }

    void stopMotion() {
        move(0, 0);
    }

    void move(double powerLeft, double powerRight) {
        motorLeft.setPower(powerLeft);
        motorRight.setPower(powerRight);
    }
}
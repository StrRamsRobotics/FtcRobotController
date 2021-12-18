package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class FtcSimMovement08 extends LinearOpMode {
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
        // Snake zone
        swingRight(0.5, 0.4);
        sleep(1000);
        swingLeft(0.5, 0.2);
        sleep(1500);
        swingRight(0.5, 0.2);
        sleep(2850);

        // long straight
        forward(0.5);
        sleep(1500);

        // Half moon swing
        swingRight(0.6, 0.45);
        sleep(4000);
        pointTurnRight(0.5);  // slight heading correction
        sleep(350);

        // Last small turns
        forward(0.5);
        sleep(1000);

        pointTurnRight(0.5);
        sleep(825);

        forward(0.5);
        sleep(1000);

        pointTurnRight(0.5);
        sleep(825);

        forward(0.5);
        sleep(500);

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

    void pointTurnLeft(double power) {
        move(-power, power);
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

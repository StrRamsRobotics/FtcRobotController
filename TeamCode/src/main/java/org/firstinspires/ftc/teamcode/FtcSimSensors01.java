package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class FtcSimSensors01 extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    Servo servo1;
    DistanceSensor color1;
    DistanceSensor distance1;

    Color color;
    String detectedColor;
    double hue;
    int phase;


    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");

        servo1 = hardwareMap.get(Servo.class, "servo1");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        DistanceSensor distance1 = hardwareMap.get(DistanceSensor.class, "distance1");

        waitForStart();
        phase = 1;
        detectedColor = "none";

        while (opModeIsActive()) {
            telemetry.addData("Phase", phase);
            if (phase == 1)
                phase01();
            else if (phase == 2)
                phase02();
            else if (phase == 3)
                phase03();
        }
    }

    void phase01() {
        forward(0.1);

        /*
            The following snippet is required for FtcSim as of Dec 29, 2021.
            There is a discrepancy between how the sim robot and the actual
            robot (using Android Studio) creates a Color object and extracts the hue.
         */
        color = Color.RGBToHSV(color1.red(), (color1.green()), (color1.blue()));
        hue = color.r;
        // End discrepancy

        telemetry.addData("Color", color);
        telemetry.addData("Hue", hue);

        if (hue != 0) {
            stopMotion();
            // determine color
            if (hue >= 340 && hue <= 360 || hue >= 0 && hue <= 20)
                detectedColor = "red";
            else if (hue >= 220 && hue <= 260)
                detectedColor = "blue";
            phase += 1;
        }
    }

    /***
     * Rotate toward the appropriate side
     */
    void phase02() {
        double power = 0.5;
        if (detectedColor == "red")
            pointTurnRight(power);
        else
            pointTurnLeft(power);

        sleep(825);

        if (true) {
            stopMotion();
            phase += 1;
        }
    }

    /***
     * Move forward
     */
    void phase03() {
        double power = 0.5;
        forward(power);
        sleep(1200);

        if (true) {
            stopMotion();
            phase += 1;
        }
    }

    void doRoutine() {
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
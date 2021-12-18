package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/***
 * Essentially the same as challenge 1, except in reverse. I included a constant for
 * the forward speed and sleep time to keep things organized and easy to find/tweak.
 */
public class FTCSimIntro02 extends LinearOpMode {
    double FORWARD_SPEED = 0.5;
    int SLEEP_TIME = 3000;

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

        motorLeft.setPower(-FORWARD_SPEED);
        motorRight.setPower(-FORWARD_SPEED);

        sleep(SLEEP_TIME);

        motorLeft.setPower(0);
        motorRight.setPower(0);

        while (opModeIsActive()) {}
    }
}

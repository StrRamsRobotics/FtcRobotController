package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/***
 * NOT DONE
 */
public class FtcSimMovement01 extends LinearOpMode {
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
        forward(0.5);
        sleep(3000);
        stopMotion();

        while (opModeIsActive()) { }
    }

    void forward(double power) {
        move(power, power);
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

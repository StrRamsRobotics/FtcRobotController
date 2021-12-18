package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/***
 * This challenge introduces the use of methods to simplify our code.
 * My code is quite different from the solution in the tutorial video.
 *
 * Notice how all the movement methods (forward, swingRight, and stopMotion)
 * all refer to the basic "move" method. They all move, just differently
 * based on how they handle the power distributions. Forward requires both
 * motors to have the same power, where a swing turn sets one motor's power
 * significantly lower than the other.
 *
 * Can you come up with a "swingLeft" and a "reverse" method?
 *
 * See http://cmra.rec.ri.cmu.edu/products/ev3_intermediate/lesson/2-3Turning2.html
 * for more information about the different ways a robot can turn.
 */
public class FtcSimIntro03 extends LinearOpMode {
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

        forward(0.3);
        sleep(500);
        swingRight(0.5, 0.2);
        sleep(4000);
        forward(0.3);
        sleep(1000);
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
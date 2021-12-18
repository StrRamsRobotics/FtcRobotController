package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/***
 * This OpMode uses the sleep() command. Not the greatest for anything beyond beginner
 * code, but it serves as a good intro to programming a robot.
 *
 * More advanced implementation:
 * It would be best to make use of the ElapsedTime object to run the while loop and
 * break out of the while loop when your time has been reached. I was unable to import
 * this class in the FTC Sim but it could be used IRL.
 *
 * In the FTC sim, you can also make use of the
 * DistanceSensor where distance1.getDistance(DistanceUnit.CM) will give you the distance
 * to the flag (from the Robot Controller) in centimeters. If the distance < 1 cm, then
 * break out of the while loop.
 */
public class FtcSimIntro01 extends LinearOpMode {
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

        motorLeft.setPower(0.3);
        motorRight.setPower(0.3);

        sleep(3000);

        motorLeft.setPower(0);
        motorRight.setPower(0);

        while (opModeIsActive()) {
        }
    }
}

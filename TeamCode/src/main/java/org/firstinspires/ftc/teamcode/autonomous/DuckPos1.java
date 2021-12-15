package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="DuckPosLeft")
public class DuckPos1 extends LinearOpMode {

    public void runOpMode() throws InterruptedException {
        Driving driver = new Driving(hardwareMap);
        waitForStart();

        // move to the duck
        driver.move(0.2, false);
        driver.strafe(1.0, true);

        // get duck
        telemetry.addData("MS", "Definitely got the duck");
        telemetry.update();

        // go to big storage thing
        driver.move(1.0, true);

        // throw duck
        telemetry.addData("MS", "Put duck ish?");
        telemetry.update();

        // move back into duck spinner
        driver.strafe(1.0, false);
        driver.move(1.0, false);

        // spin
        telemetry.addData("MS", "Spinning ducks!");
        telemetry.update();

        // finish i guess

    }

}

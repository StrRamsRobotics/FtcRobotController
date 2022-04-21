package test.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="DuckPosLeft")
public class DuckPos1 extends LinearOpMode {

    public void runOpMode() throws InterruptedException {
        Driving driver = new Driving(hardwareMap);
        DcMotor spinner = hardwareMap.dcMotor.get("duckSpinner");
        waitForStart();

        spinner.setPower(-0.5);

        // move to the duck
        driver.setPower(1, 1, 1, 1);
        Thread.sleep(25);
        driver.resetPower();
        driver.move(38, false);

        // finish i guess

        Thread.sleep(6000);
        driver.setPower(1, 1, 1, 1);
        Thread.sleep(150);
        driver.resetPower();

        driver.move(40, false);
        driver.resetPower();
    }

}

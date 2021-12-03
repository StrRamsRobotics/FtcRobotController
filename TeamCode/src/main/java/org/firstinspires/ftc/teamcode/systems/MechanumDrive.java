package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Main;
import org.firstinspires.ftc.teamcode.driving.MacanumDrive;
import org.firstinspires.ftc.teamcode.intake.Chassis;
import org.firstinspires.ftc.teamcode.intake.TelescopingArm;

public class MechanumDrive extends System {

    private HardwareMap hardwareMap;
    private MacanumDrive robot;
    private Main main;

    double x1; // left/right
    double y1; // front/back

    double fortyFiveInRads = -Math.PI/4;
    double cosine45 = Math.cos(fortyFiveInRads);
    double sine45 = Math.sin(fortyFiveInRads);

    double x2;
    double y2;

    public MechanumDrive(HardwareMap hw, Main main)
    {
        //initiating parts
        this.hardwareMap = hw;
        this.main = main;
        this.robot = new MacanumDrive(hw);
    }

    @Override
    public void init() {
        // Send telemetry message to signify robot waiting;
        main.telemetry.addData("Say", "Hello Driver");    //
        main.telemetry.update();
    }

    @Override
    public void update() {
        y1 = -main.gamepad1.left_stick_y;
        x1  =  main.gamepad1.left_stick_x;

        //Since it's Mecanum it needs to turn 45 degrees
        y2 = x1*sine45 + y1*cosine45;
        x2 = x1*cosine45 - y1*sine45;


        // Output the safe vales to the motor drives.
        robot.frontLeftDrive.setPower(x2);
        robot.frontRightDrive.setPower(x2);

        robot.backLeftDrive.setPower(y2);
        robot.backRightDrive.setPower(y2);

        // Send telemetry message to signify robot running;
        main.telemetry.addData("x1",  "%.2f", x1);
        main.telemetry.addData("y1", "%.2f", y1);
        main.telemetry.update();
    }
}

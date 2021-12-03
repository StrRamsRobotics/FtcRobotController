package org.firstinspires.ftc.teamcode.intake;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class OpMode extends LinearOpMode {
    private Chassis chassis;
    private TelescopingArm arm;
    private Spinner spinner;

    @Override
    public void runOpMode() throws InterruptedException {
        //initiating parts
        // chassis = new Chassis(this.hardwareMap);
        arm = new TelescopingArm(this.hardwareMap);
        // spinner = new Spinner(this.hardwareMap);
        //initializ ed status
        telemetry.addData("Status: ", "Initialized");
        telemetry.update();
        //waiting for driver to start
        waitForStart();
        //robot while loop
        while(opModeIsActive()){
            actionPerformed();
            //running status
            telemetry.addData("Status", "Running");
            //arm position
            telemetry.addData("Arm Position: ", "Position: " + arm.getPos());
            telemetry.addData("Arm", "MotorPosition: " + arm.getControllerPos());
            telemetry.update();
        }
    }
    public void actionPerformed() {
        //running chassis
        // chassis.chassisRun(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        //gamepad 2 actions

        if (gamepad2.dpad_down) {
            arm.setPosThree();
        } else if (gamepad2.dpad_right) {
            arm.setPosTwo();
        } else if(gamepad2.dpad_up) {
            arm.setPosOne();
        }

        // this.spinner.spin(this.gamepad1.b);

    }
}

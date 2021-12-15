package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DuckSpinner extends System {

    private DcMotor spinner;

    public DuckSpinner(HardwareMap hw, Controller controller){
        super(hw, controller);
    }

    @Override
    public void init() {
        // get motor
        spinner = hw.dcMotor.get("duckSpinner");
        spinner.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void update() {
        // if a is pressed on gp 1, spin
        if (controller.gamepad1.a){
            spinner.setPower(0.6);
            controller.telemetry.addData("Spinning", "YES!");
            if (controller.gamepad1.right_trigger > 0) spinner.setPower(2.0);
        }else{
            spinner.setPower(0);
            controller.telemetry.addData("Spinning", "NO!");
        }
    }
}

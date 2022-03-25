package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake extends System {

    private DcMotor inputSpinner;

    public Intake(HardwareMap hw, Controller controller){
        super(hw, controller);
    }

    @Override
    public void init() {
        // get the motor
        inputSpinner = hw.dcMotor.get("inputSpinner");
        inputSpinner.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void update() {
        if (UserInput.left.y){
            inputSpinner.setPower(2);
        }else if(UserInput.left.b){
            inputSpinner.setPower(-2);
        }else{
            inputSpinner.setPower(0);
        }
        controller.telemetry.addData("Intake Power", inputSpinner.getPowerFloat());

    }

}

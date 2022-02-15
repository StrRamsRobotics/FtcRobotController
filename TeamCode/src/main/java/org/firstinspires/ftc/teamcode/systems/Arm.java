package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm extends System {

    private final String motorName = "outputController";
    private double casting = 0.0, increase = 1.0;
    private int fullRotation = 288;
    private int increment = fullRotation/2;

    private DcMotor motor;
    private int start, end, current;

    public Arm(HardwareMap map, Controller controller){
        super(map, controller);
    }

    @Override
    public void init() {
        motor = hw.dcMotor.get(motorName);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        start = motor.getCurrentPosition();
        end = start;
    }

    @Override
    public void update() {
        // check for input
        // if input, move motor to proper position
        // bottom is position 0
        if(controller.gamepad2.dpad_down){
            end = start;
        }else if(controller.gamepad2.dpad_right){
            end = start + increment;
        }else if(controller.gamepad2.dpad_up){
            end = start + fullRotation;
        }

        motor.setTargetPosition(end);
        // set motor mode
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // set motor power
        current = motor.getCurrentPosition() - start;
        // first position
        if(current > end){
            // neg - move back
            motor.setPower(-0.2);
        }else if(current > end){
            motor.setPower(0.2);
        }
    }



}

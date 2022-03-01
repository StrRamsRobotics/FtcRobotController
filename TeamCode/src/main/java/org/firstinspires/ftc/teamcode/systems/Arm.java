package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm extends System {

    private final String motorName = "outputController";
    private double casting = 0.0, increase = 1.0;
    private int fullRotation = 288;
    private int increment = fullRotation/2;

    private int spin = 0;

    private DcMotor motor;
    private int start, end, current;

    public Arm(HardwareMap map, Controller controller){
        super(map, controller);
    }

    @Override
    public void init() {
        motor = hw.dcMotor.get(motorName);
        start = motor.getCurrentPosition();
        end = start;
    }

    @Override
    public void update() {
//        // check for input
//        // if input, move motor to proper position
//        // bottom is position 0
//        if (controller.gamepad2.dpad_down) {
//            end = start;
//            updatePos();
//        } else if (controller.gamepad2.dpad_right) {
//            end = start + increment;
//            updatePos();
//        } else if (controller.gamepad2.dpad_up) {
//            end = start + fullRotation;
//            updatePos();
//        }
//        controller.telemetry.addData("Position", end);
//        controller.telemetry.addData("Arm Power", motor.getPowerFloat());
        boolean move = false;
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        if(controller.gamepad2.dpad_right){ // go up?
            motor.setPower(0.3);
            move = true;
        }else if(controller.gamepad2.dpad_up){
            motor.setPower(-0.3);
            move = true;
        }else{
            motor.setPower(0);
            move = false;
        }
        controller.telemetry.addData("Is Arm Spinning: ", move);
    }

    public void updatePos(){
//        motor.setTargetPosition(end);
//        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        // set motor power
//        current = motor.getCurrentPosition() - start;
//        // first position
//        if(current > end){
//            // neg - move back
//            motor.setPower(-1);
//        }else if(current > end){
//            motor.setPower(1);
//        }
    }
}

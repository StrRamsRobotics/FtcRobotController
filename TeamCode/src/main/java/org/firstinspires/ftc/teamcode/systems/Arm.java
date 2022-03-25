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

    private final double speed = 1.0;

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
        boolean move = false;
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        if(UserInput.left.right_trigger > 0){ // go up?
            motor.setPower(speed);
            move = true;
        }else if(UserInput.left.left_trigger > 0){
            motor.setPower(-speed / 2);
            move = true;
        }else{
            motor.setPower(0);
            move = false;
        }
        controller.telemetry.addData("Is Arm Spinning: ", move);
    }

}

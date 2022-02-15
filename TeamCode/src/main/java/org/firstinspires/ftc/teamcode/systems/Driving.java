package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Driving extends System {

    private final double FortiFiveInRads = -Math.PI/4, sin45 = Math.sin(FortiFiveInRads), cos45 = Math.cos(FortiFiveInRads);
    private DcMotor flm, frm, blm, brm;
    private double x1, x2, y1, y2;
    private boolean strafe = false;
    private boolean slomo = false, pressed = false;

    public Driving(HardwareMap hw, Controller controller){
        super(hw, controller);
    }

    public void init(){
        flm = hw.dcMotor.get("flm");
        frm = hw.dcMotor.get("frm");
        blm = hw.dcMotor.get("blm");
        brm = hw.dcMotor.get("brm");

        flm.setDirection(DcMotorSimple.Direction.FORWARD);
        frm.setDirection(DcMotorSimple.Direction.REVERSE);
        blm.setDirection(DcMotorSimple.Direction.FORWARD);
        brm.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void update() {
        // check for strafing
        if (controller.gamepad1.y && !pressed) {
            pressed = true;
            slomo = !slomo;
        } else pressed = false;
        if(controller.gamepad1.dpad_left) {
            x1 = -1;
            strafe = false;
        }else if(controller.gamepad1.dpad_right){
            x1 = 1;
            strafe = false;
        }else{
            x1 = 0;
            strafe = true;
        }
        if (strafe){
            y1 = -controller.gamepad1.left_stick_y;
            x1 = -controller.gamepad1.right_stick_x;

            y2 = x1*sin45 + y1*cos45;
            x2 = x1*cos45 - y1*sin45;

            if (slomo){
                y2 *= 0.2;
                x2 *= 0.2;
            }

            //power
            flm.setPower(y2);
            frm.setPower(x2);

            blm.setPower(y2);
            brm.setPower(x2);

            controller.telemetry.addData("x2", "%.2f", x2);
            controller.telemetry.addData("y2", "%.2f", y2);
        }else{
            y1 = -controller.gamepad1.left_stick_y;

            y2 = x1*sin45 + y1*cos45;
            x2 = x1*cos45 - y1*sin45;


            //power
            flm.setPower(x2);
            frm.setPower(-x2);

            blm.setPower(-x2);
            brm.setPower(x2);

            controller.telemetry.addData("x2", "%.2f", x2);
            controller.telemetry.addData("y2", "%.2f", y2);
        }

    }
}

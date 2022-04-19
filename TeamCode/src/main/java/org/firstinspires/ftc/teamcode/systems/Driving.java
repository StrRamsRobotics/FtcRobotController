package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Driving extends System {

    private final double FortiFiveInRads = -Math.PI/4, sin45 = Math.sin(FortiFiveInRads), cos45 = Math.cos(FortiFiveInRads);
    private DcMotor flm, frm, blm, brm;
    private double flv, frv, blv, brv;
    private double x1, x2, y1, y2, sx;
    private boolean strafe = false;
    private boolean slomo = false, pressed = false;


    public Driving(HardwareMap hw, Controller controller){
        super(hw, controller);
    }

    public void init() {
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
        if(!pressed){
            if(UserInput.left.y){
                pressed=true;
                slomo=!slomo;
            }
        }else pressed=false;

        // strafing
        y1 = UserInput.left.left_stick_y;
        x1 = -UserInput.left.left_stick_x;

        // lerp stuff
        flv = lerp(flv, 0.0, .1);
        frv = lerp(frv, 0.0, .1);
        blv = lerp(blv, 0.0, .1);
        brv = lerp(brv, 0.0, .1);
        
        if(Math.round(y1*10) == 0) return;

        y2 = (x1*sin45 + y1*cos45) * 0.2;
        x2 = (x1*cos45 - y1*sin45) * 0.2;

        // decide if its strafing
        sx = y1*10;
        if(Math.round(sx) == 0.0) {
            // calculate power
            controller.telemetry.addData("STrafe", true);
            flv -= y2;
            frv -= y2;
            blv -= x2;
            brv -= x2;
        }
        else {
            // calculate power
            flv += y2;
            frv += x2;
            blv += y2;
            brv += x2;
        }

        // set power
        flm.setPower(flv);
        frm.setPower(frv);
        blm.setPower(blv);
        brm.setPower(brv);

    }

    public double clamp(double num, double min, double max){
        if (num < min) return min;
        if (num > max) return max;
        return num;
    }

    public double lerp(double a, double b, double c){
        return a + (b-a) * c;
    }
}

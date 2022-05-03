package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Time;

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
        // strafing
        y1 = -UserInput.left.left_stick_y;
        x1 = -UserInput.left.left_stick_x;

        // rotate the movement vector by 45 degrees counter clockwise
        y2 = (x1*sin45 + y1*cos45) * Time.deltaTime * 20;
        x2 = (x1*cos45 - y1*sin45) * Time.deltaTime * 20;

        // output data
        this.controller.telemetry.addData("Is moving", String.format("front-left: %.4f | front-right: %.4f\nback-left: %.4f | back-right: %.4f", flv, frv, blv, brv));

        flv += y2; frv += x2; blv += x2; brv += y2;

        double turnval = UserInput.left.right_stick_x / 4;
        if (y1 < 0) turnval *= -1;
        if (turnval > 0){
            frv -= turnval;
            brv -= turnval;
            flv += turnval;
            blv += turnval;
        }else{
            flv += turnval;
            blv += turnval;
            frv -= turnval;
            brv -= turnval;
        }

        // lerp stuff
        flv = clamp(lerp(flv, 0.0, .15), -1, 1);
        frv = clamp(lerp(frv, 0.0, .15), -1, 1);
        blv = clamp(lerp(blv, 0.0, .15), -1, 1);
        brv = clamp(lerp(brv, 0.0, .15), -1, 1);

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

    public double getAngle(double x, double y){
        // tan(t) = y/x
        return Math.atan(y/x);
    }

    public double lerp(double a, double b, double c){
        return a + (b-a) * c;
    }
}

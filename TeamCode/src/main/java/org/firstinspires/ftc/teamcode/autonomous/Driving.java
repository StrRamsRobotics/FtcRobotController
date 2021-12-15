package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Driving {
    private final long MILLIS = 1000;
    private final double sin45 = Math.sin(Math.toRadians(45)), cos45 = Math.cos(Math.toRadians(45));

    private DcMotor flm, frm, blm, brm;
    private double x1, x2, y1, y2;

    public Driving(HardwareMap hw){
        flm = hw.dcMotor.get("flm");
        frm = hw.dcMotor.get("frm");
        blm = hw.dcMotor.get("blm");
        brm = hw.dcMotor.get("brm");
    }

    public void move(double distance, boolean front) throws InterruptedException {
        // turn to face direction first
        y1 = front ? -1 : 1;
        setPower(y1, y1, y1, y1);
        Thread.sleep(500 * MILLIS);

        // then move towards direction
    }

    public void strafe(double distance, boolean left) throws InterruptedException {
        y1 = -1;
        x1 = left ? -1 : 1;

        y2 = x1*sin45 + y1*cos45;
        x2 = x1*cos45 - y1*sin45;


        //power
        setPower(x2, -x2, -x2, x2);

        Thread.sleep(1000 * MILLIS);
        resetPower();

    }

    public void resetPower(){
        flm.setPower(0);
        frm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
    }

    public void setPower(double fl, double fr, double bl, double br){
        flm.setPower(fl);
        frm.setPower(fr);
        blm.setPower(bl);
        brm.setPower(br);
    }

}

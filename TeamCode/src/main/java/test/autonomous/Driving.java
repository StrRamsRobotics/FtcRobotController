package test.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Driving {
    private final double MILLIS = 1000;
    private final double sin45 = Math.sin(Math.toRadians(45)), cos45 = Math.cos(Math.toRadians(45));
    private final double wheelCirc = 10 * Math.PI;
    private final double rps = 1.5, rpm = 90;
    private final int bigTeeth = 120, smallTeeth = 40, gearRatio = 3;

    private DcMotor flm, frm, blm, brm;
    private double x1, x2, y1, y2;

    public Driving(HardwareMap hw){
        flm = hw.dcMotor.get("flm");
        frm = hw.dcMotor.get("frm");
        blm = hw.dcMotor.get("blm");
        brm = hw.dcMotor.get("brm");
    }

    public void move(double cm, boolean front) throws InterruptedException {
        // turn to face direction first
        y1 = front ? -0.5 : 0.5;
        double rotations = cm / wheelCirc;
        double time = rotations / rps;

        setPower(y1, -y1, y1, -y1);
        Thread.sleep((long)(MILLIS * time));
        resetPower();
    }

    public void strafe(double time, boolean left) throws InterruptedException {
        y1 = -1;
        x1 = left ? -1 : 1;

        // power
        setPower(-x1*1.3, x1, x1, -x1);

        // time needed to sleep
        Thread.sleep((long)(MILLIS * time));
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

package org.firstinspires.ftc.teamcode.main.systems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.main.Main;

public class MechanumDrive extends SubSystem{

    private final double lerpRate = 0.3;
    private final double FortyFiveInRads = -Math.PI/4,
                        sine45 = Math.sin(FortyFiveInRads),
                        cosine45 = Math.cos(FortyFiveInRads);

    private DcMotor flm, frm, blm, brm;
    private double fls, frs, bls, brs;
    private double x1, x2, y1, y2;


    public MechanumDrive()
    {
        super();
    }

    @Override
    public void init(HardwareMap hw, Main main) {
        this.hw = hw;
        this.main = main;

        // four wheel - mechanum drive
        flm = hw.dcMotor.get("frontLeftDrive");
        frm = hw.dcMotor.get("frontRightDrive");

        blm = hw.dcMotor.get("backLeftDrive");
        brm = hw.dcMotor.get("backRightDrive");

        flm.setDirection(DcMotorSimple.Direction.FORWARD);
        frm.setDirection(DcMotorSimple.Direction.REVERSE);

        blm.setDirection(DcMotorSimple.Direction.FORWARD);
        brm.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void update() {
        y1 = -main.gamepad1.left_stick_y;
        x1 = -main.gamepad1.right_stick_x;

        //Since it's Mecanum it needs to turn 45 degrees
        y2 = x1*sine45+y1*cosine45;
        x2 = x1*cosine45-y1*sine45;

//        frm.setPower(1);
//        flm.setPower(1);
//        brm.setPower(1);
//        blm.setPower(1);
//
//        if (true) return;

        y2 *= 0.5;
        x2 *= 0.5;

        // Output the safe vales to the motor drives.
        flm.setPower(y2);
        frm.setPower(x2);

        blm.setPower(y2);
        brm.setPower(x2);

        // Send telemetry message to signify robot running;
        main.telemetry.addData("x1",  "%.2f", x2);
        main.telemetry.addData("y1", "%.2f", y2);
        main.telemetry.update();

    }

    public double lerp(double start, double stop, double rate)
    {
        return start+(stop-start)*rate;
    }


}

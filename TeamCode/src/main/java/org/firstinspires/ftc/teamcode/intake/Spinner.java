package org.firstinspires.ftc.teamcode.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Spinner {

    private HardwareMap hw;
    private DcMotor spinner;

    public Spinner(HardwareMap hw)
    {
        this.hw = hw;
        this.spinner = hw.dcMotor.get("spinner");
    }

    public void spin(boolean spin)
    {
        if (spin){
            spinner.setDirection(DcMotorSimple.Direction.FORWARD);
            spinner.setPower(Double.MAX_VALUE);
        }else{
            spinner.setDirection(DcMotorSimple.Direction.FORWARD);
            spinner.setPower(-Double.MAX_VALUE);
        }
    }

}

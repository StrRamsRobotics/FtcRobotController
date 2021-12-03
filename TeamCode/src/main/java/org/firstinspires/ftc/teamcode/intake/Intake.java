package org.firstinspires.ftc.teamcode.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Main;

public class Intake extends SubSystem{
    private DcMotor intake;
    private boolean on;
    public Intake(HardwareMap map, Main main){
        on = true;
        intake = map.get(DcMotor.class, "intakeMotor");
    }
    public void switchOnOff(){
        on = !on;
    }
    public void update(){

    }
}

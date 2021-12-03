package org.firstinspires.ftc.teamcode.main.nodes;
import android.icu.util.Output;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.io.ObjectOutput;
import java.io.OutputStream;

public class TelescopingArm  {
    private DcMotor outputController;
    private int position, initPosition;
    private int counter;

    public TelescopingArm(HardwareMap map){
        outputController = map.get(DcMotor.class, "outputController");
        outputController.setTargetPosition(0);
        position = 0;
        initPosition = outputController.getCurrentPosition();
    }

    public void setPosOne()
    {
        position = 0;
        update();
    }

    public void setPosTwo(){
        position = 1440;
        update();
    }
    public void setPosThree(){
        position = 2880;
        update();
    }

    public int getPos(){
        return position;
    }

    public int getControllerPos()
    {
        return outputController.getCurrentPosition();
    }

    public void update(){
        outputController.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        outputController.setTargetPosition(position);
        if(initPosition > position) outputController.setPower(-1);
        else outputController.setPower(1);
        initPosition = position;
    }

}
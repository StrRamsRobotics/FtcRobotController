package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.main.systems.Arm;
import org.firstinspires.ftc.teamcode.main.systems.MechanumDrive;
import org.firstinspires.ftc.teamcode.main.systems.SubSystem;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="Final")
public class Main extends LinearOpMode {

    private List<SubSystem> systems;

    private double milliSec = (double)1000000L;
    private double endTime, delta, currentTime, total_passed, segment_passed;

    @Override
    public void runOpMode() throws InterruptedException {
        this.systems = new ArrayList<>();

        // add the systems
        systems.add(new MechanumDrive());
        // systems.add(new Arm());


        for(SubSystem s:systems)
            s.init(hardwareMap, this);


        telemetry.addData("Starting Robot!", "Press Start to start ;-;");
        telemetry.update();

        waitForStart();
        currentTime = System.nanoTime()/milliSec;
        while(opModeIsActive())
        {

            while (segment_passed > 1)
            {
                for(SubSystem s : systems)
                    s.update();
                segment_passed -= 1.0;
            }


            // calculate delta time
            endTime = java.lang.System.nanoTime()/milliSec;
            delta = endTime - currentTime;
            currentTime = endTime;

            total_passed += delta;
            segment_passed += delta;
        }
    }
}

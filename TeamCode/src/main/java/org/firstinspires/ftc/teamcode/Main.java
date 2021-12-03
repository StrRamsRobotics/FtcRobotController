package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.driving.motorDrive;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.MechanumDrive;
import org.firstinspires.ftc.teamcode.systems.System;

import java.util.ArrayList;


@TeleOp(name="Main Loop!")
public class Main extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // stuff
        ArrayList<System> systems = new ArrayList<>();

        // systems.add(new Intake(this.hardwareMap, this));
        // systems.add(new motorDrive(this.hardwareMap, this));
        systems.add(new MechanumDrive(this.hardwareMap, this));

        // init the systems
        for(System system : systems)
            system.init();

        telemetry.addData("Waiting for Start!", "Press start to start");
        telemetry.update();
        waitForStart();

        while (opModeIsActive())
            {
                // update each system
                for (System system : systems)
                {
                    system.update();
                }
            }

    }
}

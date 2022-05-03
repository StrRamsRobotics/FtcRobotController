package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.systems.Driving;
import org.firstinspires.ftc.teamcode.systems.System;
import org.firstinspires.ftc.teamcode.utils.Time;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="FTC ROBOT CONTROLLERt")
public class Controller extends LinearOpMode {

    public void runOpMode(){
        // init stuff
        UserInput.init(this);

        List<System> systems = new ArrayList<>();

        /*
        right bumper for slo mo toggle
        start for duck spinner
        y + b for input spinner

        left_stick for moving

        dpad for moving the arm
         */

        // add the systems
        systems.add(new Driving(hardwareMap, this));
        systems.add(new DuckSpinner(hardwareMap, this));
        systems.add(new Arm(hardwareMap, this));
        systems.add(new Intake(hardwareMap, this));

        for (System s : systems)
            s.init();

        telemetry.addData("Starting!", "Press start to START ;-;");

        telemetry.update();

        waitForStart();
        Time.start();
        while(opModeIsActive()){

            // dropped time counting -> no need
            Time.update();
            for (System s: systems)
                s.update();
            telemetry.update();

        }


    }

}

package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.systems.Driving;
import org.firstinspires.ftc.teamcode.systems.System;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="FTC ROBOT CONTROLLER")
public class Controller extends LinearOpMode {

    public void runOpMode(){
        // init stuff
        UserInput.init(this);

        List<System> systems = new ArrayList<>();

        /*
        y + b for input

        right_bumper for slomo activation

        start for duck spinner

        left_stick and right_stick for moving

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
        while(opModeIsActive()){

            // dropped time counting -> no need

            for (System s: systems)
                s.update();
            telemetry.update();

        }


    }

}

package test.systems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;
import java.util.List;

import test.utils.Time;

@TeleOp(name="FTC ROBOT CONTROLLER")
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

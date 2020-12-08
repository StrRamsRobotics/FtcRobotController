package com.team16488;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.team16488.common.RobotMap;
import com.team16488.compoonents.DriveStraight;
import com.team16488.compoonents.MecanumDrive;

/**
 * This is the main OpMode for the driver operated portion of the event
 * HERE WE RUN THE METHODS ASSOCIATED WITH THE COMPONENTS OF THE ROBOT
 */
public class Robot extends OpMode {
    // Create our virtual Robot(using the robotMap class)
    public RobotMap       robot = new RobotMap();

    // Create our components(add them to our virtual robot)
    //public MecanumDrive   drive;
    public DriveStraight  driveStraight;


    @Override
    public void init() {
        /* IMPORTANT !!!!
        * EACH OPMODE HAS ITS OWN OBJECTS THAT ARE CALLED WHEN THE PROGRAM IS RUN
        * IN ORDER TO MAKE OUR OTHER FILES INTERACT WITH THESE CUSTOM OBJECTS(WHICH
        * CORRELATE WITH THE PHYSICAL ROBOT WE HAVE TO CALL THEM IN OUR PARAMETERS
        */


        // Build our virtual robot(make the opMode call its RobotMap(hardwareMap))
        this.robot.mapHardware(hardwareMap);

        // Add our Virtual components
        //this.drive              = new MecanumDrive(this.robot);
        this.driveStraight      = new DriveStraight(this.robot);

    }

    @Override
    public void loop() {

        // Note the GamePad sticks values are inverted physically(forward = backward) so in order to get the
        // right orientation we have invert them virtually so that forward = forward

        //Invert GamePad sticks
        gamepad1.left_stick_y = -gamepad1.left_stick_y;
        gamepad1.left_stick_x = -gamepad1.left_stick_x;
        gamepad1.right_stick_x = -gamepad1.right_stick_x;
        gamepad1.right_stick_y = -gamepad1.right_stick_y;

        // Call Custom Command Methods

        // Drive Train(Mecanum Drive)
        //drive.operatorMecanumDrive(gamepad1.left_stick_x,
        //                           gamepad1.left_stick_y,
        //                           gamepad1.right_stick_x);

        // Drive Train(DriveStraight)
        // Tank Command
        driveStraight.driveTank(gamepad1.left_stick_y, gamepad1.right_stick_y);
        // Arcade Drive Command
        driveStraight.driveArcade(gamepad1.right_stick_x, gamepad1.left_stick_y);

    }

    @Override
    public void stop() {
        driveStraight.stopDrive();
        //drive.stopDrive();
    }
}

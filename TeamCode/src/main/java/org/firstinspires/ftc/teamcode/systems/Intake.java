package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Main;
import org.firstinspires.ftc.teamcode.intake.Chassis;
import org.firstinspires.ftc.teamcode.intake.TelescopingArm;

public class Intake extends System {

    private Chassis chassis;
    private TelescopingArm arm;
    private Main main;

    public Intake(HardwareMap hw, Main main)
    {
        // intiialize parts
        this.arm = new TelescopingArm(hw);
        this.main = main;
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        actionPerformed();
        //running status
        main.telemetry.addData("Status", "Running");
        //arm position
        main.telemetry.addData("Arm Position: ", "Position " + arm.getPos());
        main.telemetry.update();
    }

    public void actionPerformed() {
        //gamepad 2 actions
        if (main.gamepad2.y) {
            arm.setPosThree();
        }
        if (main.gamepad2.a) {
            arm.setPosTwo();
        }
    }
}

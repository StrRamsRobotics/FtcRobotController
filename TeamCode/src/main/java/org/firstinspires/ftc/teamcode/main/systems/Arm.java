package org.firstinspires.ftc.teamcode.main.systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.main.Main;
import org.firstinspires.ftc.teamcode.main.nodes.TelescopingArm;

public class Arm extends SubSystem
{
    private TelescopingArm arm;
    private HardwareMap hw;
    private Main main;

    @Override
    public void init(HardwareMap hw, Main main) {
        this.hw = hw;
        this.main = main;
        arm = new TelescopingArm(hw);
    }

    @Override
    public void update() {
        if (main.gamepad2.dpad_down) {
            arm.setPosThree();
        } else if (main.gamepad2.dpad_right) {
            arm.setPosTwo();
        } else if (main.gamepad2.dpad_up) {
            arm.setPosOne();
        }
    }
}

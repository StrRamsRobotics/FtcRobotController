package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.Gamepad;

public class UserInput {

    public static Gamepad left, right;

    public static void init(Controller controller){
        left = controller.gamepad1;
        right = controller.gamepad2;
    }
}

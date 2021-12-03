package org.firstinspires.ftc.teamcode.main.systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.main.Main;

public abstract class SubSystem {
    protected HardwareMap hw;
    protected Main main;

    public abstract void init(HardwareMap hw, Main main);
    public abstract void update();
}

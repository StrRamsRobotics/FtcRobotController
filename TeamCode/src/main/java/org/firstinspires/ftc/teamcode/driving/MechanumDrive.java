/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.driving;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
import org.firstinspires.ftc.teamcode.Main;
import org.firstinspires.ftc.teamcode.systems.System;

public class MechanumDrive extends System {

    /* Declare OpMode members. */
    MacanumDrive robot;
    Main main;
    double x1, y1, x2, y2;
    double fortyFiveInRads, cosine45, sine45;

    public MechanumDrive(HardwareMap hwMap, Main main)
    {
        this.main= main;
        robot = new MacanumDrive(hwMap);
        robot.init();

        main.telemetry.addData("Say", "Hello Driver");    //
        main.telemetry.update();

    }

    public void init() {

        fortyFiveInRads = -Math.PI / 4;
        cosine45 = Math.cos(fortyFiveInRads);
        sine45 = Math.sin(fortyFiveInRads);

    }

    public void update(){

        y1 = -main.gamepad1.left_stick_y;
        x1  =  main.gamepad1.left_stick_x;

        //Since it's Mecanum it needs to turn 45 degrees
        y2 = x1*sine45 + y1*cosine45;
        x2 = x1*cosine45 - y1*sine45;

        // Output the safe vales to the motor drives.
        robot.frontLeftDrive.setPower(x2);
        robot.frontRightDrive.setPower(x2);

        robot.backLeftDrive.setPower(y2);
        robot.backRightDrive.setPower(y2);

        // Send telemetry message to signify robot running;
        main.telemetry.addData("x1",  "%.2f", x1);
        main.telemetry.addData("y1", "%.2f", y1);
        main.telemetry.update();


    }
}

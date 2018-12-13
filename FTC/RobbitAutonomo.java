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

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Autonomo", group="Linear Opmode")

public class RobbitAutonomo extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor centreDrive = null;
    private DcMotor elevador = null;
    private DcMotor brazo = null;
    private Servo mano = null;
    private Servo extension = null;
    private Servo pinza = null;
    private Servo catapulta = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        centreDrive = hardwareMap.get(DcMotor.class, "centre_drive");
        elevador = hardwareMap.get(DcMotor.class, "elevador");
        brazo = hardwareMap.get(DcMotor.class, "brazo");
        extension = hardwareMap.get(Servo.class, "extension");
        mano = hardwareMap.get(Servo.class, "mano");
        pinza = hardwareMap.get(Servo.class, "dedos");
        catapulta = hardwareMap.get(Servo.class, "catapulta");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        double extensionPosition = 0;
        double manoPosition = 0;
        double pinzaPosition = 0.2;
        extension.setPosition(extensionPosition);
        mano.setPosition(manoPosition);
        pinza.setPosition(pinzaPosition);
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setTargetPosition((int)Math.round(288*5.5));
        rightDrive.setTargetPosition((int)Math.round(288*5.5));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        catapulta.setPosition(1);
        waitForStart();
        runtime.reset();

        while (opModeIsActive() && leftDrive.isBusy() && rightDrive.isBusy()) {
            leftDrive.setPower(0.5);
            rightDrive.setPower(0.5);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motor derecho: ", rightDrive.getCurrentPosition());
            telemetry.addData("Motor izquierdo: ", leftDrive.getCurrentPosition());
            telemetry.update();
        }
        double currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime + 500 > runtime.milliseconds()){
            catapulta.setPosition(0.6);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motor derecho: ", rightDrive.getCurrentPosition());
            telemetry.addData("Motor izquierdo: ", leftDrive.getCurrentPosition());
            telemetry.addData("Current time: ", currentTime);
            telemetry.update();
        }
        catapulta.setPosition(1);
    }
}

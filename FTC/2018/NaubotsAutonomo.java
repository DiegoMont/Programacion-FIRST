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
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

  @Autonomous(name="Autonomo", group="Linear Opmode")
public class NaubotsAutonomo extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor elevador1 = null;
    private DcMotor elevador2 = null;
    private DcMotor barredora = null;
    private Servo catapulta = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        elevador1 = hardwareMap.get(DcMotor.class, "elevador1");
        elevador2 = hardwareMap.get(DcMotor.class, "elevador2");
        barredora = hardwareMap.get(DcMotor.class, "barredora");
        catapulta = hardwareMap.get(Servo.class, "catapulta");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        elevador1.setTargetPosition(0);
        elevador1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevador2.setTargetPosition(0);
        elevador2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        catapulta.setPosition(1);

        waitForStart();
        elevador1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador1.setTargetPosition(-935);
        elevador1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevador2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador2.setTargetPosition(-935);
        elevador2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        runtime.reset();
        double currentTime;

        while (opModeIsActive() && elevador1.isBusy()&&elevador2.isBusy()) {
            elevador1.setPower(0.4);
            elevador2.setPower(0.4);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Elevador: ", elevador1.getCurrentPosition());
            telemetry.update();
        }
        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +500 > runtime.milliseconds()){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setTargetPosition(160);
        rightDrive.setTargetPosition(-160);
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive() && rightDrive.isBusy() && leftDrive.isBusy()) {
            leftDrive.setPower(1);
            rightDrive.setPower(1);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Left encoder: ", leftDrive.getCurrentPosition());
            telemetry.addData("Right encoder: ", rightDrive.getCurrentPosition());
            telemetry.update();
        }

        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +500 > runtime.milliseconds()){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setTargetPosition(-160);
        rightDrive.setTargetPosition(+160);
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive() && rightDrive.isBusy() && leftDrive.isBusy()) {
            leftDrive.setPower(1);
            rightDrive.setPower(1);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Left encoder: ", leftDrive.getCurrentPosition());
            telemetry.addData("Right encoder: ", rightDrive.getCurrentPosition());
            telemetry.update();
        }

        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +500 > runtime.milliseconds()){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setTargetPosition((int)(-288*3.5));
        rightDrive.setTargetPosition((int)(-288*3.5));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive() && rightDrive.isBusy() && leftDrive.isBusy()) {
            leftDrive.setPower(0.85);
            rightDrive.setPower(0.85);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Left encoder: ", leftDrive.getCurrentPosition());
            telemetry.addData("Right encoder: ", rightDrive.getCurrentPosition());
            telemetry.update();
        }

        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +1000 > runtime.milliseconds()){
            catapulta.setPosition(0.5);
            barredora.setPower(-0.5);

        }
        catapulta.setPosition(1);

        //dar vuelta
        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +500 > runtime.milliseconds()){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setTargetPosition(200);
        rightDrive.setTargetPosition(-200);
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive() && rightDrive.isBusy() && leftDrive.isBusy()) {
            leftDrive.setPower(1);
            rightDrive.setPower(1);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Left encoder: ", leftDrive.getCurrentPosition());
            telemetry.addData("Right encoder: ", rightDrive.getCurrentPosition());
            telemetry.update();
        }

        //crater
        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +500 > runtime.milliseconds()){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setTargetPosition((int)(-288*6));
        rightDrive.setTargetPosition((int)(-288*6));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive() && rightDrive.isBusy() && leftDrive.isBusy()) {
            leftDrive.setPower(1);
            rightDrive.setPower(1);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Left encoder: ", leftDrive.getCurrentPosition());
            telemetry.addData("Right encoder: ", rightDrive.getCurrentPosition());
            telemetry.update();
        }
    }
}

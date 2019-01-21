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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Naubots", group="Iterative Opmode")

public class Naubots extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor elevador = null;
    private Servo teamMarker = null;
    private DcMotor barredora = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        elevador = hardwareMap.get(DcMotor.class, "elevador");
        teamMarker = hardwareMap.get(Servo.class, "catapulta");
        barredora = hardwareMap.get(DcMotor.class, "barredora");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        teamMarker.setPosition(0.5);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    boolean modoDriver = true;
    boolean click = false;
    double servoPosition = 0.5;

    @Override
    public void loop() {
        double leftPower;
        double rightPower;
        double barredoraPower;
        double elevadorPower;

        if(gamepad1.start){
            click = true;
        } else if ( !gamepad1.start && click){
           modoDriver = (modoDriver)?false:true;
           click = false;
        }

        if(modoDriver){
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x * 1.5;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0);
            rightPower   = Range.clip(drive - turn, -1.0, 1.0);
        } else {
            leftPower = -gamepad1.left_stick_y;
            rightPower = -gamepad1.right_stick_y;
        }

        if(gamepad1.right_trigger > 0){
            barredoraPower = 1;
        } else if (gamepad1.left_trigger > 0){
            barredoraPower = -1;
        } else {
            barredoraPower = 0;
        }

        if(gamepad2.dpad_up){
            elevadorPower = 1;
        } else if(gamepad2.dpad_down){
            elevadorPower = -1;
        } else {
            elevadorPower = 0;
        }

        if(gamepad2.y){
            servoPosition = 0;
        } else if(gamepad2.a){
            servoPosition = 0.5;
        }

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
        elevador.setPower(elevadorPower);
        teamMarker.setPosition(servoPosition);
        barredora.setPower(barredoraPower);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Modo driver: ", (modoDriver)?"POV":"Tanque");
        telemetry.addData("Elevador: ", elevador.getCurrentPosition());
    }

    @Override
    public void stop() {
    }

}

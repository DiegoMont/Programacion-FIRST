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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Robbit", group="Iterative Opmode")
//@Disabled
public class Robbit extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor centreDrive = null;
    private DcMotor elevador = null;
    private DcMotor brazo = null;
    private Servo mano = null;
    private Servo extension = null;
    private Servo pinza = null;

    //Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        centreDrive = hardwareMap.get(DcMotor.class, "centre_drive");
        elevador = hardwareMap.get(DcMotor.class, "elevador");
        brazo = hardwareMap.get(DcMotor.class, "brazo");
        extension = hardwareMap.get(Servo.class, "extension");
        mano = hardwareMap.get(Servo.class, "mano");
        pinza = hardwareMap.get(Servo.class, "dedos");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
    }

     //Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {
    }

     //Code to run ONCE when the driver hits PLAY
    @Override
    public void start() {
        runtime.reset();
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brazo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        brazo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    double extensionPosition = 0;
    double manoPosition = 0;
    double pinzaPosition = 0.15;
     //Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        double leftPower;
        double rightPower;
        double elevadorPosition;
        double brazoPosition;

        if (gamepad1.a /*&& elevador.getCurrentPosition() <= 0*/) {
            elevadorPosition = 0.5;
          } else if (gamepad1.b /*&& elevador.getCurrentPosition() >= -2500*/) {
            elevadorPosition = -0.5;
          } else {
            elevadorPosition = 0.0;
          }

        if(gamepad2.dpad_left){
            pinzaPosition += 0.01;
        } else if(gamepad2.dpad_right){
            pinzaPosition -= 0.01;
        }

        if(gamepad2.left_bumper){
            extensionPosition += 0.01;
        } else if(gamepad2.right_bumper){
            extensionPosition -= 0.01;
        }

        if(gamepad2.left_trigger > 0){
            manoPosition += 0.01;
        } else if(gamepad2.right_trigger > 0){
            manoPosition -= 0.01;
        }
        pinzaPosition = Range.clip(pinzaPosition,0,1.15);
        extensionPosition = Range.clip(extensionPosition,0,1);
        manoPosition = Range.clip(manoPosition,0,1);
        if(gamepad1.dpad_up){
            brazoPosition = 0.3;
        } else if(gamepad1.dpad_down/* && brazo.getCurrentPosition() <= 0*/){
            brazoPosition = -0.2;
        } else {
            brazoPosition = 0;
        }

        // POV Mode uses left stick to go forward, and right stick to turn.
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
        centreDrive.setPower(gamepad1.left_stick_x);
        elevador.setPower(elevadorPosition);
        brazo.setPower(brazoPosition);
        extension.setPosition(extensionPosition);
        mano.setPosition(manoPosition);
        pinza.setPosition(pinzaPosition);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.addData("Elevador position", elevador.getCurrentPosition());
        telemetry.addData("Brazo position", brazo.getCurrentPosition());
        telemetry.addData("Pinza: ", pinzaPosition);
        telemetry.addData("Mano: ", manoPosition);
        telemetry.addData("Extension: ", extensionPosition);
    }

     //Code to run ONCE after the driver hits STOP
    @Override
    public void stop() {
    }

}

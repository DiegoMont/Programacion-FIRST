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
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Robbit", group="Iterative Opmode")
public class Robbit extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor centreDrive = null;
    private DcMotor elevador = null;
    private DcMotor brazo = null;
    private DcMotor extencion=null;
    private Servo mano = null;
    private Servo pinza = null;
    private Servo teamMarker = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        centreDrive = hardwareMap.get(DcMotor.class, "centre_drive");
        elevador = hardwareMap.get(DcMotor.class, "elevador");
        brazo = hardwareMap.get(DcMotor.class, "brazo");
        extencion = hardwareMap.get(DcMotor.class,"extencion");
        mano = hardwareMap.get(Servo.class, "mano");
        pinza = hardwareMap.get(Servo.class, "garra");
        teamMarker = hardwareMap.get(Servo.class, "catapulta");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        teamMarker.setPosition(1);

        telemetry.addData("Status", "Initialized");
    }

    double manoPosition = 0;
    double pinzaPosition = 0.9;


    @Override
    public void init_loop() {
        mano.setPosition(manoPosition);
        pinza.setPosition(pinzaPosition);
    }

    @Override
    public void start() {
        runtime.reset();
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    boolean modoDriver = true;
    boolean click = false;

    @Override
    public void loop() {
        double leftPower;
        double rightPower;
        double elevadorPower;
        double extensionPower;
        double teamMarkerPosition;

        if(gamepad1.start){
            click = true;
        } else if (click && !gamepad1.start){
            modoDriver = (modoDriver)?false:true;
            click=false;
        }

        if(modoDriver){
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;
        } else {
            leftPower = -gamepad1.left_stick_y;
            rightPower = -gamepad1.right_stick_y;
        }

        if (gamepad2.x) {
            elevadorPower = 0.5;
          } else if (gamepad2.y) {
            elevadorPower = -0.5;
          } else {
            elevadorPower = 0.0;
          }

        if(gamepad2.left_trigger > 0){
            pinzaPosition+=0.01;
        } else if(gamepad2.right_trigger > 0){
            pinzaPosition -= 0.01;
        }

        if(gamepad2.a){
            extensionPower = 0.5;
        } else if(gamepad2.b){
            extensionPower = -0.5;
        } else {
            extensionPower = 0;
        }

        if (gamepad2.right_bumper) {
            manoPosition +=0.01;
        } else if (gamepad2.left_bumper) {
            manoPosition -= 0.01;
        }


        if (gamepad2.dpad_left){
            brazo.setPower(1);
        } else if (gamepad2.dpad_right){
            brazo.setPower(-1);
        } else {
            brazo.setPower(0);
        }

        if(gamepad2.left_stick_x < 0){
            teamMarker.setPosition(0.65);
        } else if(gamepad2.left_stick_x > 0){
            teamMarker.setPosition(1);
        }

        manoPosition = Range.clip(manoPosition, 0, 1);
        pinzaPosition = Range.clip(pinzaPosition, 0.65, 1);

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
        centreDrive.setPower(gamepad1.left_stick_x);
        elevador.setPower(elevadorPower);
        mano.setPosition(manoPosition);
        pinza.setPosition(pinzaPosition);
        extencion.setPower(extensionPower);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.addData("Elevador position", elevador.getCurrentPosition());
        telemetry.addData("Modo driver: ", (modoDriver)?"POV":"Tanque");
        telemetry.addData("Mano: ", manoPosition);
        telemetry.addData("Pinza: ", pinzaPosition);
        telemetry.addData("Brazo: ",brazo.getCurrentPosition());
    }

    @Override
    public void stop() {
    }

}

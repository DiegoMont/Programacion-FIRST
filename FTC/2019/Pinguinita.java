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
import java.util.Set;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Pinguinita", group="Linear Opmode")
public class Pinguinita extends LinearOpMode {
  private ElapsedTime runtime = new ElapsedTime();
  private DcMotor leftDrive = null;
  private DcMotor rightDrive = null;

  @Override
  public void runOpMode() {
    telemetry.addData("Status", "Initialized");
    telemetry.update();

    leftDrive  = hardwareMap.get(DcMotor.class, "motor_left");
    rightDrive = hardwareMap.get(DcMotor.class, "motor_right");
    leftDrive.setDirection(DcMotor.Direction.REVERSE);
    rightDrive.setDirection(DcMotor.Direction.FORWARD);

    leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    waitForStart();
    runtime.reset();
    int rightTarget;
    int leftTarget;
    boolean click = false;
    boolean click1 = false;
    boolean click2 = false;
    boolean modoDriver = true;

    while (opModeIsActive()) {
      double leftPower;
      double rightPower;
      int ticksToTurn = 200;

      if(gamepad1.back){
        click = true;
      } else if ( !gamepad1.back && click){
        modoDriver = (modoDriver)?false:true;
        click = false;
      }

      if(modoDriver){
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.left_stick_x;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0);
        rightPower   = Range.clip(drive - turn, -1.0, 1.0);
      } else {
        leftPower = -gamepad1.left_stick_y;
        rightPower = -gamepad1.right_stick_y;
      }

      if(gamepad1.left_trigger>0 && !click1){
        leftTarget=leftDrive.getCurrentPosition()- ticksToTurn;
        leftDrive.setTargetPosition(leftTarget);
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightTarget=rightDrive.getCurrentPosition()+ ticksToTurn;
        rightDrive.setTargetPosition(rightTarget);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        click1 = true;
      } else if(gamepad1.left_trigger==0 && click1){
        click1 = false;
      }

      if(gamepad1.right_trigger>0 && !click2){
        leftTarget=leftDrive.getCurrentPosition()+ticksToTurn;
        leftDrive.setTargetPosition(leftTarget);
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightTarget=rightDrive.getCurrentPosition()-ticksToTurn;
        rightDrive.setTargetPosition(rightTarget);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        click2 = true;
      } else if(gamepad1.right_trigger==0 && click2){
        click2 = false;
      }

      if(leftDrive.isBusy() && rightDrive.isBusy()){
        leftPower = 1;
        rightPower = 1;
      } else {
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      }

      leftDrive.setPower(leftPower);
      rightDrive.setPower(rightPower);

      telemetry.addData("Status", "Run Time: " + runtime.toString());
      telemetry.addData("Velocidad motor izquierdo:", leftPower);
      telemetry.addData("Velocidad motor derecho:", rightPower);
      telemetry.update();
    }
  }
}

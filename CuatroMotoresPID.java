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
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Control PID", group="Iterative Opmode")

public class CuatroMotoresPID extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftBDrive = null;
    private DcMotor rightBDrive = null;
    private DcMotor leftFDrive = null;
    private DcMotor rightFDrive = null;

    //Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftFDrive  = hardwareMap.get(DcMotor.class, "LFM");
        leftBDrive  = hardwareMap.get(DcMotor.class, "LBM");
        rightFDrive = hardwareMap.get(DcMotor.class, "RFM");
        rightBDrive = hardwareMap.get(DcMotor.class, "RBM");

        leftBDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBDrive.setDirection(DcMotor.Direction.FORWARD);
        leftFDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFDrive.setDirection(DcMotor.Direction.FORWARD);

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
    }

    //Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    double tiempo = 0;
    double leftPower = 0;
    double rightPower = 0;

    public static double controlP(double pAct, double des) {
      double dif = Math.abs(des-pAct);
      if (dif > 0.3) {
        if (des > pAct) {
          pAct = pAct + 0.1;
        } else if (des < pAct) {
          pAct = pAct - 0.1;
        }
      }  else {
        pAct = des;
      }
      return Range.clip(pAct, -1, +1);
    }

    @Override
    public void loop() {
        double tiempoActual = runtime.milliseconds();
        double leftDeseado = -gamepad1.left_stick_y;
        double rightDeseado  =  -gamepad1.right_stick_y;

        //Acceleration control
        if (tiempoActual >= tiempo + 40) {
          leftPower = controlP(leftPower,leftDeseado);
          rightPower = controlP(rightPower,rightDeseado);
          tiempo = tiempoActual;
        }

        // Control power of wheels.
        if (gamepad1.right_trigger > 0) {
          leftPower = leftPower * 0.75;
          rightPower = rightPower * 0.75;
        } else if(gamepad1.left_trigger>0){
          leftPower = leftPower * 0.25 + leftPower * 0.5 * (1 - gamepad1.left_trigger);
          rightPower = rightPower * 0.25 + rightPower * 0.5 * (1 - gamepad1.left_trigger);
        }

        // Send calculated power to wheels
        leftBDrive.setPower(leftPower);
        rightFDrive.setPower(rightPower);
        leftFDrive.setPower(leftPower);
        rightBDrive.setPower(rightPower);
        
        if(gamepad1.right_bumper){
          leftBDrive.setPower(leftDeseado);
          rightFDrive.setPower(rightDeseado);
          leftFDrive.setPower(leftDeseado);
          rightBDrive.setPower(rightDeseado);
        }

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", tiempoActual);
        telemetry.addData("Left", "Deseado: (%.2f), Actual: (%.2f)", leftDeseado, leftPower);
        telemetry.addData("Right", "Deseado: (%.2f), Actual: (%.2f)", rightDeseado, rightPower);
    }

    //Code to run ONCE after the driver hits STOP
    @Override
    public void stop() {
    }
    
  }

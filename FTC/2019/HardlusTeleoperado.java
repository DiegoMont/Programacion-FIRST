/*Copyright 2019
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Hardlus", group="Linear Opmode")
public class HardlusTeleoperado extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private NaveDelOlvido hardbot = new NaveDelOlvido(this);

    @Override
    public void runOpMode() {
      telemetry.addData("Status", "Initialized");
      telemetry.update();

      hardbot.getHardware(hardwareMap);

      hardbot.resetEncoders();

      waitForStart();

      runtime.reset();

      double desiredPosition;
      double posicionServo = 0;
      double periodo = runtime.milliseconds();
      boolean modoDios = false;
      boolean click = false;

      while(opModeIsActive()) {
        double frontLeftPower, frontRightPower, backLeftPower, backRightPower, desviacion = hardbot.getDesviacion();
        double actual = runtime.milliseconds();

        if(gamepad1.back) {
          click = true;
        } else if(!gamepad1.back && click) {
          modoDios = !modoDios;
          click = false;
        }

        if(gamepad2.dpad_left || (modoDios && gamepad1.dpad_left)) {
          if(periodo < actual) {
            posicionServo += 0.05;
            periodo = actual + 100;
          }
        } else if(gamepad2.dpad_right || (modoDios && gamepad1.dpad_right)) {
          if(periodo < actual) {
            posicionServo -= 0.05;
            periodo = actual + 100;
          }
        }
        posicionServo = Range.clip(posicionServo, 0, 1);

        double drive = -gamepad1.left_stick_y;
        double lateral = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        frontLeftPower = Range.clip(drive + lateral + turn, -1.0, 1.0);
        frontRightPower = Range.clip(drive - lateral -turn, -1.0, 1.0);
        backLeftPower = Range.clip(drive - lateral + turn, -1.0, 1.0);
        backRightPower = Range.clip(drive + lateral - turn, -1.0, 1.0);

        if(gamepad1.right_bumper){
          frontLeftPower *= 0.75;
          frontRightPower *= 0.75;
          backLeftPower *= 0.75;
          backRightPower *= 0.75;
        }

        if(gamepad1.left_bumper){
          frontLeftPower *= 0.5;
          frontRightPower *= 0.5;
          backLeftPower *= 0.5;
          backRightPower *= 0.5;
        }

        double elevadorPower;
        if(gamepad2.dpad_up || (modoDios && gamepad1.dpad_up)) elevadorPower = -0.25;
        else if(gamepad2.dpad_down || (modoDios && gamepad1.dpad_down)) elevadorPower = 0.25;
        else elevadorPower = 0;
        if(gamepad2.right_trigger > 0 && gamepad2.left_trigger > 0 || (modoDios && gamepad1.right_trigger > 0 && gamepad1.left_trigger > 0)) elevadorPower *= 4;

        hardbot.frontLeft.setPower(frontLeftPower);
        hardbot.frontRight.setPower(frontRightPower);
        hardbot.backLeft.setPower(backLeftPower);
        hardbot.backRight.setPower(backRightPower);
        hardbot.elevador.setPower(elevadorPower);
        hardbot.brazo.setPosition(posicionServo);

        if(modoDios) {
          telemetry.addData("M  M OOOO DDD  OOOO", "DDD  IIII OOOO SSSS");
          telemetry.addData("MMMM O  O D  D O  O", "D  D   I  O  O S");
          telemetry.addData("M  M O  O D  D O  O", "D  D   I  O  O  S");
          telemetry.addData("M  M OOOO DDD  OOOO", "DDD  IIII OOOO ssss");
        }else {
          telemetry.addData("Status", "Run Time: " + actual);
          telemetry.addData("Potencia:", posicionServo);
        }
        telemetry.update();
      }
    }
}

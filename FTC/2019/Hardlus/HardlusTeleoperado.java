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
      double servoPositionUno = 0;
      double servoDosPosition = 0.75;
      double foundationDerechaPosicion = 0.3;
      double foundationIzquierdaPosicion = 0.5;
      double lastServoChange = runtime.milliseconds();
      double lastFoundationChange = runtime.milliseconds();
      while(opModeIsActive()) {
        double frontLeftPower, frontRightPower, backLeftPower, backRightPower;

        double drive = -gamepad1.left_stick_y;
        double lateral = 1.28*gamepad1.right_stick_x;
        double turn = gamepad1.left_stick_x;
        double elevadorPower = 0.35*gamepad2.left_stick_y;
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

        if(gamepad1.left_bumper && gamepad1.right_bumper){
          frontLeftPower *= 0.4;
          frontRightPower *= 0.4;
          backLeftPower *= 0.4;
          backRightPower *= 0.4;
        }

        if(gamepad2.left_bumper){
          elevadorPower *= 0.33;
        }

        double intakePower = 0;
        if(gamepad2.left_trigger > 0)
          intakePower = 1;
        else if (gamepad2.right_trigger > 0)
          intakePower = -1;

        //método para los servos
        if(runtime.milliseconds() > 50 + lastServoChange) {
          if(gamepad2.a) {
            servoPositionUno += 0.02;
            lastServoChange = runtime.milliseconds();
          }
          else if(gamepad2.b) {
            servoPositionUno -= 0.02;
            lastServoChange = runtime.milliseconds();
          }

          if(gamepad2.x) {
            servoDosPosition += 0.04;
            lastServoChange = runtime.milliseconds();
          }
          else if(gamepad2.y) {
            servoDosPosition -= 0.04;
            lastServoChange = runtime.milliseconds();
          }
        }

        servoPositionUno = Range.clip(servoPositionUno, 0, 1);
        servoDosPosition = Range.clip(servoDosPosition, 0, 1);

        //métodos para la foundation
        if(runtime.milliseconds()> 100 + lastFoundationChange){
          if((foundationIzquierdaPosicion < 0.8) & (foundationDerechaPosicion < 0.8)){
            if(gamepad1.a){
            foundationDerechaPosicion += 0.09;
            foundationIzquierdaPosicion -= 0.09;
            lastFoundationChange = runtime.milliseconds();
            }

            else if(gamepad1.b){
            foundationDerechaPosicion -= 0.09;
            foundationIzquierdaPosicion += 0.09;
            lastFoundationChange = runtime.milliseconds();
            }
          }
          /*((foundationDerechaPosicion > 0.8) & (foundationIzquierdaPosicion > 0.10)){
            foundationDerechaPosicion = 0.65;
            foundationIzquierdaPosicion = 0.65;
            break;
          }*/
        }

        foundationDerechaPosicion = Range.clip(foundationDerechaPosicion, 0, 1);
        foundationIzquierdaPosicion = Range.clip(foundationIzquierdaPosicion, 0, 1);

        hardbot.frontLeft.setPower(frontLeftPower);
        hardbot.frontRight.setPower(frontRightPower);
        hardbot.backLeft.setPower(backLeftPower);
        hardbot.backRight.setPower(backRightPower);
        hardbot.elevadorOne.setPower(elevadorPower);
        hardbot.elevadorTwo.setPower(elevadorPower);
        hardbot.servoUno.setPosition(servoPositionUno);
        hardbot.servoDos.setPosition(servoDosPosition);
        hardbot.activarExtension(intakePower);
        hardbot.foundationDerecha.setPosition(foundationDerechaPosicion);
        hardbot.foundationIzquierda.setPosition(foundationIzquierdaPosicion);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Servo Dos: ", servoDosPosition);
        telemetry.addData("Servo Uno: ", servoPositionUno);
        telemetry.addData("elevador: ", hardbot.posicionElevador());
        telemetry.addData("intake: ", intakePower);
        telemetry.update();
      }
    }
}

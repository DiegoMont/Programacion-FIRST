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
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Hardlus", group="Linear Opmode")
public class TeleoperadoHardlus extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private NaveDelOlvido hardbot = new NaveDelOlvido(this);

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        hardbot.getHardware(hardwareMap);

        hardbot.resetEncoders();
    }

    @Override
    public void init_loop() {
    }

    private double periodo;
    @Override
    public void start() {
      runtime.reset();
      periodo = runtime.milliseconds();
    }

    double desiredPosition;

    @Override
    public void loop() {
      double frontLeftPower, frontRightPower, backLeftPower, backRightPower, desviacion = hardbot.getDesviacion();
      double servoPower = 0;
      double tiempoActual = runtime.milliseconds();

      double drive = -gamepad1.left_stick_y;
      double lateral = gamepad1.left_stick_x;
      double turn = gamepad11.right_stick_x;
      frontLeftPower = Range.clip(drive + lateral + turn, -1.0, 1.0);
      frontRightPower = Range.clip(drive - lateral -turn, -1.0, 1.0);
      backLeftPower = Range.clip(drive - lateral + turn, -1.0, 1.0);
      backRightPower = Range.clip(drive + lateral - turn, -1.0, 1.0);

      if(gamepad1.right_bumper){
        leftPower *= 0.75;
        rightPower *= 0.75;
      }

      if(gamepad1.left_bumper){
        leftPower *= 0.5;
        rightPower *= 0.5;
      }

      if(periodo + 1500 > tiempoActual){
            if(gamepad1.a){
                servoPower+=0.05;
            } else if(gamepad1.b){
                servoPower -= 0.05;
            }
            periodo = tiempoActual;
        }
        servoPower = Range.clip(power, 0.0, 1.0);
        hardbot.servo.setPosition(servoPower);
        telemetry.addData("servo: ", servoPower);

      /*hardbot.frontLeft.setPower(frontLeftPower);
      hardbot.frontRight.setPower(frontRightPower);
      hardbot.backLeft.setPower(backLeftPower);
      hardbot.backRight.setPower(backRightPower);*/

      telemetry.addData("Status", "Run Time: " + runtime.toString());
      telemetry.addData("Desviacion:", desviacion);
    }

    @Override
    public void stop() {

    }
}

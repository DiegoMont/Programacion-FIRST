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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Pinguinita", group="Linear Opmode")
public class Pinguinita extends LinearOpMode {
  private ElapsedTime runtime = new ElapsedTime();
  private LaBarca naubot = new LaBarca(this);

  @Override
  public void runOpMode() {
    telemetry.addData("Status", "Initialized");
    telemetry.update();

    naubot.getHardware(hardwareMap);

    naubot.resetEncoders();

    waitForStart();
    runtime.reset();
    boolean click = false;
    boolean click1 = false;
    boolean click2 = false;
    boolean modoDriver = true;
    double desiredPosition = 0;
    String direccionGiro = null;
    final double reduccionVelocidad = -100/9375/0.13;

    while (opModeIsActive()) {
      double leftPower, rightPower, desviacion = naubot.getDesviacion();
      double servoPower = 0;

      if(gamepad1.back){
        click = true;
      } else if ( !gamepad1.back && click){
        modoDriver = (modoDriver)?false:true;
        click = false;
      }

      if(modoDriver){
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0);
        rightPower   = Range.clip(drive - turn, -1.0, 1.0);
        if(turn != 0) desiredPosition = desviacion;
      } else {
        leftPower = -gamepad1.left_stick_y;
        rightPower = -gamepad1.right_stick_y;
        desiredPosition = desviacion;
      }

      double error = desiredPosition - desviacion;
      final double PROPORTIONAL = 0.2;
      if (error > 0 ) {
        double errorRelativo;
        try {errorRelativo = error / desiredPosition;} catch(ArithmeticException e){errorRelativo = 100;}
        leftPower -= leftPower * errorRelativo * PROPORTIONAL;
        rightPower += rightPower * errorRelativo * PROPORTIONAL;
      } else if (error < 0) {
        double errorRelativo;
        try {errorRelativo = error / desiredPosition;} catch(ArithmeticException e){errorRelativo = 100;}
        leftPower += leftPower * errorRelativo * PROPORTIONAL;
        rightPower -= rightPower * errorRelativo * PROPORTIONAL;
      }
      leftPower = Range.clip(leftPower, -1.0, 1.0);
      rightPower = Range.clip(rightPower, -1.0, 1.0);

      if(gamepad1.right_bumper){
        leftPower *= 0.75;
        rightPower *= 0.75;
      }

      if(gamepad1.left_bumper){
        leftPower *= 0.5;
        rightPower *= 0.5;
      }

      if(gamepad1.a){
        servoPower = -1;
      } else if(gamepad1.b){
        servoPower = 1;
      }

      if(gamepad1.left_trigger > 0 && !click1){
        direccionGiro = "left";
        naubot.setGiroDeNoventaGrados(direccionGiro);
        desiredPosition = desviacion + 90;
        click1 = true;
      } else if(gamepad1.left_trigger == 0 && click1){
        click1 = false;
      }

      if(gamepad1.right_trigger > 0 && !click2){
        direccionGiro = "right";
        naubot.setGiroDeNoventaGrados(direccionGiro);
        desiredPosition = desviacion - 90;
        click2 = true;
      } else if(gamepad1.right_trigger == 0 && click2){
        click2 = false;
      }

      if(naubot.leftDrive.isBusy() && naubot.rightDrive.isBusy()){
        if ((direccionGiro.equals("left") && desiredPosition > desviacion) || (direccionGiro.equals("right") && desiredPosition < desviacion)){
          leftPower = 1;
          rightPower = 1;
        }
      } else naubot.defaultRunmode();

      naubot.leftDrive.setPower(leftPower);
      naubot.rightDrive.setPower(rightPower);
      naubot.intakeIzquierdo.setPower(-servoPower);
      naubot.intakeDerecho.setPower(servoPower);

      telemetry.addData("Status", "Run Time: " + runtime.toString());
      telemetry.addData("Modo conduccion:", modoDriver ? "POV" : "Tanque");
      telemetry.addData("Velocidad motor izquierdo:", leftPower);
      telemetry.addData("Velocidad motor derecho:", rightPower);
      telemetry.addData("Desviacion:", desviacion);
      telemetry.update();
    }
  }
}

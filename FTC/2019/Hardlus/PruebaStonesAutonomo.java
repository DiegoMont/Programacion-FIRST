/*
Copyright 2019 FIRST Tech Challenge Team 15704

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
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.List;

@Autonomous(name="Prueba de stones")

public class PruebaStonesAutonomo extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private NaveDelOlvido hardbot = new NaveDelOlvido(this);

    @Override
    public void runOpMode() {

      hardbot.getHardware(hardwareMap);
      hardbot.iniciarAcelerometro(hardwareMap);

      telemetry.addData("Status", "Initialized");
      telemetry.update();
      waitForStart();

      if(opModeIsActive()) {
        runtime.reset();
        hardbot.resetEncoders();
        //hardbot.acomodarRobot();
        hardbot.moverDistanciaRecta(48.5);
        hardbot.movimientoLateral(-22, 0.5);
        sleep(500);
        hardbot.moverDistanciaRecta(-20, 0.5);
        hardbot.movimientoLateral(-45);
        sleep(100);
        hardbot.frontLeft.setPower(0.5);
        hardbot.frontRight.setPower(-0.5);
        hardbot.backLeft.setPower(-0.5);
        hardbot.backRight.setPower(0.5);
        sleep(3500);
        hardbot.frontLeft.setPower(0);
        hardbot.frontRight.setPower(0);
        hardbot.backLeft.setPower(0);
        hardbot.backRight.setPower(0);
        hardbot.moverDistanciaRecta(29);
      }
    }
}

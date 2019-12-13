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

@Autonomous(name="Foundation Red Alliance")

public class HardlusAutonomoTres extends LinearOpMode {

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
        hardbot.acomodarRobot();
        hardbot.moverDistanciaRecta(25);
        hardbot.movimientoLateral(22);
        hardbot.moverDistanciaRecta(10);
        //hardbot.girarEnEje(-3);
        hardbot.activarFoundation(false);
        sleep(1000);
        hardbot.frontRight.setPower(-0.75);
        hardbot.backRight.setPower(-0.75);
        sleep(2000);
        hardbot.frenar();
        hardbot.frontRight.setPower(0.7);
        hardbot.backRight.setPower(0.7);
        hardbot.frontLeft.setPower(0.85);
        hardbot.backLeft.setPower(0.85);
        sleep(1000);
        hardbot.frenar();
        hardbot.activarFoundation(true);
        sleep(1000);
        hardbot.moverDistanciaRecta(-30);
        hardbot.movimientoDiagonalDerecha(-15);
        hardbot.movimientoLateral(-18);
      }

    }
}

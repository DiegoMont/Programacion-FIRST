package org.firstinspires.ftc.teamcode.capacitaciones;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class ModoTanque extends OpMode{

  public DcMotor motorIzquierdo;
  public DcMotor motorDerecho;

  public void init(){
    motorIzquierdo = hardwareMap.get(DcMotor.class, "izquierdo");
    motorDerecho = hardwareMap.get(DcMotor.class, "derecho");
    motorIzquierdo.setDirection(DcMotor.Direction.REVERSE);

  }

  public void loop(){
    motorIzquierdo.setPower(-gamepad1.left_stick_y);
    motorDerecho.setPower(-gamepad1.right_stick_y);
  }
}

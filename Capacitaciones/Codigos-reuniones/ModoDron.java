package org.firstinspires.ftc.teamcode.capacitaciones;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotor;


public class ModoDron extends OpMode{

  public DcMotor izquierdo;
  public DcMotor derecho;
  public DcMotor centro;

  public void init(){
    izquierdo = hardwareMap.get(DcMotor.class, "motor1");
    derecho = hardwareMap.get(DcMotor.class, "motor2");
    centro = hardwareMap.get(DcMotor.class, "motor3");
  }

  public void loop(){
    double drive = -gamepad1.left_stick_y;
    double turn = gamepad1.right_stick_x;
    double lateral = gamepad1.left_stick_x;

    double leftPower = Range.clip(drive + turn, -1, 1);
    double rightPower = Range.clip(drive - turn, -1, 1);

    derecho.setPower(rightPower);
    izquierdo.setPower(leftPower);
    centro.setPower(lateral);

  }
}

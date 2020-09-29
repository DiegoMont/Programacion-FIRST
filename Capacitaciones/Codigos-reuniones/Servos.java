package org.firstinspires.ftc.teamcode.capacitaciones;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;


public class Servos extends OpMode{

  public Servo servoAngular;
  public CRServo servoContinuo;

  public void init(){
    servoAngular = hardwareMap.get(Servo.class, "servo1");
    servoContinuo = hardwareMap.get(CRServo.class, "servo2");
    servoAngular.setPosition(0.5);
    servoContinuo.setDirection(DcMotorSimple.Direction.REVERSE);

  }

 public void loop(){

   if(gamepad1.a) {
    servoAngular.setPosition(1);
   } else {
     servoAngular.setPosition(0.5);
   }

   //ModoClick
   
   servoContinuo.setPower(1);
 }
}

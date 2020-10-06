package org.firstinspires.ftc.teamcode.capacitaciones;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Robotito {

  public DcMotor motorIzquierdo;
  public DcMotor motorDerecho;
  
  private OpMode opMode;
  
  public Robotito(OpMode opMode){
    //Constructor
    this.opMode = opMode;
  }
  
  public void getHardware(HardwareMap hwm){
    motorIzquierdo = hwm.get(DcMotor.class, "motor1");
    motorDerecho = hwm.get(DcMotor.class, "motor2");
  }
  
  public void avanzar(double distancia){
    //TO DO
  }
}

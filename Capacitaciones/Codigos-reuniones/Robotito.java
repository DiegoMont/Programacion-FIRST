package org.firstinspires.ftc.teamcode.capacitaciones;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Robotito {

  public DcMotor motorIzquierdo;
  public DcMotor motorDerecho;
  
  final double DIAMETRO_LLANTAS = 10;
  final int TICKS_POR_ROTACION = 288;
  
  private LinearOpMode opMode;
  
  public Robotito(LinearOpMode opMode){
    //Constructor
    this.opMode = opMode;
  }
  
  public void getHardware(HardwareMap hwm){
    motorIzquierdo = hwm.get(DcMotor.class, "motor1");
    motorDerecho = hwm.get(DcMotor.class, "motor2");
  }
  
  public void resetEncoders(){
    motorIzquierdo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motorDerecho.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
  }
  
  public void avanzar(double distancia){
    int targetPosition = (int)(distancia / (DIAMETRO_LLANTAS * Math.PI) * TICKS_POR_ROTACION);
    resetEncoders();
    motorIzquierdo.setTargetPosition(targetPosition);
    motorDerecho.setTargetPosition(targetPosition);
    motorIzquierdo.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    motorDerecho.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    while(opMode.opModeIsActive() && motorIzquierdo.isBusy() && motorDerecho.isBusy()){
      motorDerecho.setPower(-1);
      motorDerecho.setPower(1);
    }
    motorDerecho.setPower(0);
    motorDerecho.setPower(0);
  }
}

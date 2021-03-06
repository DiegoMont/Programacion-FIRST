package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import java.util.Set;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name="AutonomoB1")

public class AUTONOMOBA extends LinearOpMode {
  private DcMotor leftDrive = null;
  private DcMotor rightDrive = null;
  private DcMotor mediumDrive= null;
  private Servo Servo1=null;
  private Servo Servo2=null;
 ColorSensor sensorcolor=null;

  @Override
public void runOpMode(){
  leftDrive  = hardwareMap.get(DcMotor.class, "m.izquierdo");
    rightDrive = hardwareMap.get(DcMotor.class, "m.derecho");
    mediumDrive= hardwareMap.get(DcMotor.class, "m.medio");
    Servo1= hardwareMap.get(Servo.class, "Servo1");
    Servo2= hardwareMap.get(Servo.class, "Servo2");

   leftDrive.setDirection(DcMotor.Direction.FORWARD);
    rightDrive.setDirection(DcMotor.Direction.REVERSE);
    mediumDrive.setDirection(DcMotor.Direction.REVERSE);

    waitForStart();
    //distancias en inches
    moverDistanciaRecta(68);
    telemetry.addData("Avance lateral", "");
    telemetry.update();
    moverLateralmente(-45);
    telemetry.addData("Avance hacia atras", "");
    telemetry.update();
    moverDistanciaRecta(-45);telemetry.addData("Avance lateral", "");
    telemetry.update();
     moverLateralmente(50);


}
private void moverDistanciaRecta(double distancia){
      leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      leftDrive.setTargetPosition((int)(distancia*288/9));
      rightDrive.setTargetPosition((int)(distancia*288d/9d));
      leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      while (opModeIsActive()&& leftDrive.isBusy()&& rightDrive.isBusy()){
      leftDrive.setPower(0.75);
      rightDrive.setPower(0.75);}
      leftDrive.setPower(0);
      rightDrive.setPower(0);
}
 private void moverLateralmente(double distancia){
      mediumDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       mediumDrive.setTargetPosition((int)(distancia*288d/9d));
       mediumDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive()&& mediumDrive.isBusy()){
            mediumDrive.setPower(1);
        }
      mediumDrive.setPower(0);

}
private void IniciarGiro(double distancia){
      leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      leftDrive.setTargetPosition((int)(distancia*288/9));
      rightDrive.setTargetPosition((int)(-distancia*288/9));
      leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      while (opModeIsActive()&& leftDrive.isBusy()&& rightDrive.isBusy()){
      leftDrive.setPower(1);
      rightDrive.setPower(1);}
      leftDrive.setPower(0);
      rightDrive.setPower(0);

}

}

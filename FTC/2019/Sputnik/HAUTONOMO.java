package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="HAL")
public class HALOADING extends LinearOpMode {

  private DcMotor frontLeft = null;
  private DcMotor frontRight = null;
  private DcMotor backLeft = null;
  private DcMotor backRight = null;
  private DcMotor compliant1 = null;
  private DcMotor compliant2 = null;
  private CRServo Servo3 = null;
  private Servo Servo4 = null;
  private DcMotor elevador1 = null;
  private DcMotor elevador2 = null;

  @Override
  public void runOpMode() {
    frontLeft  = hardwareMap.get(DcMotor.class, "left_front_drive");
    backLeft  = hardwareMap.get(DcMotor.class, "left_back_drive");
    frontRight = hardwareMap.get(DcMotor.class, "right_front_drive");
    backRight = hardwareMap.get(DcMotor.class, "right_back_drive");
    backRight = hardwareMap.get(DcMotor.class, "right_back_drive");
    compliant1 = hardwareMap.get(DcMotor.class, "m.izquierdo");
    compliant2 =hardwareMap.get(DcMotor.class, "m.derecho");
    Servo3 = hardwareMap.get(CRServo.class,"Servo3");
    Servo4 = hardwareMap.servo.get("Servo4");
    elevador1 = hardwareMap.get(DcMotor.class, "elevador-izquierdo");
    elevador2 = hardwareMap.get(DcMotor.class, "elevador-derecho");

    frontLeft.setDirection(DcMotor.Direction.FORWARD);
    frontRight.setDirection(DcMotor.Direction.REVERSE);
    backLeft.setDirection(DcMotor.Direction.FORWARD);
    backRight.setDirection(DcMotor.Direction.REVERSE);
    compliant1.setDirection(DcMotor.Direction.FORWARD);
    compliant2.setDirection(DcMotor.Direction.REVERSE);
    elevador1.setDirection(DcMotor.Direction.REVERSE);
    elevador2.setDirection(DcMotor.Direction.FORWARD);

    waitForStart();

    Servo4.setPosition(0);
    sleep(1000);
    moverDistanciaRecta(-123);
    sleep(2000);
    girar(80);
    sleep(2000);
    activateCompliant(15);
    sleep(100);
    Servo4.setPosition(0);
    sleep(100);
    Servo4.setPosition(0.7);
    sleep(3500);
    moverDistanciaRecta(20);
    sleep(2000);
    moverDistanciaLateral(-62);
    sleep(1500);
    activateElevator(5);
    sleep(1000);
    Servo3.setPower(1);
    sleep(3000);
    Servo4.setPosition(0);
    sleep(3500);
    Servo3.setPower(0);
    sleep(1000);
    activateElevator(-5);
    sleep(999);
    moverDistanciaLateral(62);
    sleep(2000);
  }

  private void activateCompliant (double distancia){
    if(distancia > 0) {
      compliant1.setPower(0.75);
      compliant2.setPower(0.75);
    } else {
      compliant1.setPower(0);
      compliant2.setPower(0);
    }
  }

  private void activateElevator (double distancia){
    if(distancia > 0) {
      elevador1.setPower(0.55);
      elevador2.setPower(0.55);
    } else {
      elevador1.setPower(0);
      elevador2.setPower(0);
    }
  }

  private void moverDistanciaRecta (double distancia){
    final int counts = (int) Math.round(distancia* 1631/10.16/Math.PI);
    //resetEncoders();

    backLeft.setTargetPosition(counts);
    backRight.setTargetPosition(counts);
    frontLeft.setTargetPosition(counts);
    frontRight.setTargetPosition(counts);

    frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    while(opModeIsActive() && backLeft.isBusy()&& backRight.isBusy() && frontLeft.isBusy() && frontRight.isBusy()){
      double velocidad = 0.5;
      backLeft.setPower(velocidad);
      backRight.setPower(velocidad);
      frontLeft.setPower(velocidad);
      frontRight.setPower(velocidad);
    }

    backLeft.setPower(0);
    backRight.setPower(0);
    frontLeft.setPower(0);
    frontRight.setPower(0);
  }

private void girar (double distancia){
    final int counts = (int) Math.round(distancia* 1631/10.16/Math.PI);

    frontLeft.setTargetPosition(counts);
    frontRight.setTargetPosition(-counts);
    backLeft.setTargetPosition(counts);
    backRight.setTargetPosition(-counts);

    frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    while(opModeIsActive() && backLeft.isBusy()&& backRight.isBusy() && frontLeft.isBusy() && frontRight.isBusy()){
      double velocidad = 0.5;

      frontLeft.setPower(velocidad);
      frontRight.setPower(velocidad);
      backLeft.setPower(velocidad);
      backRight.setPower(velocidad);
    }

    frontLeft.setPower(0);
    frontRight.setPower(0);
    backLeft.setPower(0);
    backRight.setPower(0);
  }




  public void moverDistanciaLateral (double distancia){
    final int counts = (int) Math.round(distancia* 1631/10.16/Math.PI);

    backLeft.setTargetPosition(-counts);
    backRight.setTargetPosition(counts);
    frontLeft.setTargetPosition(counts);
    frontRight.setTargetPosition(-counts);

    frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    while(opModeIsActive() && backLeft.isBusy()&& backRight.isBusy() && frontLeft.isBusy() && frontRight.isBusy()){
      double velocidad = 0.5;
      backLeft.setPower(velocidad);
      backRight.setPower(velocidad);
      frontLeft.setPower(velocidad);
      frontRight.setPower(velocidad);
    }

    backLeft.setPower(0);
    backRight.setPower(0);
    frontLeft.setPower(0);
    frontRight.setPower(0);
  }
}

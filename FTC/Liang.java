package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Autonomo2 (Blocks to Java)", group = "")
public class Autonomo2 extends LinearOpMode {

  private Servo servoDer;
  private Servo servoIzq;
  private DcMotor Izquierdo;
  private DcMotor Derecho;
  private Servo servoGarra;
  private DcMotor Engranes;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    ElapsedTime timer;
    boolean primerPaso;
    boolean segundoPaso;
    boolean tercerPaso;
    boolean cuartoPaso;

    servoDer = hardwareMap.servo.get("servoDer");
    servoIzq = hardwareMap.servo.get("servoIzq");
    Izquierdo = hardwareMap.dcMotor.get("Izquierdo");
    Derecho = hardwareMap.dcMotor.get("Derecho");
    servoGarra = hardwareMap.servo.get("servoGarra");
    Engranes = hardwareMap.dcMotor.get("Engranes");

    servoDer.setPosition(1);
    servoIzq.setPosition(0);
    // You will have to determine which motor to reverse for your robot.
    // In this example, the right motor was reversed so that positive
    // applied power makes it move the robot in the forward direction.
    Izquierdo.setDirection(DcMotorSimple.Direction.REVERSE);
    Izquierdo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    Derecho.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    servoGarra.setPosition(0.5);
    timer = new ElapsedTime();
    waitForStart();
    primerPaso = true;
    segundoPaso = false;
    tercerPaso = false;
    cuartoPaso = false;
    Izquierdo.setTargetPosition(Math.round(5.2 * 1175));
    Derecho.setTargetPosition(Math.round(5.2 * 1175));
    Izquierdo.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    Derecho.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    if (opModeIsActive()) {
      Engranes.setPower(0);
      while (opModeIsActive()) {
        if (primerPaso) {
          Izquierdo.setPower(0.5);
          Derecho.setPower(0.5);
        }
        if (Izquierdo.getCurrentPosition() > 5800 && primerPaso) {//cambiar por isBusy
          primerPaso = false;
          segundoPaso = true;
        }
        if (segundoPaso == true) {
          servoGarra.setPosition(0.22);
          Izquierdo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
          Derecho.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
          Izquierdo.setTargetPosition(Math.round(-4.2 * 1175) + Izquierdo.getCurrentPosition());
          Derecho.setTargetPosition(Math.round(4.2 * 1175) + Izquierdo.getCurrentPosition());
          Izquierdo.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          Derecho.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          segundoPaso = false;
          primerPaso = true;//cambiar a false
          tercerPaso = true;
        }
        if (tercerPaso) {
          Izquierdo.setPower(0);
          Derecho.setPower(0);
        }
        if (Izquierdo.getCurrentPosition() > 850 && tercerPaso) {//cambiar por isBusy
          Izquierdo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
          Derecho.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
          servoIzq.setPosition(0.5);
          Izquierdo.setTargetPosition(Math.round(7 * 1175) + Izquierdo.getCurrentPosition());
          Derecho.setTargetPosition(Math.round(7 * 1175) + Derecho.getCurrentPosition());
          Izquierdo.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          Derecho.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          tercerPaso = false;
          cuartoPaso = true;
        }
        if (cuartoPaso) {
          Izquierdo.setPower(0.5);
          Derecho.setPower(0.5);
        }
        telemetry.addData("Time: ", timer.milliseconds());
        telemetry.addData("Encoder Izq:", Izquierdo.getCurrentPosition());
        telemetry.addData("Encoder Der:", Derecho.getCurrentPosition());
        telemetry.addData("Target Izq", Izquierdo.getTargetPosition());
        telemetry.addData("Target Der", Derecho.getTargetPosition());
        telemetry.update();
      }
    }
  }
}

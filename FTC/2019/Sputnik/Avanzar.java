package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.Set;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Avanzar", group="Linear Opmode")
public class Avanzar extends LinearOpMode {
  private ElapsedTime runtime = new ElapsedTime();
  private DcMotor leftDrive = null;
  private DcMotor rightDrive = null;
  private DcMotor mediumDrive= null;
  private Servo Servo1=null;
  private Servo Servo2=null;
  private Servo Servo3=null;
  private Servo Servo4=null;
  private DcMotor elevador1=null;
  private DcMotor elevador2=null;
  private DcMotor elevador3=null;



  @Override
  public void runOpMode() {
    telemetry.addData("Status", "Initialized");
    telemetry.update();

    leftDrive  = hardwareMap.get(DcMotor.class, "m.izquierdo");
    rightDrive = hardwareMap.get(DcMotor.class, "m.derecho");
    mediumDrive= hardwareMap.get(DcMotor.class, "m.medio");
   elevador1= hardwareMap.get(DcMotor.class, "elevador-izquierdo");
   elevador2= hardwareMap.get(DcMotor.class, "elevador-derecho");
    elevador3= hardwareMap.get(DcMotor.class, "elevador");

    Servo1=hardwareMap.servo.get("Servo1");
    Servo2=hardwareMap.servo.get("Servo2");
    Servo3=hardwareMap.servo.get("Servo3");
    Servo4=hardwareMap.servo.get("Servo4");

    leftDrive.setDirection(DcMotor.Direction.FORWARD);
    rightDrive.setDirection(DcMotor.Direction.REVERSE);
    elevador1.setDirection(DcMotor.Direction.REVERSE);
    elevador2.setDirection(DcMotor.Direction.FORWARD);

    leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    mediumDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    mediumDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    waitForStart();
    runtime.reset();
    boolean click = false;
    boolean click1 = false;
    boolean click2 = false;
    boolean modoDriver = true;
    double periodo = runtime.milliseconds();
    double position = 0;

    while (opModeIsActive()) {
      double actual = runtime.milliseconds();
      double leftPower;
      double rightPower;
      double mediumPower;
      double elevadorPower;



      final int TICKS_TO_TURN = 500;

      if(gamepad2.dpad_up){
        elevadorPower=(0.2);
      }else if(gamepad2.dpad_down){
        elevadorPower=(-0.2);
      }else{
        elevadorPower=(0);
      }

      double elevadorPower1;
      if(gamepad2.dpad_left){
        elevadorPower1=(0.7);
      }else if(gamepad2.dpad_right){
        elevadorPower1=(-0.7);
      }else{
        elevadorPower1=(0);
      }

      if(periodo + 1000 > actual) {
        periodo = actual;
        if(gamepad2.left_bumper){
          position += 0.02;
        } else if(gamepad2.right_bumper) {
          position -= 0.02;
        }
      }
      position = Range.clip(position, 0, 1);

   if(gamepad1.y){
     Servo2.setPosition(0.002);
   }else{
    Servo2.setPosition(1);
   }

   if(gamepad1.b){
        Servo1.setPosition(1);
    }else{
          Servo1.setPosition(0.002);
    }
    if(gamepad2.b){
     Servo3.setPosition(0.3);
   }else{
    Servo3.setPosition(0.9);
   }


    if(gamepad1.back){
        click = true;
      } else if ( !gamepad1.back && click){
        modoDriver = (modoDriver)?false:true;
        click = false;
      }

      if(modoDriver){
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0);
        mediumPower= gamepad1.left_stick_x;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0);
      } else {
        leftPower = -gamepad1.left_stick_y;
        rightPower = -gamepad1.right_stick_y;
        mediumPower=gamepad1.right_stick_x;
      }

      if(gamepad1.left_trigger > 0 && !click1){
        int leftTarget = leftDrive.getCurrentPosition() - TICKS_TO_TURN;
        leftDrive.setTargetPosition(leftTarget);
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int rightTarget = rightDrive.getCurrentPosition() + TICKS_TO_TURN;
        rightDrive.setTargetPosition(rightTarget);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        click1 = true;
      } else if(gamepad1.left_trigger == 0 && click1){
        click1 = false;
      }

      if(gamepad1.right_trigger > 0 && !click2){
        int leftTarget = leftDrive.getCurrentPosition() + TICKS_TO_TURN;
        leftDrive.setTargetPosition(leftTarget);
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int rightTarget = rightDrive.getCurrentPosition() - TICKS_TO_TURN;
        rightDrive.setTargetPosition(rightTarget);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        click2 = true;
      } else if(gamepad1.right_trigger == 0 && click2){
        click2 = false;
      }

      if(leftDrive.isBusy() && rightDrive.isBusy()){
        leftPower = 1;
        rightPower = 1;
      } else {
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      }

      leftDrive.setPower(leftPower);
      rightDrive.setPower(rightPower);
      mediumDrive.setPower(mediumPower);
      Servo4.setPosition(position);
      elevador1.setPower(elevadorPower);
      elevador2.setPower(elevadorPower);
      elevador3.setPower(elevadorPower1);

      telemetry.addData("Status", "Run Time: " + runtime.toString());
      telemetry.addData("Velocidad m.izquierdo:", leftPower);
      telemetry.addData("Velocidad m.derecho:", rightPower);
      telemetry.update();
      telemetry.addData("enconder izquierdo", leftDrive.getCurrentPosition());
      telemetry.addData("encoder derecho",rightDrive.getCurrentPosition());
      telemetry.addData("target" , rightDrive.getTargetPosition());
    }
  }
}

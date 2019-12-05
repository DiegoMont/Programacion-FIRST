package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import java.util.List;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;

@Autonomous (name="AUTONOMOLI")

public class AUTONOMOLI extends LinearOpMode {
  private ElapsedTime runtime;
  private DcMotor leftDrive = null;
  private DcMotor rightDrive = null;
  private DcMotor mediumDrive= null;
  private Servo Servo1=null;
  private Servo Servo2=null;
  private ColorSensor colorsensor=null;
  public VuforiaLocalizer vuforia;
  public TFObjectDetector tfod;

  @Override
public void runOpMode(){
    runtime = new ElapsedTime();
    leftDrive  = hardwareMap.get(DcMotor.class, "m.izquierdo");
    rightDrive = hardwareMap.get(DcMotor.class, "m.derecho");
    mediumDrive= hardwareMap.get(DcMotor.class, "m.medio");
    Servo1= hardwareMap.get(Servo.class, "Servo1");
    Servo2= hardwareMap.get(Servo.class, "Servo2");

   leftDrive.setDirection(DcMotor.Direction.FORWARD);
    rightDrive.setDirection(DcMotor.Direction.REVERSE);
    mediumDrive.setDirection(DcMotor.Direction.REVERSE);
     VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

    parameters.vuforiaLicenseKey = "ATjrkEL/////AAABmfR2/BPftkOFvL9kl5ElbHswfU6Tuno4QSB4aHpVUmWaWqKdEUps2CsnGbmjoGqMAfOjyPlhrew8njlemEsarH9XKySF9i0egaUhOiT2fE0MivatYaT037ZwPe1bOkI1GGmd2CsWL8GeupcT91XQkGhRcMyTS3ZfmDYu1/HmcRxCy4zxwbiyPVcoHtsh+KPfjI29mv9YfMStiB4/o8FgefPbTGtX6L9zeoyUemNIMN1WcaMi6wSM7rB7kF3VnUJCrXAca6YmFNEr6GEdJX4G7JhO5EiD6K/e1+wZ0fLtWiQDWe09Bgxxpp2n+qHeccA06zA8nNTo2F07UORoM40ZK29vMj4eh0GjyNMAOmWcuQeI";
    parameters.cameraDirection = CameraDirection.BACK;

    vuforia = ClassFactory.getInstance().createVuforia(parameters);

   int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
    tfodParameters.minimumConfidence = 0.8;
    tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
    tfod.loadModelFromAsset("Skystone.tflite", "Stone", "Skystone");
    tfod.activate();

    waitForStart();


  runtime.reset();

  while(runtime.milliseconds() < 20000 && opModeIsActive()) {
   List<Recognition> recognitions = tfod.getUpdatedRecognitions();
    if(recognitions != null) {
      telemetry.addData("# Objetos: ", recognitions.size());
      if(recognitions.size() == 0) {
          mediumDrive.setPower(-0.5);
      } else {
          break;
      }
    }
    telemetry.update();
  }
    boolean FoundSkystone= false;
    buscarStones: while(!FoundSkystone && !isStopRequested()){
        List<Recognition> recognitions = tfod.getUpdatedRecognitions();
        if(recognitions!= null){
            telemetry.addData("Size", recognitions.size());
            for(Recognition bloque: recognitions){
                telemetry.addData("Label", bloque.getLabel() );
                if(bloque.getLabel().equals("Skystone")) {
                    FoundSkystone = true;
                  break buscarStones;
                }
            }
            telemetry.update();
            moverDistanciaRecta(4);

        }

    }
        if(FoundSkystone && !isStopRequested()) {
        moverLateralmente(-60);
        sleep(2000);
        Servo1.setPosition(1);
        Servo2.setPosition(0);
        sleep(1000);
        moverLateralmente(20);
        moverDistanciaRecta(66);
        }
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
public void initVuforia() {
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
    parameters.vuforiaLicenseKey = "ATjrkEL/////AAABmfR2/BPftkOFvL9kl5ElbHswfU6Tuno4QSB4aHpVUmWaWqKdEUps2CsnGbmjoGqMAfOjyPlhrew8njlemEsarH9XKySF9i0egaUhOiT2fE0MivatYaT037ZwPe1bOkI1GGmd2CsWL8GeupcT91XQkGhRcMyTS3ZfmDYu1/HmcRxCy4zxwbiyPVcoHtsh+KPfjI29mv9YfMStiB4/o8FgefPbTGtX6L9zeoyUemNIMN1WcaMi6wSM7rB7kF3VnUJCrXAca6YmFNEr6GEdJX4G7JhO5EiD6K/e1+wZ0fLtWiQDWe09Bgxxpp2n+qHeccA06zA8nNTo2F07UORoM40ZK29vMj4eh0GjyNMAOmWcuQeI";
    parameters.cameraDirection = CameraDirection.BACK;
    vuforia = ClassFactory.getInstance().createVuforia(parameters);
  }

  public void initTfod() {
    if(!ClassFactory.getInstance().canCreateTFObjectDetector()) return;
    int tfodMonitorViewId =hardwareMap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id",hardwareMap.appContext.getPackageName());
    TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
    tfodParameters.minimumConfidence = 0.8;
    tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
    tfod.loadModelFromAsset("Skystone.tflite", "Stone", "Skystone");
    tfod.activate();
}
}

    

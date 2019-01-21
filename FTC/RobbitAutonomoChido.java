package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.List;
import java.util.ArrayList;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

@Autonomous(name="Frente al crater", group="Linear Opmode")

public class AutonomoChido extends LinearOpMode{

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "ATjrkEL/////AAABmfR2/BPftkOFvL9kl5ElbHswfU6Tuno4QSB4aHpVUmWaWqKdEUps2CsnGbmjoGqMAfOjyPlhrew8njlemEsarH9XKySF9i0egaUhOiT2fE0MivatYaT037ZwPe1bOkI1GGmd2CsWL8GeupcT91XQkGhRcMyTS3ZfmDYu1/HmcRxCy4zxwbiyPVcoHtsh+KPfjI29mv9YfMStiB4/o8FgefPbTGtX6L9zeoyUemNIMN1WcaMi6wSM7rB7kF3VnUJCrXAca6YmFNEr6GEdJX4G7JhO5EiD6K/e1+wZ0fLtWiQDWe09Bgxxpp2n+qHeccA06zA8nNTo2F07UORoM40ZK29vMj4eh0GjyNMAOmWcuQeI";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private boolean targetVisible = false;

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor centreDrive = null;
    private DcMotor elevador = null;
    private Servo servoMinerales = null;
    private Servo mano = null;
    private Servo pinza = null;
    private Servo teamMarker = null;

    public void desplazamiento(double motorderecho,double motorizquierdo,double velocidad){
      leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      leftDrive.setTargetPosition((int)Math.round(288*motorizquierdo));
      rightDrive.setTargetPosition((int)Math.round(288*motorderecho));
      leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      while (opModeIsActive() && leftDrive.isBusy() && rightDrive.isBusy()) {
        leftDrive.setPower(velocidad);
        rightDrive.setPower(velocidad);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motor derecho: ", rightDrive.getCurrentPosition());
        telemetry.addData("Motor izquierdo: ", leftDrive.getCurrentPosition());
        telemetry.update();
      }
      double currentTime = runtime.milliseconds();
      while(opModeIsActive() && currentTime + 500 > runtime.milliseconds()){
          leftDrive.setPower(0);
          rightDrive.setPower(0);
          telemetry.addData("Status", "Run Time: " + runtime.toString());
          telemetry.update();
      }
    }

    private void desplazarCentro(double rot, double vel){
        centreDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        centreDrive.setTargetPosition((int)(288*rot));
        centreDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(opModeIsActive() && centreDrive.isBusy()){
            centreDrive.setPower(vel);
        }
        double currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime + 500 > runtime.milliseconds()){
          centreDrive.setPower(0);
        }
    }

    @Override
    public void runOpMode() {

        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        centreDrive = hardwareMap.get(DcMotor.class, "centre_drive");
        elevador = hardwareMap.get(DcMotor.class, "elevador");
        servoMinerales = hardwareMap.get(Servo.class, "perfil");
        mano = hardwareMap.get(Servo.class, "mano");
        pinza = hardwareMap.get(Servo.class, "garra");
        teamMarker = hardwareMap.get(Servo.class, "catapulta");

        initVuforia();
        initTfod();

        VuforiaTrackables targetsRoverRuckus = this.vuforia.loadTrackablesFromAsset("RoverRuckus");
        VuforiaTrackable blueRover = targetsRoverRuckus.get(0);
        blueRover.setName("Blue-Rover");
        VuforiaTrackable redFootprint = targetsRoverRuckus.get(1);
        redFootprint.setName("Red-Footprint");
        VuforiaTrackable frontCraters = targetsRoverRuckus.get(2);
        frontCraters.setName("Front-Craters");
        VuforiaTrackable backSpace = targetsRoverRuckus.get(3);
        backSpace.setName("Back-Space");
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsRoverRuckus);

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        servoMinerales.setPosition(0);
        teamMarker.setPosition(0.75);
        pinza.setPosition(0.95);
        mano.setPosition(0);

        while(!isStarted()){
            double elevadorPower;
            if (gamepad2.a || gamepad1.a) {
            elevadorPower = 0.7;
          } else if (gamepad2.b || gamepad1.b) {
            elevadorPower = -0.7;
          } else {
            elevadorPower = 0.0;
          }
          elevador.setPower(elevadorPower);
          telemetry.addData(">", "Press Play to start");
          telemetry.addData("Elevador: ", elevador.getCurrentPosition());
          telemetry.update();
        }
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador.setTargetPosition((int)Math.round(-2770));
        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForStart();

        runtime.reset();
        //Landing
        while (opModeIsActive()&&elevador.isBusy()){
            elevador.setPower(1);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Etapa: ", "Landing");
            telemetry.addData("Elevador: ", elevador.getCurrentPosition());
            telemetry.update();
        }
        desplazamiento(0.1,0.1,0.7);
        desplazarCentro(0.5,0.75);

        //Posicionarse frente a los minerales
        desplazamiento(1.4,1.4,1);

        //Identificar minerales
        if (opModeIsActive()) {
            if (tfod != null) {
                tfod.activate();
            }
            boolean findMineral =false;
            int platasVistas = 0;
            while (opModeIsActive() && !findMineral && runtime.milliseconds() < 25000) {
                if (tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        if (updatedRecognitions.size() == 1) {
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    findMineral = true;
                                    break;
                                } else {
                                    platasVistas++;
                                }
                            }
                            if(platasVistas == 1 && !findMineral){
                            //Avanzar al siguiente mineral
                            telemetry.addData("Avanzar al siguiente mineral"," de la derecha" );
                            desplazarCentro(2.25,0.85);
                            desplazamiento(0.3,0.28,1);
                        } else if (platasVistas == 2 && !findMineral){
                            //Avanzar al siguiente mineral
                            telemetry.addData("Avanzar al siguiente mineral"," de la derecha" );
                            desplazarCentro(-4.5,0.85);
                            findMineral = true;
                        }
                        }
                    } else {
                        telemetry.addData("No está viendo ni madres"," -.-" );
                    }
                }
            telemetry.addData("Platas: ", platasVistas);
            telemetry.update();
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }

        //Mover mineral de oro
        desplazamiento(0.5,0.5,1);
        servoMinerales.setPosition(0.7);
        sleep(550);
        servoMinerales.setPosition(0);
        desplazamiento(2,2,1);
    }

    private void initVuforia() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}

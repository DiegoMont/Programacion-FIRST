/*Copyright 2019

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import com.qualcomm.robotcore.util.Range;

@Disabled
public class NaveDelOlvido {

  //Inicializar variables para motores y sensores del robot
  public DcMotor frontLeft = null;
  public DcMotor frontRight = null;
  public DcMotor backLeft = null;
  public DcMotor backRight = null;
  private DcMotor elevadorOne = null;
  private DcMotor elevadorTwo = null;
  public Servo servoUno = null;
  public Servo servoDos = null;
  private DcMotor extensionIntake = null;
  //Servos para mover foundation
  public Servo foundationDerecha = null;
  public Servo foundationIzquierda = null;

  public VuforiaLocalizer vuforia;
  public TFObjectDetector tfod;

  private LinearOpMode programa;
  private BNO055IMU imu;
  private Orientation angles;
  private Acceleration gravity;

  public NaveDelOlvido(LinearOpMode programa){
    this.programa = programa;
  }

  //Metodo para buscar motores y servomotores del Expansion y asignarlos a las variables
  public void getHardware(HardwareMap hwMap){
      frontLeft = hwMap.get(DcMotor.class, "left_front_drive");
      frontRight = hwMap.get(DcMotor.class, "right_front_drive");
      backLeft = hwMap.get(DcMotor.class, "left_back_drive");
      backRight = hwMap.get(DcMotor.class, "right_back_drive");
      elevadorOne = hwMap.get(DcMotor.class, "elevador_derecha");
      elevadorTwo = hwMap.get(DcMotor.class, "elevador_izquierda");
      servoUno = hwMap.get(Servo.class, "servo_uno");
      servoDos = hwMap.get(Servo.class, "servo_dos");
      extensionIntake = hwMap.get(DcMotor.class, "tornillo");
      foundationDerecha = hwMap.get(Servo.class, "foundation_derecha");
      foundationIzquierda = hwMap.get(Servo.class, "foundation_izquierda");

      frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

      frontLeft.setDirection(DcMotor.Direction.FORWARD);
      frontRight.setDirection(DcMotor.Direction.REVERSE);
      backLeft.setDirection(DcMotor.Direction.FORWARD);
      backRight.setDirection(DcMotor.Direction.REVERSE);
      elevadorOne.setDirection(DcMotor.Direction.REVERSE);
      elevadorTwo.setDirection(DcMotor.Direction.FORWARD);

      elevadorOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      elevadorTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      extensionIntake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      elevadorOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      elevadorTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      extensionIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

      servoDos.setPosition(0.75);
      servoUno.setPosition(0);

      activarFoundation(true);

  }

  public void iniciarAcelerometro(HardwareMap hwMap) {
    programa.telemetry.addData("Calibrando ", "IMU");
    programa.telemetry.update();
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
    parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    parameters.calibrationDataFile = "BNO055IMUCalibration.json";
    parameters.loggingEnabled      = true;
    parameters.loggingTag          = "IMU";
    parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

    imu = hwMap.get(BNO055IMU.class, "imu");
    imu.initialize(parameters);
    //imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
  }

  public double getDesviacion(){
    if(imu == null)
      return 0;
    angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    gravity  = imu.getGravity();
    programa.sleep(25);
    return angles.firstAngle;
  }

  public int posicionElevador() {
    return (elevadorOne.getCurrentPosition() + elevadorTwo.getCurrentPosition()) / 2;
  }

  public void frenar(){
    frontLeft.setPower(0);
    frontRight.setPower(0);
    backLeft.setPower(0);
    backRight.setPower(0);
  }

  public void activarElevador(double power) {
    double velocidad = 0.25;
    if(power > 0) {
      elevadorOne.setPower(velocidad);
      elevadorTwo.setPower(velocidad);
    } else if(power < 0) {
      elevadorOne.setPower(-velocidad);
      elevadorTwo.setPower(-velocidad);
    } else {
      elevadorOne.setPower(0);
      elevadorTwo.setPower(0);
    }
  }

  public void activarExtension(double power) {
    double velocidad = 0.5;
    if(power > 0) {
      extensionIntake.setPower(velocidad);
    } else if(power < 0) {
      extensionIntake.setPower(-velocidad);
    } else {
      extensionIntake.setPower(0);
    }
  }

  public void activarFoundation(boolean power) {
    if(power) {
      foundationDerecha.setPosition(0.8);
      foundationIzquierda.setPosition(0.0);
    } else {
      foundationDerecha.setPosition(0.024);
      foundationIzquierda.setPosition(0.596);
    }
  }

  public void resetEncoders(){
    frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    defaultRunmode();
  }

  public void defaultRunmode(){
    frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
  }

  public void initVuforia() {
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
    parameters.vuforiaLicenseKey = "ATjrkEL/////AAABmfR2/BPftkOFvL9kl5ElbHswfU6Tuno4QSB4aHpVUmWaWqKdEUps2CsnGbmjoGqMAfOjyPlhrew8njlemEsarH9XKySF9i0egaUhOiT2fE0MivatYaT037ZwPe1bOkI1GGmd2CsWL8GeupcT91XQkGhRcMyTS3ZfmDYu1/HmcRxCy4zxwbiyPVcoHtsh+KPfjI29mv9YfMStiB4/o8FgefPbTGtX6L9zeoyUemNIMN1WcaMi6wSM7rB7kF3VnUJCrXAca6YmFNEr6GEdJX4G7JhO5EiD6K/e1+wZ0fLtWiQDWe09Bgxxpp2n+qHeccA06zA8nNTo2F07UORoM40ZK29vMj4eh0GjyNMAOmWcuQeI";
    parameters.cameraDirection = CameraDirection.BACK;
    vuforia = ClassFactory.getInstance().createVuforia(parameters);
  }

  public void initTfod() {
    if(!ClassFactory.getInstance().canCreateTFObjectDetector()) return;
    int tfodMonitorViewId = programa.hardwareMap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", programa.hardwareMap.appContext.getPackageName());
    TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
    tfodParameters.minimumConfidence = 0.8;
    tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
    tfod.loadModelFromAsset("Skystone.tflite", "Stone", "Skystone");
    tfod.activate();
  }

  /* Metodos para programacion autonoma */

  //Este metodo movera al robot en linea recta la distancia que se especifique
  public void moverDistanciaRecta(double distancia){
    final int counts = (int)Math.round(distancia * 1631 / 10.16 / Math.PI);
    double desiredPosition = getDesviacion();
    if(desiredPosition == 0)
      desiredPosition = 0.0625;
    resetEncoders();
    backLeft.setTargetPosition(counts);
    backRight.setTargetPosition(counts);
    frontLeft.setTargetPosition(counts);
    frontRight.setTargetPosition(counts);
    backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    while (programa.opModeIsActive() && backLeft.isBusy() && backRight.isBusy() && frontLeft.isBusy() && frontRight.isBusy()) {
      double desviacion = getDesviacion();
      double errorRelativo = (desiredPosition-desviacion)/desiredPosition;
      double leftPower = 0;
      double rightPower = 0;
      double velocidad = 0.7;
      final double PROPORTIONAL = 0.002;
      if(distancia > 0) {
        leftPower = velocidad;
        rightPower = velocidad;
        leftPower -= leftPower * errorRelativo * PROPORTIONAL;
        rightPower += rightPower * errorRelativo * PROPORTIONAL;
      } else if(distancia < 0) {
        velocidad *= -1;
        leftPower = velocidad;
        rightPower = velocidad;
        leftPower += leftPower * errorRelativo * PROPORTIONAL;
        rightPower -= rightPower * errorRelativo * PROPORTIONAL;
      }
      leftPower = Range.clip(leftPower, -1.0, 1.0);
      rightPower = Range.clip(rightPower, -1.0, 1.0);
      programa.telemetry.addData("Left power: ", leftPower);
      programa.telemetry.addData("Right power: ", rightPower);
      programa.telemetry.addData("Error: ", errorRelativo);
      programa.telemetry.addData("Desviacion: ", desviacion);
      programa.telemetry.addData("Target ", desiredPosition);
      programa.telemetry.update();
      backLeft.setPower(leftPower);
      backRight.setPower(rightPower);
      frontRight.setPower(rightPower);
      frontLeft.setPower(leftPower);
    }
    frenar();
    defaultRunmode();
  }

  public void movimientoLateral(double distancia){
    final int counts = (int)Math.round(distancia * 1631 / 10.16 / Math.PI);
    double desiredPosition = getDesviacion();
    if(desiredPosition == 0)
      desiredPosition = 0.0625;
    resetEncoders();
    backLeft.setTargetPosition(-counts);
    backRight.setTargetPosition(counts);
    frontLeft.setTargetPosition(counts);
    frontRight.setTargetPosition(-counts);
    backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    while (programa.opModeIsActive() && backLeft.isBusy() && backRight.isBusy() && frontLeft.isBusy() && frontRight.isBusy()) {
      double desviacion = getDesviacion();
      double errorRelativo = (desiredPosition-desviacion)/desiredPosition;
      double leftPower = 0;
      double rightPower = 0;
      double velocidad = 0.7;
      final double PROPORTIONAL = 0.002;
      if(distancia != 0) {
        leftPower = velocidad;
        rightPower = velocidad;
        leftPower += leftPower * errorRelativo * PROPORTIONAL;
        rightPower -= rightPower * errorRelativo * PROPORTIONAL;
      }
      leftPower = Range.clip(leftPower, -1.0, 1.0);
      rightPower = Range.clip(rightPower, -1.0, 1.0);
      programa.telemetry.addData("Left power: ", leftPower);
      programa.telemetry.addData("Right power: ", rightPower);
      programa.telemetry.addData("Error: ", errorRelativo);
      programa.telemetry.addData("Desviacion: ", desviacion);
      programa.telemetry.addData("Target ", desiredPosition);
      programa.telemetry.update();
      backLeft.setPower(leftPower);
      backRight.setPower(rightPower);
      frontRight.setPower(rightPower);
      frontLeft.setPower(leftPower);
    }
    frenar();
    defaultRunmode();
  }

  public void movimientoDiagonalDerecha(double distancia) {
    final int counts = (int)Math.round(distancia * 1631 / 10.16 / Math.PI);
    resetEncoders();
    backRight.setTargetPosition(counts);
    frontLeft.setTargetPosition(counts);
    frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    while (programa.opModeIsActive() && backRight.isBusy() && frontLeft.isBusy()) {
      double velocidad = 1;
      backRight.setPower(velocidad);
      frontLeft.setPower(velocidad);
    }
    frenar();
    defaultRunmode();
  }

  public void movimientoDiagonalIzquierda(double distancia) {
    final int counts = (int)Math.round(distancia * 1631 / 10.16 / Math.PI);
    resetEncoders();
    frontRight.setTargetPosition(counts);
    backLeft.setTargetPosition(counts);
    backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    while (programa.opModeIsActive() && backLeft.isBusy() && frontRight.isBusy()) {
      double velocidad = 1;
      frontRight.setPower(velocidad);
      backLeft.setPower(velocidad);
    }
    frenar();
    defaultRunmode();
  }

  public void acomodarRobot() {
    moverDistanciaRecta(5);
    servoUno.setPosition(0.6);
    programa.sleep(1000);
    while(posicionElevador() > -545) {
      activarElevador(-1);
    }
    activarElevador(0);
  }

  public void girarEnEje(double distancia) {
    if(!programa.opModeIsActive()) return;
      final int counts = (int)Math.round(distancia * 1631 / 10.16 / Math.PI);

      //Establecer la posicion actual del encoder como nuestro cero
      resetEncoders();

      //Establecer a que posicion y velocidad se debe mover el robot
      frontLeft.setTargetPosition(counts);
      backLeft.setTargetPosition(counts);
      frontRight.setTargetPosition(-counts);
      backRight.setTargetPosition(-counts);
      backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      double velocidad = 0.75;
      frontLeft.setPower(velocidad);
      backRight.setPower(velocidad);
      frontRight.setPower(velocidad);
      backLeft.setPower(velocidad);
      //Cambiar el modo del motor para comenzar movimiento automatico
      while(programa.opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy()) {
      }
      frenar();
      defaultRunmode();
  }
}

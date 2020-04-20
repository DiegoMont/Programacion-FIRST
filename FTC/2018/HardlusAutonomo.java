package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Autonomo", group="Linear Opmode")
public class HardlusAutonomo extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor lanzador = null;
    private DcMotor elevador = null;
    private DcMotor elevadorGrande = null;
    private Servo servo1 = null;
    private Servo servo2 = null;
    private Servo catapulta = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        elevador = hardwareMap.get(DcMotor.class, "elevador");
        elevadorGrande = hardwareMap.get(DcMotor.class, "elevador1");
        lanzador = hardwareMap.get(DcMotor.class, "lanzador");
        servo1 = hardwareMap.get(Servo.class,"servo1" );
        servo2 = hardwareMap.get(Servo.class,"servo2" );
        catapulta = hardwareMap.get(Servo.class, "catapulta");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        servo1.setPosition(0);
        servo2.setPosition(1);
        elevadorGrande.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevadorGrande.setTargetPosition(-2120);
        elevadorGrande.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        catapulta.setPosition(0.5);

        waitForStart();
        runtime.reset();
        double currentTime;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive() && elevadorGrande.isBusy()) {
            elevadorGrande.setPower(0.5);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Elevador: ", elevadorGrande.getCurrentPosition());
            telemetry.update();
        }

        //Esperar 1000ms con los motores a velocidad 0
        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +1000 > runtime.milliseconds()){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }

        //girar sobre su propio eje hacia la derecha
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setTargetPosition(130);
        rightDrive.setTargetPosition(-130);
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //mover robot al 100% durante 100ms
        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +1000 > runtime.milliseconds()){
            leftDrive.setPower(1);
            rightDrive.setPower(1);
        }

        //Esperar 1000ms con los motores a velocidad 0
        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +1000 > runtime.milliseconds()){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }

        //mover al robot hacia adelante
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setTargetPosition(130);
        rightDrive.setTargetPosition(130);
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Esperar 1000ms con los motores a velocidad 1
        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +1000 > runtime.milliseconds()){
            leftDrive.setPower(1);
            rightDrive.setPower(1);
        }

        //Esperar 1000ms con los motores a velocidad 0
        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +1000 > runtime.milliseconds()){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }

        //Girar sobre su propio eje hacia la izquierda
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setTargetPosition(-130);
        rightDrive.setTargetPosition(130);
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Esperar 1000ms con los motores a velocidad 1
        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +1000 > runtime.milliseconds()){
            leftDrive.setPower(1);
            rightDrive.setPower(1);
        }

        //Esperar 1000ms con los motores a velocidad 0
        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +1000 > runtime.milliseconds()){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }

        //Avanzar hacia adelante
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setTargetPosition((int) Math.round(288*5));
        rightDrive.setTargetPosition((int) Math.round(288*5));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Esperar 1000ms con los motores a velocidad 1
        currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime +1000 > runtime.milliseconds()){
            leftDrive.setPower(1);
            rightDrive.setPower(1);
        }
        catapulta.setPosition(0.3);
    }
}

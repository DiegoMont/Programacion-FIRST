package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Autonomo 2.0", group="Linear Opmode")

public class NaubotsAutonomoChido extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor elevador = null;
    private Servo teamMarker = null;

    private void avanzar(double right, double left, double vel){
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setTargetPosition((int)(288*left));
        rightDrive.setTargetPosition((int)(288*right));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(opModeIsActive() && leftDrive.isBusy() && rightDrive.isBusy()){
            leftDrive.setPower(vel);
            rightDrive.setPower(vel);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Left encoder: ", leftDrive.getCurrentPosition());
            telemetry.addData("Right encoder: ", rightDrive.getCurrentPosition());
            telemetry.update();
        }
        double currentTime = runtime.milliseconds();
        while(opModeIsActive() && currentTime + 50 > runtime.milliseconds()){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }
    }

    @Override
    public void runOpMode() {

        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        elevador = hardwareMap.get(DcMotor.class, "elevador");
        teamMarker = hardwareMap.get(Servo.class, "catapulta");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        teamMarker.setPosition(0.5);

        while(!isStarted()){
            if(gamepad1.dpad_up || gamepad2.dpad_up){
                elevador.setPower(0.7);
            } else if(gamepad1.dpad_down || gamepad2.dpad_down){
                elevador.setPower(-0.7);
            } else {
                elevador.setPower(0);
            }
            telemetry.addData(">", "Press Play to start tracking");
            telemetry.update();
        }
        elevador.setPower(0);
        waitForStart();
        runtime.reset();

        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador.setTargetPosition(6950);
        elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(opModeIsActive() && elevador.isBusy()){
            elevador.setPower(1);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Status: ", "Bajando");
            telemetry.addData("Elevador: ", elevador.getCurrentPosition());
            telemetry.update();
        }
        elevador.setPower(0);

        avanzar(-0.6,0.6,1);
        avanzar(1.7,-1.7,1);
        avanzar(3.8,3.8,1);
        avanzar(1.7,-1.7,1);
        avanzar(4.5,4.5,1);
        teamMarker.setPosition(0);
        sleep(200);
        avanzar(-10.5,-10.5,1);

    }
}
